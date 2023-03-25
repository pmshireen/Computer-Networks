import java.io.*;
import java.net.*;
import java.util.*;
public class Echoserver {
public static void main(String[] atgs)throws Exception
{
ServerSocket m = null;
Socket c = null;
DataInputStream usr_ip = null;
DataInputStream din = new DataInputStream(System.in);
DataInputStream dout = null;
try{
m = new ServerSocket(5678);
c = m.accept();
usr_ip  = new DataInputStream(c.getInputStream());
dout =  new DataOutputStream(c.getOutputStream());
}
catch (IOException e)
{}
if(usr_ip!=null || c!=null)
{
   String m1;
   while(true)
  {
     System.out.println("message from client");
     m1 = (usr_ip.readLine());
     System.out.println(m1);
     dout.writeBytes(" "+m1);
     dout.writeBytes("\n"); 
     System.exit(0);

 
  }
}
  
 }
}