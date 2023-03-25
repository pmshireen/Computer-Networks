
package Networks;

import java.io.*;
import java.net.*;
import java.util.*;
public class ArpServer{

    public static void main(String[] args) throws SocketException {

        try{
            System.out.println("Server running");
            int clientport = 4000,serverport = 4456;
            DatagramSocket server = new DatagramSocket(serverport);

            while(true) {
                byte[] sendbyte = new byte[1024];
                byte[] receivebyte = new byte[1024];
                BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
                InetAddress addr = InetAddress.getLocalHost();

                DatagramPacket receiver = new DatagramPacket(receivebyte, receivebyte.length);
                // int port = receiver.getPort();

                server.receive(receiver);
                String ip = new String(receiver.getData());
                String s = ip.trim();
                System.out.println(s);

                if(s.equals("Exit")){
                    break;
                }


                File file = new File("D:\\JavaIntelliJ\\Sample\\src\\Networks\\ARPtable.txt");
                if(file == null){
                    System.out.println("File not found");
                }
//                FileReader fr = new FileReader("D:\\JavaIntelliJ\\Sample\\src\\Networks\\ARPtable.txt");
//                BufferedReader br = new BufferedReader(fr);

                BufferedReader br = new BufferedReader(new FileReader(file));
                String line;
                int f = 0;

                while ((line = br.readLine()) != null) {
                    if(line.indexOf(s) >= 0) {
                        System.out.println("Found Mapping -> " + line);
                        sendbyte = line.getBytes();
                        DatagramPacket sender = new DatagramPacket(sendbyte, sendbyte.length, addr, clientport);
                        server.send(sender);
                        f = 1;
                    }
                }
                if (f == 0) {
                    System.out.println("Address not  found");
                    String str1 = "Address Not Found";
                    sendbyte = str1.getBytes();
                    DatagramPacket sender = new DatagramPacket(sendbyte, sendbyte.length, addr, clientport);
                    server.send(sender);
                }
            }//while(true)
            server.close();

        }//try
        catch(IOException e){System.out.println(e);}

    }//psvm

}//public class