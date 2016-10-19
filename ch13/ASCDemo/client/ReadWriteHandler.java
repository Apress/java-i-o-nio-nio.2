import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

import java.nio.channels.CompletionHandler;

import java.nio.charset.Charset;

public class ReadWriteHandler implements CompletionHandler<Integer, Attachment> 
{
   private final static Charset CSUTF8 = Charset.forName("UTF-8");

   private BufferedReader conReader = 
      new BufferedReader(new InputStreamReader(System.in));

   @Override
   public void completed(Integer result, Attachment att) 
   {
      if (att.isReadMode) 
      {
         att.buffer.flip();
         int limit = att.buffer.limit();
         byte[] bytes = new byte[limit];
         att.buffer.get(bytes, 0, limit);
         String msg = new String(bytes, CSUTF8);
         System.out.printf("Server responded: %s%n", msg);

         try 
         {
            msg = "";
            while (msg.length() == 0)
            {
               System.out.print("Enter message (\"end\" to quit): ");
               msg = conReader.readLine();
            }
            if (msg.equalsIgnoreCase("end")) 
            {
               att.mainThd.interrupt();
               return;
            }
         } 
         catch (IOException ioe) 
         {
            System.err.println("Unable to read from console");
         }

         att.isReadMode = false;
         att.buffer.clear();
         byte[] data = msg.getBytes(CSUTF8);
         att.buffer.put(data);
         att.buffer.flip();
         att.channel.write(att.buffer, att, this);
      }
      else 
      {
         att.isReadMode = true;

         att.buffer.clear();
         att.channel.read(att.buffer, att, this);
      }
   }

   @Override
   public void failed(Throwable t, Attachment att) 
   {
      System.err.println("Server not responding");
      System.exit(1);
   }
}