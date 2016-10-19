import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Paths;

public class CLDemo
{
   public static void main(String[] args) throws IOException
   {
      if (args.length != 2)
      {
         System.err.println("usage: java CLDemo linkpath existfilepath");
         return;
      }
      Files.createLink(Paths.get(args[0]), Paths.get(args[1]));
   }
}