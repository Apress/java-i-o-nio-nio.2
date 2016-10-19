import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Paths;

public class Delete
{
   public static void main(String[] args) throws IOException
   {
      if (args.length != 1)
      {
         System.err.println("usage: java Delete path");
         return;
      }
      if (!Files.deleteIfExists(Paths.get(args[0])))
         System.err.printf("%s does not exist%n", args[0]);
   }
}