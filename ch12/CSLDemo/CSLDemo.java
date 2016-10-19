import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Paths;

public class CSLDemo
{
   public static void main(String[] args) throws IOException
   {
      if (args.length != 2)
      {
         System.err.println("usage: java CSLDemo linkpath targetpath");
         return;
      }
      Files.createSymbolicLink(Paths.get(args[0]), Paths.get(args[1]));
   }
}