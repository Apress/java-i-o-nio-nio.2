public class FormatterDemo
{
   public static void main(String[] args)
   {
      System.out.printf("%04X%n", 478);
      System.out.printf("Current date: %1$tb %1$te, %1$tY%n", 
                        System.currentTimeMillis());
   }
}