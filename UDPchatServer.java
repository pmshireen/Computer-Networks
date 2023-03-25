package Networks;

import java.io.*;
import java.net.*;
public class UDPchatServer
{
    public static void main(String args[])throws Exception
    {
        try
        {
                int clientport = 4789, serverport = 4790;
                DatagramSocket server = new DatagramSocket(serverport);
                BufferedReader dis = new BufferedReader(new InputStreamReader(System.in));
                InetAddress ia = InetAddress.getLocalHost();
                 while (true)
                {
                    byte[] sendbyte = new byte[1024];
                    byte[] receivebyte = new byte[1024];

                    System.out.println("Server running");
                    DatagramPacket p = new DatagramPacket(receivebyte, receivebyte.length);
                    server.receive(p);
                    String s = new String(p.getData());
                    String psx = s.trim();

                    if(!psx.equals("exit"))
                    {
                        System.out.println("Client:" + psx);
                        System.out.println("Server:");
                        String str = dis.readLine();
                        sendbyte = str.getBytes();
                        server.send(new DatagramPacket(sendbyte, sendbyte.length, ia, clientport));

                    }//if
                    else{
                        System.out.println("Server exiting");
                        break;
                    }

                }//while
            server.close();
        }//try
        catch(IOException e){
            System.out.println(e);
        }
    }//psvm
}