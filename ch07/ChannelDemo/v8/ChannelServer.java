import java.io.IOException;

import java.net.InetSocketAddress;
import java.net.SocketAddress;

import java.nio.ByteBuffer;

import java.nio.channels.DatagramChannel;

public class ChannelServer
{
   final static int PORT = 9999;

   public static void main(String[] args) throws IOException
   {
      System.out.println("server starting and listening on port " +
                         PORT + " for incoming requests...");
      DatagramChannel dcServer = DatagramChannel.open();
      dcServer.socket().bind(new InetSocketAddress(PORT));
      ByteBuffer symbol = ByteBuffer.allocate(4);
      ByteBuffer payload = ByteBuffer.allocate(16);
      while (true)
      {
         payload.clear();
         symbol.clear();
         SocketAddress sa = dcServer.receive(symbol);
         if (sa == null)
            return;
         System.out.println("Received request from " + sa);
         String stockSymbol = new String(symbol.array(), 0, 4);
         System.out.println("Symbol: " + stockSymbol);
         if (stockSymbol.toUpperCase().equals("MSFT"))
         {
            payload.putFloat(0, 37.40f); // open share price
            payload.putFloat(4, 37.22f); // low share price
            payload.putFloat(8, 37.48f); // high share price
            payload.putFloat(12, 37.41f); // close share price
         }
         else
         {
            payload.putFloat(0, 0.0f);
            payload.putFloat(4, 0.0f);
            payload.putFloat(8, 0.0f);
            payload.putFloat(12, 0.0f);
         }
         dcServer.send(payload, sa);
      }
   }
}