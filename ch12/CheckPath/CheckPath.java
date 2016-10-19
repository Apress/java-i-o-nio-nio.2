import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class CheckPath
{
   public static void main(String[] args) throws IOException
   {
      if (args.length < 1 || args.length > 2)
      {
         System.err.println("usage: java CheckPath path1 [path2]");
         return;
      }
      Path path1 = Paths.get(args[0]);
      System.out.printf("Path1: %s%n", path1);
      System.out.printf("Exists: %b%n", Files.exists(path1));
      System.out.printf("Not exists: %b%n", Files.notExists(path1));
      System.out.printf("Is directory: %b%n", Files.notExists(path1));
      System.out.printf("Is executable: %b%n", Files.isExecutable(path1));
      try
      {
         System.out.printf("Hidden: %b%n", Files.isHidden(path1));
      }
      catch (IOException ioe)
      {
         ioe.printStackTrace();
      }
      System.out.printf("Is readable: %b%n", Files.isReadable(path1));
      System.out.printf("Is regular file: %b%n", 
                        Files.isRegularFile(path1));
      System.out.printf("Is writable: %b%n", 
                        Files.isWritable(path1));
      if (args.length == 2)
      {
         Path path2 = Paths.get(args[1]);
         System.out.printf("Path2: %s%n", path2);
         System.out.printf("Is same path: %b%n", 
                           Files.isSameFile(path1, path2));
      }
   }
}