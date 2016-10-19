import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Paths;

import java.util.List;

public class DumpText
{
   public static void main(String[] args) throws IOException
   {
      if (args.length != 1)
      {
         System.err.println("usage: java DumpText textfilepath");
         return;
      }
      List<String> lines = Files.readAllLines(Paths.get(args[0]));
      for (String line: lines)
         System.out.println(line);
   }
}