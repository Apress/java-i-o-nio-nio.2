import java.nio.charset.Charset;

import java.util.SortedMap;

public class AvailCharsets
{
   public static void main(String[] args)
   {
      SortedMap<String, Charset> acs = Charset.availableCharsets();
      System.out.println(acs);   
   }
}