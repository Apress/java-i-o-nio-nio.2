import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Path;

public class PathDemo
{
   public static void main(String[] args)
   {
      FileSystem fsDefault = FileSystems.getDefault();
      Path path = fsDefault.getPath("a", "b", "c");
      System.out.println(path);
      System.out.printf("Absolute: %b%n", path.isAbsolute());
      System.out.printf("Root: %s%n", path.getRoot());
      for (Path root: fsDefault.getRootDirectories())
      {
         path = fsDefault.getPath(root.toString(), "a", "b", "c");
         System.out.println(path);
         System.out.printf("Absolute: %b%n", path.isAbsolute());
         System.out.printf("Root: %s%n", path.getRoot());
      }   
   }
}