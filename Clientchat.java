package Networks;

import java.io.*;
import java.net.*;
public class Clientchat
{
    public static  void main(String args[]) throws Exception
    {
        Socket c = null;
        DataInputStream usr_inp = null;
        DataInputStream din = new DataInputStream(System.in);
        DataOutputStream dout = null;
        try
        {
            c = new Socket("127.0.0.1",4444);
            usr_inp = new DataInputStream(c.getInputStream());
            dout = new DataOutputStream(c.getOutputStream());
        }
        catch(IOException e)
        {
            System.out.println(e);
        }
        if(c!=null||usr_inp!=null||dout!=null)
        {
            String unip;
            System.out.println("Enter the message for server");
            while((unip = din.readLine())!=null)
            {

                dout.writeBytes(""+unip);
                dout.writeBytes("\n");
                if(unip.equals("exit")) break;
                System.out.println("reply");

                System.out.println(usr_inp.readLine());
                System.out.println("Enter your message");
            }

        }

        din.close();
        usr_inp.close();
        c.close();
    }
}
