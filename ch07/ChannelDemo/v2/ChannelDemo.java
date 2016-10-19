import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import java.nio.ByteBuffer;

import java.nio.channels.Channels;
import java.nio.channels.GatheringByteChannel;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.ScatteringByteChannel;

public class ChannelDemo
{
   public static void main(String[] args) throws IOException
   {
      ScatteringByteChannel src;
      FileInputStream fis = new FileInputStream("x.dat");
      src = (ScatteringByteChannel) Channels.newChannel(fis);
      ByteBuffer buffer1 = ByteBuffer.allocateDirect(5);
      ByteBuffer buffer2 = ByteBuffer.allocateDirect(3);
      ByteBuffer[] buffers = { buffer1, buffer2 };
      src.read(buffers);
      buffer1.flip();
      while (buffer1.hasRemaining())
         System.out.println(buffer1.get());
      System.out.println();
      buffer2.flip();
      while (buffer2.hasRemaining())
         System.out.println(buffer2.get());
      buffer1.rewind();
      buffer2.rewind();
      GatheringByteChannel dest;
      FileOutputStream fos = new FileOutputStream("y.dat");
      dest = (GatheringByteChannel) Channels.newChannel(fos);
      buffers[0] = buffer2;
      buffers[1] = buffer1;
      dest.write(buffers);
   }
}