import java.io.IOException;
import java.io.RandomAccessFile;

public class RAFDemo
{
   final static String MSG = "Test";

   public static void main(String[] args)
   {
      try (RandomAccessFile raf = new RandomAccessFile("data", "rw"))
      {
         raf.writeInt(127);
         raf.writeChars(MSG);
         raf.seek(0);
         System.out.println(raf.readInt());
         for (int i = 0; i < MSG.length(); i++)
            System.out.print(raf.readChar());
         System.out.println();
      }
      catch (IOException ioe)
      {
         ioe.printStackTrace();
      }
   }
}