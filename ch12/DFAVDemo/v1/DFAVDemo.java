import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.nio.file.attribute.DosFileAttributes;

public class DFAVDemo
{
   public static void main(String[] args) throws IOException
   {
      if (args.length != 1)
      {
         System.err.println("usage: java DFAVDemo path");
         return;
      }
      Path path = Paths.get(args[0]);
      DosFileAttributes dfa;
      dfa = Files.readAttributes(path, DosFileAttributes.class);
      System.out.printf("Is archive: %b%n", dfa.isArchive());
      System.out.printf("Is hidden: %b%n", dfa.isHidden());
      System.out.printf("Is readonly: %b%n", dfa.isReadOnly());
      System.out.printf("Is system: %b%n", dfa.isSystem());
   }
}