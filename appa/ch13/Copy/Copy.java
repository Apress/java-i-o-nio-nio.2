import java.io.IOException;

import java.nio.ByteBuffer;

import java.nio.channels.AsynchronousFileChannel;
import java.nio.channels.CompletionHandler;

import java.nio.file.Paths;

import static java.nio.file.StandardOpenOption.*;

public class Copy
{
   final static Thread THDMAIN = Thread.currentThread();

   public static void main(String[] args) throws IOException
   {
      if (args.length != 2)
      {
         System.err.println("usage: java Copy src dest");
         return;
      }

      copy(AsynchronousFileChannel.open(Paths.get(args[0])),
           AsynchronousFileChannel.open(Paths.get(args[1]), WRITE, 
                                        TRUNCATE_EXISTING, CREATE));

      try
      {
         THDMAIN.join();
      }
      catch (InterruptedException ie)
      {
         System.out.println("done");
      }
   }

   public static void copy(AsynchronousFileChannel chSrc, 
                           AsynchronousFileChannel chDest)
   {
      ByteBuffer buffer = ByteBuffer.allocate(8192);

      class ReadCompletionHandler implements CompletionHandler<Integer, 
                                                               Integer>
      {
         @Override
         public void completed(Integer result, Integer pos) 
         {
            if (result == -1) 
            {
               THDMAIN.interrupt();
               return;
            }

            buffer.flip();
            chDest.write(buffer, pos, pos + result,
                         new CompletionHandler<Integer, Integer>()
                         {
                            @Override
                            public void completed(Integer result, 
                                                  Integer newPos)
                            {
                               buffer.compact();     
                               chSrc.read(buffer, newPos, newPos, 
                                          ReadCompletionHandler.this);
                            }

                            @Override
                            public void failed(Throwable t, Integer pos)
                            {
                               System.out.println("write failure");
                               THDMAIN.interrupt();
                            }
                         });
         }

         @Override
         public void failed(Throwable t, Integer pos)
         {
            System.out.println("read failure");
            THDMAIN.interrupt();
         }
      }

      chSrc.read(buffer, 0, 0, new ReadCompletionHandler());
   }
}