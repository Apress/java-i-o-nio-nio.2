import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;

import java.nio.file.attribute.BasicFileAttributes;

public class Delete
{
   public static void main(String[] args) throws IOException
   {
      if (args.length != 1)
      {
         System.err.println("usage: java Delete path");
         return;
      }

      class DeleteVisitor extends SimpleFileVisitor<Path>
      {
         @Override
         public FileVisitResult postVisitDirectory(Path dir, 
                                                   IOException ioe)
            throws IOException
         {
            if (ioe == null)
               if (Files.deleteIfExists(dir))
                  System.out.printf("deleted directory %s%n", dir);
               else
                  System.out.printf("couldn't delete directory %s%n", dir);
            else
               throw ioe;
            return FileVisitResult.CONTINUE;           
         }

         @Override
         public FileVisitResult visitFile(Path file, 
                                          BasicFileAttributes attr)
            throws IOException
         {
            if (Files.deleteIfExists(file))
               System.out.printf("deleted regular file %s%n", file);
            else
               System.out.printf("couldn't delete regular file %s%n", file);
            return FileVisitResult.CONTINUE;           
         }
      }

      Files.walkFileTree(Paths.get(args[0]), new DeleteVisitor());
   }
}