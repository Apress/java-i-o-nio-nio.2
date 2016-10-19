import java.io.IOException;

import java.nio.ByteBuffer;

import java.nio.channels.AsynchronousFileChannel;

import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.concurrent.Future;

public class AFCDemo
{
   public static void main(String[] args) throws Exception
   {
      if (args.length != 1)
      {
         System.err.println("usage: java AFCDemo path");
         return;
      }
      Path path = Paths.get(args[0]);
      AsynchronousFileChannel ch = AsynchronousFileChannel.open(path);
      ByteBuffer buf = ByteBuffer.allocate(1024);
      Future<Integer> result = ch.read(buf, 0);
      while (!result.isDone())
      {
         System.out.println("Sleeping...");
         Thread.sleep(500);
      }
      System.out.println("Finished = " + result.isDone());
      System.out.println("Bytes read = " + result.get());
      ch.close();
   }
}