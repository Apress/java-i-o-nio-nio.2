import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;

import java.nio.file.attribute.BasicFileAttributes;

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
         @Override
         public FileVisitResult postVisitDirectory(Path dir, 
                                                   IOException ioe)
            throws IOException
         {
            System.out.printf("postVisitDirectory: %s %s%n%n", dir, ioe);
            return super.postVisitDirectory(dir, ioe);
         }

         @Override
         public FileVisitResult preVisitDirectory(Path dir, 
                                                  BasicFileAttributes attrs)
            throws IOException
         {
            System.out.printf("preVisitDirectory: %s%n", dir);
            System.out.printf("   lastModifiedTime: %s%n", 
                              attrs.lastModifiedTime());
            System.out.printf("   size: %d%n%n", attrs.size());
            return super.preVisitDirectory(dir, attrs);
         }

         @Override
         public FileVisitResult visitFile(Path file, 
                                          BasicFileAttributes attrs)
            throws IOException
         {
            System.out.printf("visitFile: %s%n%n", file);
            System.out.printf("   lastModifiedTime: %s%n", 
                              attrs.lastModifiedTime());
            System.out.printf("   size: %d%n%n", attrs.size());
            return super.visitFile(file, attrs);
         }

         @Override
         public FileVisitResult visitFileFailed(Path file, 
                                                IOException ioe)
            throws IOException
         {
            System.out.printf("visitFileFailed: %s %s%n%n", file, ioe);
            return super.visitFileFailed(file, ioe);
         }
      }
      Files.walkFileTree(Paths.get(args[0]), new DoNothingVisitor());
   }
}