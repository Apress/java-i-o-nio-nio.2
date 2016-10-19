import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Paths;

public class StreamsDemo
{
   public static void main(String[] args) throws IOException
   {
      if (args.length != 1)
      {
         System.err.println("usage: java StreamsDemo textfilepath");
         return;
      }
      Files.lines(Paths.get(args[0])).forEach(System.out::println);
   }
}