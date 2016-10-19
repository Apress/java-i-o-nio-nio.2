import java.io.IOException;

import java.nio.ByteBuffer;

import java.nio.channels.Pipe;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;

public class ChannelDemo
{
   final static int BUFSIZE = 10;
   final static int LIMIT = 3;

   public static void main(String[] args) throws IOException
   {
      final Pipe pipe = Pipe.open();

      Runnable senderTask = 
         new Runnable()
         {
            @Override
            public void run()
            {
               WritableByteChannel src = pipe.sink();
               ByteBuffer buffer = ByteBuffer.allocate(BUFSIZE);
               for (int i = 0; i < LIMIT; i++)
               {
                  buffer.clear();
                  for (int j = 0; j < BUFSIZE; j++)
                     buffer.put((byte) (Math.random() * 256));
                  buffer.flip();
                  try
                  {
                     while (src.write(buffer) > 0);
                  }   
                  catch (IOException ioe)
                  {
                     System.err.println(ioe.getMessage());
                  }
               }
               try
               {
                  src.close();
               }
               catch (IOException ioe)
               {
                  ioe.printStackTrace();
               }
            }
         };

      Runnable receiverTask = 
         new Runnable()
         {
            @Override
            public void run()
            {
               ReadableByteChannel dst = pipe.source();
               ByteBuffer buffer = ByteBuffer.allocate(BUFSIZE);
               try
               {
                  while (dst.read(buffer) >= 0)
                  {
                     buffer.flip();
                     while (buffer.remaining() > 0)
                        System.out.println(buffer.get() & 255);
                     buffer.clear();
                  }
               } 
               catch (IOException ioe)
               {
                  System.err.println(ioe.getMessage());
               }
            }
         };

      Thread sender = new Thread(senderTask);
      Thread receiver = new Thread(receiverTask);
      sender.start();
      receiver.start();
   }
}