import java.nio.ByteBuffer;

import java.nio.charset.Charset;

public class CharsetDemo
{
   public static void main(String[] args)
   {
      String msg = "façade touché";
      String[] csNames =
      {
         "US-ASCII",
         "ISO-8859-1",
         "UTF-8",
         "UTF-16BE",
         "UTF-16LE",
         "UTF-16"
      };

      encode(msg, Charset.defaultCharset());
      for (String csName: csNames)
         encode(msg, Charset.forName(csName));
   }

   static void encode(String msg, Charset cs)
   {
      System.out.println("Charset: " + cs.toString());
      System.out.println("Message: " + msg);

      ByteBuffer buffer = cs.encode(msg);
      System.out.println("Encoded: ");

      for (int i = 0; buffer.hasRemaining(); i++)
      {
         int _byte = buffer.get() & 255;
         char ch = (char) _byte;
         if (Character.isWhitespace(ch) || Character.isISOControl(ch))
            ch = '\u0000';
         System.out.printf("%2d: %02x (%c)%n", i, _byte, ch);
      }
      System.out.println();
   }
}