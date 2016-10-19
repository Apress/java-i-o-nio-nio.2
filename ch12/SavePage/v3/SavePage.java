import java.io.IOException;

import java.net.URL;

import java.nio.file.Files;
import java.nio.file.Paths;

public class SavePage
{
   public static void main(String[] args) throws IOException
   {
      if (args.length != 1)
      {
         System.err.println("usage: java SavePage url");
         return;
      }
      URL url = new URL(args[0]);
      Files.copy(url.openStream(), Paths.get("page.html"));
   }
}