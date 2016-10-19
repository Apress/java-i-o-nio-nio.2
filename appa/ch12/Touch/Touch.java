import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Paths;

import java.nio.file.attribute.FileTime;

import java.time.Instant;

public class Touch
{
   public static void main(String[] args) throws IOException
   {
      if (args.length != 1)
      {
         System.err.println("usage: java Touch path");
         return;
      }
      Files.setLastModifiedTime(Paths.get(args[0]), 
                                FileTime.from(Instant.now()));
   }
}