import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Paths;

public class CFDemo
{
   public static void main(String[] args) throws IOException
   {
      if (args.length != 1)
      {
         System.err.println("usage: java CFDemo path");
         return;
      }
      Files.createFile(Paths.get(args[0]));
   }
}