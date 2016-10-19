import java.util.Formattable;
import java.util.FormattableFlags;
import java.util.Formatter;
import java.util.Locale;

public class Employee implements Formattable
{
   private String name;

   private int empno;

   public Employee(String name, int empno)
   {
      this.name = name;
      this.empno = empno;
   }

   @Override
   public void formatTo(Formatter formatter, int flags, int width, 
                        int precision)
   {
      StringBuilder sb = new StringBuilder();

      String output = this.name;
      if (formatter.locale().equals(Locale.FRENCH) && 
          name.equals("John Doe"))
         output = "Jean Dupont";
      output += ": " + empno;
      if (((flags & FormattableFlags.UPPERCASE) == 
          FormattableFlags.UPPERCASE))
         output = output.toUpperCase();

      boolean alternate = (flags & FormattableFlags.ALTERNATE) == 
                          FormattableFlags.ALTERNATE;
      alternate |= (precision >= 0 && precision < 8);
      if (alternate)
         output = "" + empno;

      if (precision == -1 || output.length() <= precision)
         sb.append(output);
      else
         sb.append(output.substring(0, precision - 1)).append('*');

      int len = sb.length();
      if (len < width) 
      {
         boolean leftJustified = (flags & FormattableFlags.LEFT_JUSTIFY) 
                                 == FormattableFlags.LEFT_JUSTIFY;
         for (int i = 0; i < width - len; i++) 
            if (leftJustified)
               sb.append(' ');
            else
               sb.insert(0, ' ');
      }

      formatter.format(sb.toString());
   }

   @Override
   public String toString()
   {
      return name + ": " + empno;
   }
}