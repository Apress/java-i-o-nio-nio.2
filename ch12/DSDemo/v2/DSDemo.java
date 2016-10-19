import java.io.IOException;

import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DSDemo
{
   public static void main(String[] args)
   {
      if (args.length != 2)
      {
         System.err.println("usage: java DSDemo dirpath ext");
         return;
      }
      DirectoryStream.Filter<Path> filter;
      filter = new DirectoryStream.Filter<Path>() 
               {
                  @Override
                  public boolean accept(Path path) throws IOException 
                  {
                     return path.toString().endsWith(args[1]);
                  }
               };
      Path path = Paths.get(args[0]);
      try (DirectoryStream<Path> ds = 
              Files.newDirectoryStream(path, filter))
      {
         for (Path p: ds)
            System.out.println(p);
      }
      catch (IOException ioe)
      {
         ioe.printStackTrace();
      }
   }
}