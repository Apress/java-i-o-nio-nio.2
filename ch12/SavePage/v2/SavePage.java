import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
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
      InputStreamReader isr = new InputStreamReader(url.openStream());
      BufferedReader br = new BufferedReader(isr);
      BufferedWriter bw = Files.newBufferedWriter(Paths.get("page.html"));
      String line;
      while ((line = br.readLine()) != null)
      {
         bw.write(line, 0, line.length()); bw.newLine();
      }
      bw.close(); // You must close the file to write data to storage.
   }
}