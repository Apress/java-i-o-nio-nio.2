import java.io.IOException;

import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DSDemo
{
   public static void main(String[] args) throws IOException
   {
      if (args.length != 1)
      {
         System.err.println("usage: java DSDemo dirpath");
         return;
      }
      Path path = Paths.get(args[0]);
      DirectoryStream<Path> ds = Files.newDirectoryStream(path);
      for (Path p: ds)
         System.out.println(p);
   }
}