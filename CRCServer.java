package Networks;
import java.net.*;
import java.io.*;
import java.util.Arrays;

public class CRCServer
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try
        {
            int[] rem;
            int[] crc;
            int[] divisor;

            int clientport = 4789, serverport = 4790;
            DatagramSocket server = new DatagramSocket(serverport);
            BufferedReader dis = new BufferedReader(new InputStreamReader(System.in));
            InetAddress ia = InetAddress.getLocalHost();

                byte[] sendbyte = new byte[1024];
                byte[] receivebyte = new byte[1024];

                DatagramPacket p = new DatagramPacket(receivebyte, receivebyte.length);
                server.receive(p);
                String s = new String(p.getData());
                String psx = s.trim();

                int i,crc_len = 0;

                for(i=0;i < psx.length();i++)
                {
                    if(psx.charAt(i) == '$') {
                        break;
                    }
                    crc_len++;
                }

                crc = new int[crc_len];

                for(int j=0;j<crc_len;j++)
                {
                    crc[j] = psx.charAt(j) - '0';
                }

                int divisor_bits = psx.length() - crc_len - 1;

                divisor = new int[divisor_bits];

                for (int j = 0; j < divisor_bits; j++)
                    divisor[j] = psx.charAt(i+j+1) - '0';

                rem = new int[crc_len];

                for (int j = 0; j < crc.length; j++) {
                    rem[j] = crc[j];
                }

                rem = divide(crc, divisor, rem);

                String res = "";

                int f = 0;
                for (int j = 0; j < rem.length; j++)
                {
                    if (rem[j] != 0)
                    {
                        f = 1;
                        break;
                    }
                }

                if(f == 1) res = "Error";
                else res = "No Error";
                sendbyte = res.getBytes();
                server.send(new DatagramPacket(sendbyte, sendbyte.length, ia, clientport));
                System.out.println("THANK YOU :)");
        }
        catch(IOException e){
            System.out.println(e);
        }
    }//psvm

    static int[] divide(int crc[],int divisor[], int rem[])
    {
        int cur=0;
        while(true)
        {
            for(int i=0;i<divisor.length;i++)
                rem[cur+i] = (crc[cur+i]^divisor[i]);
            while(rem[cur] == 0 && cur != rem.length-1)
                cur++;
            if((rem.length-cur) < divisor.length)
                break;
        }
//        System.out.println(Arrays.toString(rem));
        return rem;
    }
}



