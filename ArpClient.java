package Networks;
import java.util.*;
import java.io.*;
import java.net.*;

public class ArpClient {

    public static void main(String[] args) {
        try {


            int clientport = 4000, serverport = 4456;
            DatagramSocket client = new DatagramSocket(clientport);

            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            InetAddress addr = InetAddress.getLocalHost();
            Scanner sc = new Scanner(System.in);
            int opt = 0;

            System.out.println("ARP and Reverse-ARP");
            do {
                System.out.println("1.arp\n2.rarp\n3.exit");
                System.out.println("Enter the option");
                opt = sc.nextInt();

                switch (opt) {
                    case 1: {
                        byte[] sendbyte = new byte[1024];
                        byte[] receivebyte = new byte[1024];
                        System.out.println("Enter the logical IP");
                        String str = in.readLine();
                        sendbyte = str.getBytes();
                        DatagramPacket sender = new DatagramPacket(sendbyte, sendbyte.length, addr, serverport);
                        client.send(sender);

                        DatagramPacket receiver = new DatagramPacket(receivebyte, receivebyte.length);
                        client.receive(receiver);
                        String str1 = new String(receiver.getData());
                        String s = str1.trim();
                        System.out.println("Mac Address: " + s);
                        break;
                    }

                    case 2: {
                        byte[] sendbyte = new byte[1024];
                        byte[] receivebyte = new byte[1024];
                        System.out.println("Enter the Physical Address");
                        String str = in.readLine();
                        sendbyte = str.getBytes();
                        DatagramPacket sender = new DatagramPacket(sendbyte, sendbyte.length, addr, serverport);
                        client.send(sender);

                        DatagramPacket receiver = new DatagramPacket(receivebyte, receivebyte.length);
                        client.receive(receiver);
                        String str2 = new String(receiver.getData());
                        String s = str2.trim();
                        System.out.println("IP Address: " + s);
                        break;
                    }

                    case 3:
                        byte[] sendbyte = new byte[1024];
                        System.out.println("Exiting");
                        String str = "Exit";
                        sendbyte = str.getBytes();
                        DatagramPacket sender = new DatagramPacket(sendbyte, sendbyte.length, addr, serverport);
                        client.send(sender);
                        break;

                    default:
                        System.out.println("Invalid input");

                }//switch

            }while(opt >=1 && opt <= 2) ;



        } catch (IOException e) {
            System.out.println(e);
        }


    }//psvm
}//class