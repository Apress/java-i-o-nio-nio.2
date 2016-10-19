import java.io.FileOutputStream;
import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Paths;

public class Copy
{
   public static void main(String[] args) throws IOException
   {
      if (args.length != 2)
      {
         System.err.println("usage: java Copy src dst");
         return;
      }
      Files.copy(Paths.get(args[0]), new FileOutputStream(args[1]));
   }
}