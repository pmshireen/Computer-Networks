package Networks;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
public class DNSserver {
    public static void main(String args[])
    {
        try
        {
            DatagramSocket server = new DatagramSocket(4444);
            while(true){
                byte[] sendbyte = new byte[1024];
                byte[] receivebyte = new byte[1024];
                DatagramPacket receiver = new DatagramPacket(receivebyte,receivebyte.length);
                server.receive(receiver);
                String str = new String(receiver.getData());
                String s = str.trim();

                if(!s.equals("exit"))
                System.out.println(s +" data received");

                InetAddress addr = receiver.getAddress();
                int port = receiver.getPort();

                String ip[]={"165.165.80.80","165.165.79.1"};
                String name[]={"www.aptitudeguru.com","www.downloadcyclone.blogspot.com"};
                String r;
                int flag = 1;
                if(s.equals("exit")){
                    System.out.println(" server exiting ");
                    break;
                }

                for(int i=0;i<ip.length;i++)
                {
                    if(s.equals(ip[i]))
                    {
                        sendbyte = name[i].getBytes();
                        DatagramPacket sender = new DatagramPacket(sendbyte,sendbyte.length,addr,port);
                        server.send(sender);
                        flag = 0;
                        break;
                    }
                    else if(s.equals(name[i]))
                    {
                        sendbyte = ip[i].getBytes();
                        DatagramPacket sender = new DatagramPacket(sendbyte,sendbyte.length,addr,port);
                        server.send(sender);
                        flag = 0;
                        break;
                    }
                }//for
                if(flag == 1) {
                    r = "NOT found";
                    sendbyte = r.getBytes();
                    DatagramPacket sender = new DatagramPacket(sendbyte, sendbyte.length, addr, port);
                    server.send(sender);
                    break;
                }
//
            }//while
            server.close();
        }//try
        catch(Exception e)
        {
            System.out.println(e);
        }
    }//psvm
}//class
