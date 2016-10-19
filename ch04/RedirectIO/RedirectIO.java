import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintStream;

public class RedirectIO
{
   public static void main(String[] args) throws IOException
   {
      if (args.length != 3)
      {
         System.err.println("usage: java RedirectIO stdinfile " + 
                            "stdoutfile stderrfile");
         return;
      }

      System.setIn(new FileInputStream(args[0]));
      System.setOut(new PrintStream(args[1]));
      System.setErr(new PrintStream(args[2]));

      int ch;
      while ((ch = System.in.read()) != -1)
         System.out.print((char) ch);

      System.err.println("Redirected error output");
   }
}