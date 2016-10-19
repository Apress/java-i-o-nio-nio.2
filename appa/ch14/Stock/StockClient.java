import java.io.IOException;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.NetworkInterface;
import java.net.StandardProtocolFamily;
import java.net.StandardSocketOptions;

import java.nio.ByteBuffer;

import java.nio.channels.DatagramChannel;
import java.nio.channels.MembershipKey;

public class StockClient
{
   final static int PORT = 9999;

   public static void main(String[] args) throws IOException
   {
      NetworkInterface ni;
      ni = NetworkInterface.getByInetAddress(InetAddress.getLocalHost());
      DatagramChannel dc;
      dc = DatagramChannel.open(StandardProtocolFamily.INET)
                          .setOption(StandardSocketOptions.SO_REUSEADDR, 
                                     true)
                          .bind(new InetSocketAddress(PORT))
                          .setOption(StandardSocketOptions.IP_MULTICAST_IF, 
                                     ni);
      InetAddress group = InetAddress.getByName("239.255.0.1");
      MembershipKey key = dc.join(group, ni);

      ByteBuffer response = ByteBuffer.allocate(16);
      while (true)
      {
         dc.receive(response);
         response.flip();
         System.out.println("Open price: " + response.getFloat());
         System.out.println("Low price: " + response.getFloat());
         System.out.println("High price: " + response.getFloat());
         System.out.println("Close price: " + response.getFloat());
         response.clear();
      }
   }
}