import java.io.BufferedReader;
import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Paths;

public class DumpText
{
   public static void main(String[] args) throws IOException
   {
      if (args.length != 1)
      {
         System.err.println("usage: java DumpText textfilepath");
         return;
      }
      BufferedReader br = Files.newBufferedReader(Paths.get(args[0]));
      String line;
      while ((line = br.readLine()) != null)
         System.out.println(line);
   }
}