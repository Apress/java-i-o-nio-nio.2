import java.io.File;

public class Permissions
{
   public static void main(String[] args)
   {
      if (args.length != 1)
      {
         System.err.println("usage: java Permissions filespec");
         return;
      }
      File file = new File(args[0]);
      System.out.println("Checking permissions for " + args[0]);
      System.out.println("  Execute = " + file.canExecute());
      System.out.println("  Read = " + file.canRead());
      System.out.println("  Write = " + file.canWrite());
   }
}