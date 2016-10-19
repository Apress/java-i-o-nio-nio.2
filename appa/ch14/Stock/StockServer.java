import java.io.IOException;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.NetworkInterface;
import java.net.StandardProtocolFamily;
import java.net.StandardSocketOptions;

import java.nio.ByteBuffer;

import java.nio.channels.DatagramChannel;
import java.nio.channels.MembershipKey;

public class StockServer
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
      InetSocketAddress isa = new InetSocketAddress(group, PORT);

      float openPrice = 37.4f;
      float lowPrice = 37.22f;
      float highPrice = 37.48f;
      float closePrice = 37.41f;
      ByteBuffer payload = ByteBuffer.allocate(16);
      while (true)
      {
         // fluctuate by a factor of -0.5 to almost +0.5 
         float fluctuation = (float) (Math.random() - 0.5);

         lowPrice += fluctuation;
         lowPrice = Math.max(lowPrice, -lowPrice);
         highPrice += fluctuation;
         highPrice = Math.max(highPrice, -lowPrice);
         closePrice += fluctuation;
         closePrice = Math.max(closePrice, -lowPrice);

         payload.putFloat(openPrice);
         payload.putFloat(lowPrice);
         payload.putFloat(highPrice);
         payload.putFloat(closePrice);
         payload.flip();
         dc.send(payload, isa);
         payload.clear();

         openPrice = closePrice;
      }
   }
}