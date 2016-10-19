import java.net.NetworkInterface;

import java.util.Enumeration;

public class EnumNI
{
   public static void main(String[] args) throws Exception
   {
      Enumeration<NetworkInterface> e;
      e = NetworkInterface.getNetworkInterfaces();
      while (e.hasMoreElements())
         System.out.println(e.nextElement());
   }
}