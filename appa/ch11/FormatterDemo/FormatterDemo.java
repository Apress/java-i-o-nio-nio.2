import java.util.Formatter;

public class FormatterDemo
{
   public static void main(String[] args)
   {
      StringBuilder output = new StringBuilder();
      Formatter formatter = new Formatter(output);
      formatter.format("%d", 123);
      System.out.println(formatter.toString());
      output.setLength(0);
      formatter.format("%x", 123);
      System.out.println(formatter.toString());
      output.setLength(0);
      formatter.format("%c", 'X');
      System.out.println(formatter.toString());
      output.setLength(0);
      formatter.format("%f", 0.1);
      System.out.println(formatter.toString());
      output.setLength(0);
      formatter.format("%s%n", "Hello, World");
      System.out.println(formatter.toString());
      output.setLength(0);
      formatter.format("%10.2f", 98.375);
      System.out.println(formatter.toString());
      output.setLength(0);
      formatter.format("%05d", 123);
      System.out.println(formatter.toString());
      output.setLength(0);
      formatter.format("%1$d %1$d", 123);
      System.out.println(formatter.toString());
      output.setLength(0);
      formatter.format("%d %d", 123);
      System.out.println(formatter.toString());
      output.setLength(0);
      formatter.close();
   }
}