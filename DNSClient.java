package Networks;
import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class DNSClient {
    public static void main(String[] args)throws Exception {
        try{
            //System.out.println("try block");
            DatagramSocket client = new DatagramSocket(4445);
            InetAddress addr =  InetAddress.getByName("127.0.0.1");

            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            while(true) {
                byte[] sendbyte = new byte[1024];
                byte[] receivebyte = new byte[1024];

                System.out.println("Enter the domain name or IPAddress");
                String str = in.readLine();
                // System.out.println(str +"ip entered");
                sendbyte = str.getBytes();
                DatagramPacket sender = new DatagramPacket(sendbyte, sendbyte.length, addr, 4444);
                client.send(sender);
                if(str.equals("exit")){
                    break;
                }

                DatagramPacket receiver = new DatagramPacket(receivebyte, receivebyte.length);
                client.receive(receiver);
                String s = new String(receiver.getData());
                System.out.println("IP address or Domain Name : " + s.trim());
            }//while
            client.close();
        }//try
        catch(IOException e){
            System.out.println(e);
        }

    }//psvm
}//class

