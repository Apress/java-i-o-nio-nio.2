import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class CTFDemo
{
   public static void main(String[] args) throws IOException
   {
      if (args.length != 1)
      {
         System.err.println("usage: java CTFDemo path");
         return;
      }
      Path path = Files.createTempFile(Paths.get(args[0]), "video", null);
      path.toFile().deleteOnExit();
   }
}