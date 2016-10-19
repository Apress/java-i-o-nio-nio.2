import java.io.IOException;
import java.io.RandomAccessFile;

public class PartsDB
{
   public final static int PNUMLEN = 20;
   public final static int DESCLEN = 30;
   public final static int QUANLEN = 4;
   public final static int COSTLEN = 4;

   private final static int RECLEN = 2 * PNUMLEN + 2 * DESCLEN + QUANLEN + 
                                     COSTLEN;
   private RandomAccessFile raf;

   public PartsDB(String path) throws IOException
   {
      raf = new RandomAccessFile(path, "rw");
   }

   public void append(String partnum, String partdesc, int qty, int ucost)
      throws IOException
   {
      raf.seek(raf.length());
      write(partnum, partdesc, qty, ucost);
   }

   public void close()
   {
      try
      {
         raf.close();
      }
      catch (IOException ioe)
      {
         System.err.println(ioe);
      }
   }

   public int numRecs() throws IOException
   {
      return (int) raf.length() / RECLEN;
   }

   public Part select(int recno) throws IOException
   {
      if (recno < 0 || recno >= numRecs())
         throw new IllegalArgumentException(recno + " out of range");
      raf.seek(recno * RECLEN);
      return read();
   }

   public void update(int recno, String partnum, String partdesc, int qty,
                      int ucost) throws IOException
   {
      if (recno < 0 || recno >= numRecs())
         throw new IllegalArgumentException(recno + " out of range");
      raf.seek(recno * RECLEN);
      write(partnum, partdesc, qty, ucost);
   }

   private Part read() throws IOException
   {
      StringBuffer sb = new StringBuffer();
      for (int i = 0; i < PNUMLEN; i++)
         sb.append(raf.readChar());
      String partnum = sb.toString().trim();
      sb.setLength(0);
      for (int i = 0; i < DESCLEN; i++)
         sb.append(raf.readChar());
      String partdesc = sb.toString().trim();
      int qty = raf.readInt();
      int ucost = raf.readInt();
      return new Part(partnum, partdesc, qty, ucost);
   }

   private void write(String partnum, String partdesc, int qty, int ucost)
      throws IOException
   {
      StringBuffer sb = new StringBuffer(partnum);
      if (sb.length() > PNUMLEN)
         sb.setLength(PNUMLEN);
      else
      if (sb.length() < PNUMLEN)
      {
         int len = PNUMLEN - sb.length();
         for (int i = 0; i < len; i++)
            sb.append(" ");
      }
      raf.writeChars(sb.toString());
      sb = new StringBuffer(partdesc);
      if (sb.length() > DESCLEN)
         sb.setLength(DESCLEN);
      else
      if (sb.length() < DESCLEN)
      {
         int len = DESCLEN - sb.length();
         for (int i = 0; i < len; i++)
            sb.append(" ");
      }
      raf.writeChars(sb.toString());
      raf.writeInt(qty);
      raf.writeInt(ucost);
   }

   public static class Part
   {
      private String partnum;
      private String desc;
      private int qty;
      private int ucost;

      public Part(String partnum, String desc, int qty, int ucost)
      {
         this.partnum = partnum;
         this.desc = desc;
         this.qty = qty;
         this.ucost = ucost;
      }

      String getDesc()
      {
         return desc;
      }

      String getPartnum()
      {
         return partnum;
      }

      int getQty()
      {
         return qty;
      }

      int getUnitCost()
      {
         return ucost;
      }
   }
}