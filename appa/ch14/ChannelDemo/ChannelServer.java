import java.io.IOException;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.NetworkInterface;
import java.net.StandardProtocolFamily;
import java.net.StandardSocketOptions;

import java.nio.ByteBuffer;

import java.nio.channels.DatagramChannel;
import java.nio.channels.MembershipKey;

public class ChannelServer
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

      int i = 0;
      while (true)
      {
         ByteBuffer bb = ByteBuffer.wrap(("line " + i).getBytes());
         dc.send(bb, new InetSocketAddress(group, PORT));
         i++;
      }
   }
}