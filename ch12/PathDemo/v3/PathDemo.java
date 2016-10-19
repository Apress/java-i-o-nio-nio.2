import java.nio.file.Path;
import java.nio.file.Paths;

public class PathDemo
{
   public static void main(String[] args)
   {
      Path path = Paths.get("a", "b", "c");
      System.out.printf("Path: %s%n", path.toString());
      System.out.printf("Absolute: %b%n", path.isAbsolute());
      path = path.toAbsolutePath();
      System.out.printf("Path: %s%n", path.toString());
      System.out.printf("Absolute: %b%n", path.isAbsolute());
   }
}