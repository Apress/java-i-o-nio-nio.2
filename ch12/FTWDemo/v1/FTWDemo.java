import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;

public class FTWDemo
{
   public static void main(String[] args) throws IOException
   {
      if (args.length != 1)
      {
         System.err.println("usage: java FTWDemo path");
         return;
      }      
      class DoNothingVisitor extends SimpleFileVisitor<Path>
      {
      }
      Files.walkFileTree(Paths.get(args[0]), new DoNothingVisitor());
   }
}