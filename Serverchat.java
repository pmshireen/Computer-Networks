package Networks;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.*;
import java.sql.SQLOutput;

public class Serverchat {
    public static void main(String[] args) throws Exception {
        ServerSocket m = null;
        Socket c = null;
        DataInputStream usr_ip = null;
        DataOutputStream dout = null;
        DataInputStream din = new DataInputStream(System.in);
        try {
            System.out.println("Server running");
            m = new ServerSocket(4444);
            c = m.accept();
            usr_ip = new DataInputStream(c.getInputStream());
            dout = new DataOutputStream(c.getOutputStream());
            //DataInputStream din = new DataOutputStream(System.in);
        }
        catch (IOException e) {
            System.out.println(e);
        }
        if (usr_ip != null || c != null)
        {
            String unip;
            while (true)
            {
                System.out.println("Message from client");
                String s1 = usr_ip.readLine();
                System.out.println(s1);

                if(s1.equals("exit")){
                    System.out.println("Server exiting");
                    break;
                }

                System.out.println("Enter your message");
                unip = din.readLine();
                dout.writeBytes(" " + unip);

                dout.writeBytes("\n");
            }
        }//if

        dout.close();
        usr_ip.close();
        c.close();
    }
}
