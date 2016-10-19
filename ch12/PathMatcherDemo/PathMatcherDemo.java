import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.PathMatcher;

public class PathMatcherDemo
{
   public static void main(String[] args)
   {
      if (args.length != 2)
      {
         System.err.println("usage: java PatchMatcherDemo " +
                            "syntax:pattern path");
         return;
      }
      FileSystem fsDefault = FileSystems.getDefault();
      PathMatcher pm = fsDefault.getPathMatcher(args[0]);
      if (pm.matches(fsDefault.getPath(args[1])))
         System.out.printf("%s matches pattern%n", args[1]);
      else
         System.out.printf("%s doesn't match pattern%n", args[1]);
   }
}