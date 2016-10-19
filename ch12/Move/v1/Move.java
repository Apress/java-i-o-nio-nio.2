import java.io.IOException;

import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.Files;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Move
{
   public static void main(String[] args)
   {
      if (args.length != 2)
      {
         System.err.println("usage: java Move source target");
         return;
      }
      Path source = Paths.get(args[0]);
      Path target = Paths.get(args[1]);
      try 
      {
         Files.move(source, target);
      } 
      catch (FileAlreadyExistsException faee) 
      {
         System.err.printf("%s: file already exists%n", target);
      } 
      catch (DirectoryNotEmptyException dnee) 
      {
         System.err.printf("%s: not empty%n", target);
      } 
      catch (IOException ioe)
      {
         System.err.printf("I/O error: %s%n", ioe.getMessage());
      }
   }
}