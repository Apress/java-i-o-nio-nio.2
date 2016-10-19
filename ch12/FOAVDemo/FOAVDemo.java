import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.nio.file.attribute.UserPrincipal;

public class FOAVDemo
{
   public static void main(String[] args) throws IOException
   {
      if (args.length != 1)
      {
         System.err.println("usage: java FOAVDemo path");
         return;
      }
      Path path = Paths.get(args[0]);
      System.out.printf("Owner: %s%n", Files.getOwner(path));
      UserPrincipal up = path.getFileSystem().
                              getUserPrincipalLookupService().
                              lookupPrincipalByName("jeff");
      System.out.println(up);
      Files.setOwner(path, up);
      System.out.printf("Owner: %s%n", Files.getOwner(path));
   }
}