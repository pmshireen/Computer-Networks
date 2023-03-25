import java.io.*;
import java.net.*;
import java.util.*;
public class Echoclient {
public static void main(String[] args)throws Exception
{
Socket c = null;
DataInputStream usr_ip = null;
DataInputStream din = new DataInputStream(System.in);
DataInputStream dout = null;
try{
c = new Socket("127.0.0.1",5678);
usr_ip  = new DataInputStream(c.getInputStream());
dout = new DataOutputStream(c.getOutputStream());
}
catch (IOException e)
{}
if(usr_ip!=null || din!=null || dout!=null)
{
   String unip;
   while((unip = din.readLine())!=null)
  {
     dout.writeBytes(" "+unip);
     dout.writeBytes("\n"); 
     System.out.println("\n Echoed Message");
     System.out.println(usr_ip.readLine());
     System.exit(0);

 
  }
}
  
 }
}