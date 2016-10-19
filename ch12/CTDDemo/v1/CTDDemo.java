import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Paths;

public class CTDDemo
{
   public static void main(String[] args) throws IOException
   {
      if (args.length != 1)
      {
         System.err.println("usage: java CTDDemo path");
         return;
      }
      Files.createTempDirectory(Paths.get(args[0]), "images");
   }
}