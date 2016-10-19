import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Split
{
   static final int FILESIZE = 1400000;
   static byte[] buffer = new byte[FILESIZE];

   public static void main(String[] args)
   {
      if (args.length != 1)
      {
         System.err.println("usage: java Split path");
         return;
      }
      File file = new File(args[0]);
      long length = file.length();
      int nWholeParts = (int) (length / FILESIZE);
      int remainder = (int) (length % FILESIZE);
      System.out.printf("Splitting %s into %d parts%n", args[0],
                        (remainder == 0) ? nWholeParts : nWholeParts + 1);
      BufferedInputStream bis = null;
      BufferedOutputStream bos = null;
      try
      {
         FileInputStream fis = new FileInputStream(args[0]);
         bis = new BufferedInputStream(fis);
         for (int i = 0; i < nWholeParts; i++)
         {
            bis.read(buffer);
            System.out.println("Writing part " + i);
            FileOutputStream fos = new FileOutputStream("part" + i);
            bos = new BufferedOutputStream(fos);
            bos.write(buffer);
            bos.close();
            bos = null;
         }
         if (remainder != 0)
         {
            int br = bis.read(buffer);
            if (br != remainder)
            {
               System.err.println("Last part mismatch: expected " + 
                                  remainder + " bytes");
               System.exit(0);
            }
            System.out.println("Writing part " + nWholeParts);
            FileOutputStream fos = new FileOutputStream("part" + 
                                                        nWholeParts);
            bos = new BufferedOutputStream(fos);
            bos.write(buffer, 0, remainder);
         }
      }
      catch (IOException ioe)
      {
         ioe.printStackTrace();
      }
      finally
      {
         if (bis != null)
            try
            {
               bis.close();
            }
            catch (IOException ioe)
            {
               assert false; // shouldn't happen in this context
            }
         if (bos != null)
            try
            {
               bos.close();
            }
            catch (IOException ioe)
            {
               assert false; // shouldn't happen in this context
            }
      }
   }
}