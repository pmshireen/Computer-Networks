package Networks;

import java .io.*;
import java.net.*;
class UDPchatClient
{
    public static void main(String args[])throws Exception {
        try {

            int clientport = 4789, serverport = 4790;
            DatagramSocket ds = new DatagramSocket(clientport);
            BufferedReader dis = new BufferedReader(new InputStreamReader(System.in));

            System.out.println("client running");
            InetAddress ia = InetAddress.getLocalHost();

            while (true) {
                byte[] sendbyte = new byte[1024];
                byte[] receivebyte = new byte[1024];
                System.out.println("Client:");
                String str = dis.readLine();
                sendbyte = str.getBytes();

                DatagramPacket clientpacket = new DatagramPacket(sendbyte, sendbyte.length, ia, serverport);
                ds.send(clientpacket);
                if(str.equals("exit"))
                    break;
                DatagramPacket p = new DatagramPacket(receivebyte, receivebyte.length);
                ds.receive(p);
                String s = new String(p.getData());
                String psx = s.trim();

                System.out.println("Server:" + psx);
            }//while
            ds.close();
        }//try
        catch(IOException e){
            System.out.println(e);
    }
}//psvm
}
