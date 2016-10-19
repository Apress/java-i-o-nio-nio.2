import java.io.IOException;
import java.io.RandomAccessFile;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;

import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;

public class ChannelDemo
{
   final static int MAXQUERIES = 150000;
   final static int MAXUPDATES = 150000;
   
   final static int RECLEN = 16;

   static ByteBuffer buffer = ByteBuffer.allocate(RECLEN);
   static IntBuffer intBuffer = buffer.asIntBuffer();

   static int counter = 1;

   public static void main(String[] args) throws IOException
   {
      boolean writer = false;
      if (args.length != 0)
         writer = true;
      RandomAccessFile raf = new RandomAccessFile("temp", 
                                                  (writer) ? "rw" : "r");
      FileChannel fc = raf.getChannel();
      if (writer)
         update(fc);
      else
         query(fc);
   }

   static void query(FileChannel fc) throws IOException
   {
      for (int i = 0; i < MAXQUERIES; i++)
      {
         System.out.println("acquiring shared lock");
         FileLock lock = fc.lock(0, RECLEN, true);
         try
         {
            buffer.clear();
            fc.read(buffer, 0);
            int a = intBuffer.get(0);
            int b = intBuffer.get(1);
            int c = intBuffer.get(2);
            int d = intBuffer.get(3);
            System.out.println("Reading: " + a + " " +
                               b + " " + 
                               c + " " +
                               d);
            if (a * 2 != b || a * 3 != c || a * 4 != d)
            {
               System.out.println("error");
               return; 
            }
         }   
         finally
         {
            lock.release();
         }
      }
   }

   static void update(FileChannel fc) throws IOException
   {
      for (int i = 0; i < MAXUPDATES; i++)
      {
         System.out.println("acquiring exclusive lock");
         FileLock lock = fc.lock(0, RECLEN, false);
         try
         {
            intBuffer.clear();
            int a = counter;
            int b = counter * 2;
            int c = counter * 3;
            int d = counter * 4;
            System.out.println("Writing: " + a + " " +
                               b + " " + 
                               c + " " +
                               d);
            intBuffer.put(a);
            intBuffer.put(b);
            intBuffer.put(c);
            intBuffer.put(d);
            counter++;
            buffer.clear();
            fc.write(buffer, 0);
         }   
         finally
         {
            lock.release();
         }
      }
   }
}