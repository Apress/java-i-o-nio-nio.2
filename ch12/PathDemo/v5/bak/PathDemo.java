import java.io.IOException;

import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Path;

public class PathDemo
{
   public static void main(String[] args) throws IOException
   {
      FileSystem fsDefault = FileSystems.getDefault();
      Path path1 = fsDefault.getPath("a", "b", "c");
      Path path2 = fsDefault.getPath("a", "b", "c", "d");
      System.out.printf("path1: %s%n", path1.toString());
      System.out.printf("path2: %s%n", path2.toString());
      System.out.printf("path1.equals(path2): %b%n", path1.equals(path2));
      System.out.printf("path1.equals(path2.subpath(0, 3): %b%n",
                        path1.equals(path2.subpath(0, 3)));
      System.out.printf("path1.compareTo(path2): %d%n",
                        path1.compareTo(path2));
      System.out.printf("path1.startsWith(\"x\"): %b%n", 
                        path1.startsWith("x"));
      System.out.printf("path1.startsWith(fsDefault.getPath(\"a\"): %b%n",
                        path1.startsWith(fsDefault.getPath("a")));
      System.out.printf("path2.endsWith(\"d\"): %b%n", 
                        path2.startsWith("d"));
      System.out.printf("path2.endsWith(fsDefault.getPath(\"c\", \"d\"): " +
                        "%b%n", 
                        path2.endsWith(fsDefault.getPath("c", "d")));
      System.out.printf("path2.toUri(): %s%n", path2.toUri());
      Path path3 = fsDefault.getPath(".");
      System.out.printf("path3: %s%n", path3.toString());
      System.out.printf("path3.toRealPath(): %s%n", path3.toRealPath());
   }
}