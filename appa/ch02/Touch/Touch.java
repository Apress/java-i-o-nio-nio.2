import java.io.File;

import java.util.Date;

public class Touch
{
   public static void main(String[] args)
   {
      if (args.length != 1)
      {
         System.err.println("usage: java Touch path");
         return;
      }
      new File(args[0]).setLastModified(new Date().getTime());
   }
}