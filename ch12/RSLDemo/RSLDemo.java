import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class RSLDemo
{
   public static void main(String[] args) throws IOException
   {
      if (args.length != 1)
      {
         System.err.println("usage: java ISLDemo linkpath");
         return;
      }
      if (!Files.isSymbolicLink(Paths.get(args[0])))
         System.out.println("is not symbolic link");
      else
      {
         Path targetpath = Files.readSymbolicLink(Paths.get(args[0]));
         System.out.println(targetpath);
      }
   }
}