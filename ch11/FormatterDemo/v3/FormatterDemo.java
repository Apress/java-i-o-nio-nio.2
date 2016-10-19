import java.util.Locale;

public class FormatterDemo
{
   public static void main(String[] args)
   {
      Employee emp = new Employee("John Doe", 1000);
      System.out.printf("[%s]%n", emp);
      System.out.printf(Locale.FRENCH, "[%s]%n", emp);
      System.out.printf("[%S]%n", emp);
      System.out.printf("[%10.3s]%n", emp);
      System.out.printf("[%-10.3s]%n", emp);
      System.out.printf("[%#s]%n", emp);
   }
}