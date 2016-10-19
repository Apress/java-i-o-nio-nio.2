import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Paths;

public class CDDemo
{
   public static void main(String[] args) throws IOException
   {
      if (args.length != 1)
      {
         System.err.println("usage: java CDDemo path");
         return;
      }
      Files.createDirectory(Paths.get(args[0]));
   }
}