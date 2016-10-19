import java.io.UnsupportedEncodingException;

public class CharsetDemo
{
   public static void main(String[] args) 
      throws UnsupportedEncodingException
   {
      byte[] encodedMsg = 
      {
         0x66, 0x61, (byte) 0xc3, (byte) 0xa7, 0x61, 0x64, 0x65, 0x20, 0x74,
         0x6f, 0x75, 0x63, 0x68, (byte) 0xc3, (byte) 0xa9
      };
      String s = new String(encodedMsg, "UTF-8");
      System.out.println(s);
      System.out.println();
      byte[] bytes = s.getBytes();
      for (byte _byte: bytes)
         System.out.print(Integer.toHexString(_byte & 255) + " ");
      System.out.println();
   }
}