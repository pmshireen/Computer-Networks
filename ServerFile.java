package Networks;


import java.net.*;
import java.io.*;
public class ServerFile {
    public static void main(String[] args) throws IOException{

        try{
            ServerSocket m = new ServerSocket(4112);
            Socket c = null;
            c = m.accept();
            while(true) {
                System.out.println("Server Running");

                DataInputStream din = new DataInputStream(c.getInputStream());

                DataOutputStream dout = new DataOutputStream(c.getOutputStream());
                String str = din.readLine();

                FileReader fr = new FileReader(str);
                BufferedReader b = new BufferedReader(fr);
                String s;
                while ((s = b.readLine()) != null) {
                    System.out.println("Printing put the file contents");
                    System.out.println(str);
                    dout.writeBytes(s + "\n");

                }
                fr.close();

            }

        }catch(IOException e){
            System.out.println(e);

        }
    }
}
