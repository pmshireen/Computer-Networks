package Networks;

import java.io.*;
import java.net.*;

public class FTPClient{

    public static void main(String[] args)throws IOException{
        Socket obj = new Socket("localhost",4532);
        try{

            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

            DataInputStream din = new DataInputStream(obj.getInputStream());

            DataOutputStream dout = new DataOutputStream (obj.getOutputStream());

            System.out.println("Enter the filename");
            String str = br.readLine();
            dout.writeBytes(str+'\n');

            System.out.println("Enter the filename to  copy the contents");
            String str2 = br.readLine();
            String s;

            FileWriter f = new FileWriter(str2);
            char buffer[];
            while(true){

                String str1 = din.readLine();
                System.out.println(str1);
                if(str1.equals("-1"))

                    break;

                buffer = new char[str1.length()];
                str1.getChars(0,str1.length(),buffer,0);
                f.write(buffer);
            }//while
            f.close();
            obj.close();

        }//try
        catch(IOException  e){System.out.println(e);}

    }//psvm
}//public
