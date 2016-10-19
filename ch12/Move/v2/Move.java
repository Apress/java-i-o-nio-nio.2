import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.StandardCopyOption;

import java.nio.file.attribute.BasicFileAttributes;

public class Move
{
   public static void main(String[] args) throws IOException
   {
      if (args.length != 2)
      {
         System.err.println("usage: java Move srcpath destpath");
         return;
      }

      class MoveVisitor extends SimpleFileVisitor<Path>
      {
         private Path srcPath, dstPath;

         MoveVisitor(Path srcPath, Path dstPath)
         {
            this.srcPath = srcPath;
            this.dstPath = dstPath;
         }

         @Override
         public FileVisitResult postVisitDirectory(Path dir, 
                                                   IOException ioe)
            throws IOException
         {
            if (ioe == null)
               Files.delete(dir);
            else
               throw ioe;
            return FileVisitResult.CONTINUE;           
         }

         @Override
         public FileVisitResult preVisitDirectory(Path dir, 
                                                  BasicFileAttributes attrs)
            throws IOException
         {
            Path targetPath = dstPath.resolve(srcPath.relativize(dir));
            Files.copy(dir, targetPath, StandardCopyOption.REPLACE_EXISTING, 
                       StandardCopyOption.COPY_ATTRIBUTES);
            return FileVisitResult.CONTINUE;           
         }

         @Override
         public FileVisitResult visitFile(Path file, 
                                          BasicFileAttributes attr)
            throws IOException
         {
            Path targetPath = dstPath.resolve(srcPath.relativize(file));
            Files.move(file, targetPath, 
                       StandardCopyOption.REPLACE_EXISTING, 
                       StandardCopyOption.ATOMIC_MOVE);
            return FileVisitResult.CONTINUE;           
         }
      }

      Path src = Paths.get(args[0]);
      Path dst = Paths.get(args[1]);
      Files.walkFileTree(src, new MoveVisitor(src, dst));
   }
}