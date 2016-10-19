import java.io.FileInputStream;
import java.io.IOException;

import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.WritableByteChannel;

public class ChannelDemo
{
   public static void main(String[] args)
   {
      if (args.length != 1)
      {
         System.err.println("usage: java ChannelDemo filespec");
         return;
      }

      try (FileInputStream fis = new FileInputStream(args[0]))
      {
         FileChannel inChannel = fis.getChannel();
         WritableByteChannel outChannel = Channels.newChannel(System.out);
         inChannel.transferTo(0, inChannel.size(), outChannel);
      }
      catch (IOException ioe)
      {
         System.out.println("I/O error: " + ioe.getMessage());
      }
   }
}