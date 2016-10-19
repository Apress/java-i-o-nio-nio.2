import java.net.NetworkInterface;
import java.net.SocketException;

import java.util.Collections;
import java.util.Enumeration;

public class NetInfo
{
   public static void main(String[] args) throws SocketException
   {
      Enumeration<NetworkInterface> eni;
      eni = NetworkInterface.getNetworkInterfaces();
      for (NetworkInterface ni: Collections.list(eni))
      {
         System.out.println("Name = " + ni.getName());
         System.out.println("Display Name = " + ni.getDisplayName());
         System.out.println("Loopback = " + ni.isLoopback());
         System.out.println("Up and running = " + ni.isUp());
         System.out.println("MTU = " + ni.getMTU());
         System.out.println("Supports multicast = " +
                            ni.supportsMulticast());
         System.out.println("Sub-interfaces");
         Enumeration<NetworkInterface> eni2;
         eni2 = ni.getSubInterfaces();
         for (NetworkInterface ni2: Collections.list(eni2))
            System.out.println("   " + ni2);
         System.out.println();
      }
   }
}