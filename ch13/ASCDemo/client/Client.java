import java.io.IOException;

import java.net.InetSocketAddress;

import java.nio.ByteBuffer;

import java.nio.channels.AsynchronousSocketChannel;

import java.nio.charset.Charset;

import java.util.concurrent.ExecutionException;

public class Client
{
   private final static Charset CSUTF8 = Charset.forName("UTF-8");

   private final static int PORT = 9090;

   private final static String HOST = "localhost";

   public static void main(String[] args) 
   {
      AsynchronousSocketChannel channel;
      try
      {
         channel = AsynchronousSocketChannel.open();
      }
      catch (IOException ioe)
      {
         System.err.println("Unable to open client socket channel");
         return;
      }

      try
      {
         channel.connect(new InetSocketAddress(HOST, PORT)).get();
         System.out.printf("Client at %s connected%n", 
                           channel.getLocalAddress());
      }
      catch (ExecutionException | InterruptedException eie)
      {
         System.err.println("Server not responding");
         return;
      }
      catch (IOException ioe)
      {
         System.err.println("Unable to obtain client socket channel's " + 
                            "local address");
         return;
      }

      Attachment att = new Attachment();
      att.channel = channel;
      att.isReadMode = false;
      att.buffer = ByteBuffer.allocate(2048);
      att.mainThd = Thread.currentThread();

      byte[] data = "Hello".getBytes(CSUTF8);
      att.buffer.put(data);
      att.buffer.flip();
      channel.write(att.buffer, att, new ReadWriteHandler());

      try
      {
         att.mainThd.join();
      }
      catch (InterruptedException ie)
      {
         System.out.println("Client terminating");
      }
   }
}