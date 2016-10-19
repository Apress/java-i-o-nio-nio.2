import java.io.IOException;
import java.io.RandomAccessFile;

import java.nio.ByteBuffer;

import java.nio.channels.FileChannel;

public class ChannelDemo
{
   public static void main(String[] args) throws IOException
   {
      RandomAccessFile raf = new RandomAccessFile("temp", "rw");
      FileChannel fc = raf.getChannel();
      long pos;
      System.out.println("Position = " + (pos = fc.position()));
      System.out.println("size: " + fc.size());
      String msg = "This is a test message.";
      ByteBuffer buffer = ByteBuffer.allocateDirect(msg.length() * 2);
      buffer.asCharBuffer().put(msg);
      fc.write(buffer);
      fc.force(true);
      System.out.println("position: " + fc.position());
      System.out.println("size: " + fc.size());
      buffer.clear();
      fc.position(pos);
      fc.read(buffer);
      buffer.flip();
      while (buffer.hasRemaining())
         System.out.print(buffer.getChar());
   }
}