package Networks;
import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class CRC
{
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] data;
        int[] div;
        int[] divisor;
        int[] rem;
        int[] crc;

        int data_bits, divisor_bits, tot_length;

        System.out.println("Enter number of data bits : ");
        data_bits = Integer.parseInt(br.readLine());
        data = new int[data_bits];

        System.out.println("Enter data bits : ");
        for (int i = 0; i < data_bits; i++)
            data[i] = Integer.parseInt(br.readLine());

        System.out.println("Enter number of bits in divisor : ");
        divisor_bits = Integer.parseInt(br.readLine());
        divisor = new int[divisor_bits];

        System.out.println("Enter Divisor bits : ");
        for (int i = 0; i < divisor_bits; i++)
            divisor[i] = Integer.parseInt(br.readLine());

        System.out.print("Data bits are : ");
        for (int i = 0; i < data_bits; i++)
            System.out.print(data[i]);
        System.out.println();

        System.out.print("divisor bits are : ");
        for (int i = 0; i < divisor_bits; i++)
            System.out.print(divisor[i]);
        System.out.println();

        tot_length = data_bits + divisor_bits - 1;
        div = new int[tot_length];
        rem = new int[tot_length];
        crc = new int[tot_length];

        for (int i = 0; i < data.length; i++)
            div[i] = data[i];

        System.out.print("Dividend (after appending 0's) are : ");
        for (int i = 0; i < div.length; i++)
            System.out.print(div[i]);
        System.out.println();

        for (int j = 0; j < div.length; j++) {
            rem[j] = div[j];
        }
        rem = divide(div, divisor, rem);

        for (int i = 0; i < div.length; i++) {
            //append dividend and remainder
            crc[i] = (div[i] ^ rem[i]);
        }
        System.out.println();

        //crc generation
        System.out.println("CRC code : ");
        for (int i = 0; i < crc.length; i++)
            System.out.print(crc[i]);
        System.out.println();

        try
        {
            int clientport = 4789, serverport = 4790;
            DatagramSocket ds = new DatagramSocket(clientport);
//            BufferedReader dis = new BufferedReader(new InputStreamReader(System.in));
            InetAddress ia = InetAddress.getLocalHost();
            byte[] sendbyte = new byte[1024];
            byte[] receivebyte = new byte[1024];

            String str = "";
            for(int i=0;i<crc.length;i++){
                str += crc[i];
            }
            str += "$";

            for(int i=0;i<divisor.length;i++){
                str += divisor[i];
            }

            sendbyte = str.getBytes();
            DatagramPacket clientpacket = new DatagramPacket(sendbyte, sendbyte.length, ia, serverport);
            ds.send(clientpacket);

            DatagramPacket p = new DatagramPacket(receivebyte, receivebyte.length);
            ds.receive(p);
            String s = new String(p.getData());
            String psx = s.trim();

            System.out.println("Server:" + psx);
        }
        catch(Exception e)
        {
            System.out.println(e);
        }

    }//psvm
    static int[] divide(int div[],int divisor[], int rem[])
    {
        int cur=0;
        while(true)
        {
            for(int i=0;i<divisor.length;i++)
                rem[cur+i] = (rem[cur+i]^divisor[i]);
            while(rem[cur] == 0 && cur != rem.length-1)
                cur++;
            if((rem.length-cur) < divisor.length)
                break;
        }
        return rem;
    }
}//class


