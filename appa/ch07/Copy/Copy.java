import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import java.nio.ByteBuffer;

import java.nio.channels.FileChannel;

public class Copy
{
   public static void main(String[] args)
   {
      if (args.length != 2)
      {
         System.err.println("usage: java Copy srcfile dstfile");
         return;
      }
      try (FileInputStream fis = new FileInputStream(args[0]);
           FileChannel fcSrc = fis.getChannel();
           FileOutputStream fos = new FileOutputStream(args[1]);
           FileChannel fcDest = fos.getChannel())
      {
         ByteBuffer buffer = ByteBuffer.allocateDirect(2048);
         while ((fcSrc.read(buffer)) != -1)
         {
            buffer.flip();
            while (buffer.hasRemaining())
               fcDest.write(buffer);
            buffer.clear();
         }
      }
      catch (FileNotFoundException fnfe)
      {
         System.err.println(args[0] + 
                            " could not be opened for input, or " +
                            args[1] + 
                            " could not be created for output");
      }
      catch (IOException ioe)
      {
         System.err.println("I/O error: " + ioe.getMessage());
      }
   }
}