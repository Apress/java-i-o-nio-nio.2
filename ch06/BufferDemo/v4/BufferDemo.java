import java.nio.CharBuffer;

public class BufferDemo
{
   public static void main(String[] args)
   {
      String[] poem = 
      {
         "Roses are red",
         "Violets are blue",
         "Sugar is sweet",
         "And so are you."
      }; 

      CharBuffer buffer = CharBuffer.allocate(50);

      for (int i = 0; i < poem.length; i++)
      {
         // Fill the buffer.
         for (int j = 0; j < poem[i].length(); j++)
            buffer.put(poem[i].charAt(j));

         // Flip the buffer so that its contents can be read.
         buffer.flip();

         // Drain the buffer.
         while (buffer.hasRemaining())
            System.out.print(buffer.get());

         // Empty the buffer to prevent BufferOverflowException.
         buffer.clear();

         System.out.println();
      }
   }
}