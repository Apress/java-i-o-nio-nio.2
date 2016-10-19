import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.nio.file.attribute.GroupPrincipal;
import java.nio.file.attribute.PosixFilePermission;

import java.util.Set;

public class PFAVDemo
{
   public static void main(String[] args) throws IOException
   {
      if (args.length < 1 || args.length > 2)
      {
         System.err.println("usage: java PFAVDemo path [group]");
         return;
      }
      Path path = Paths.get(args[0]);
      boolean setAttr = false;
      if (args.length == 2)
         setAttr = true;
      System.out.printf("Group: %b%n", 
                        Files.getAttribute(path, "posix:group"));
      @SuppressWarnings("unchecked")
      Set<PosixFilePermission> perms = 
         (Set<PosixFilePermission>)
         Files.getAttribute(path, "posix: permissions");
      for (PosixFilePermission perm: perms)
         System.out.printf("Permission: %s%n", perm);
      if (setAttr)
      {
         GroupPrincipal gp = path.getFileSystem().
                                  getUserPrincipalLookupService().
                                  lookupPrincipalByGroupName(args[1]);
         Files.setAttribute(path, "posix:group", gp);
         System.out.printf("Group: %b%n", 
                           Files.getAttribute(path, "posix:group"));
      }
   }
}