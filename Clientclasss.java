package Networks;

import java.io.*;
import java.net.*;

public class Clientclasss {
    public static void main(String[] args) throws IOException {
        try{
            Socket c = new Socket("localhost",4112);

            DataInputStream din = new DataInputStream(c.getInputStream());

            DataOutputStream dout = new DataOutputStream(c.getOutputStream());
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Enter the filrname");
            String str = in.readLine();
            dout.writeBytes(str+"\n");

            System.out.println("Enter the new filename to copy the contents");
            String str2 = in.readLine();
            FileWriter fw = new FileWriter(str);
            char buffer[];

            while(true){
                String str1 = din.readLine();
                if(str1.equals("-1"))
                    break;

                buffer = new char[str1.length()];
                //str.getChars(buffer,0,str1.length(),0);
                fw.write(str2);
            }
            fw.close();



        }catch(IOException e){
            System.out.println(e);
        }

    }
}
