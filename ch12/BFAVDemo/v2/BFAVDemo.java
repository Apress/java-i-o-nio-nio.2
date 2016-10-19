import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.nio.file.attribute.FileTime;

import java.time.Instant;

public class BFAVDemo
{
   public static void main(String[] args) throws IOException
   {
      if (args.length < 1 || args.length > 2)
      {
         System.err.println("usage: java BFAVDemo path [set]");
         return;
      }
      Path path = Paths.get(args[0]);
      boolean setAttr = false;
      if (args.length == 2)
         setAttr = true;
      System.out.printf("Creation time: %s%n", 
                        Files.getAttribute(path, "creationTime"));
      System.out.printf("File key: %s%n", 
                        Files.getAttribute(path, "fileKey"));
      System.out.printf("Is directory: %b%n", 
                        Files.getAttribute(path, "isDirectory"));
      System.out.printf("Is other: %b%n", 
                        Files.getAttribute(path, "isOther"));
      System.out.printf("Is regular file: %b%n", 
                        Files.getAttribute(path, "isRegularFile"));
      System.out.printf("Is symbolic link: %b%n", 
                        Files.getAttribute(path, "isSymbolicLink"));
      System.out.printf("Last access time: %s%n", 
                        Files.getAttribute(path, "lastAccessTime"));
      System.out.printf("Last modified time: %s%n", 
                        Files.getAttribute(path, "lastModifiedTime"));
      System.out.printf("Size: %d%n", Files.getAttribute(path, "size"));
      if (setAttr)
      {
         Files.setAttribute(path, "lastModifiedTime", 
                            FileTime.from(Instant.now().plusSeconds(60)));
         System.out.printf("Last modified time: %s%n", 
                           Files.getAttribute(path, "lastModifiedTime"));
      }
   }
}