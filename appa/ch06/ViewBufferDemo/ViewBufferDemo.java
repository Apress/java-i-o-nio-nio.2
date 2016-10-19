import java.nio.ByteBuffer;
import java.nio.CharBuffer;

public class ViewBufferDemo
{
   public static void main(String[] args)
   {
      ByteBuffer bb = ByteBuffer.allocate(6);
      byte zero = 0;
      bb.put(zero).put((byte) 0x6e).put(zero).put((byte) 0x69)
        .put(zero).put((byte) 0x6f);
      bb.rewind();
      CharBuffer cb = bb.asCharBuffer();
      for (int i = 0; i < cb.limit(); i++)
         System.out.print(cb.get());
   }
}