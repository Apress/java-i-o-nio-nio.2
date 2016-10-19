import java.io.IOException;

import java.nio.channels.CompletionHandler;

import java.nio.charset.Charset;

public class ReadWriteHandler 
   implements CompletionHandler<Integer, Attachment> 
{
   private final static Charset CSUTF8 = Charset.forName("UTF-8");

   @Override
   public void completed(Integer result, Attachment att) 
   {
      if (result == -1) 
      {
         try 
         {
            att.channelClient.close();
            System.out.printf("Stopped listening to client %s%n",
                              att.clientAddr);
         } 
         catch (IOException ioe) 
         {
            ioe.printStackTrace();
         }
         return;
      }

      if (att.isReadMode)
      {
         att.buffer.flip();
         int limit = att.buffer.limit();
         byte bytes[] = new byte[limit];
         att.buffer.get(bytes, 0, limit);
         System.out.printf("Client at %s sends message: %s%n", 
                           att.clientAddr, 
                           new String(bytes, CSUTF8));

         att.isReadMode = false;

         att.buffer.rewind();
         att.channelClient.write(att.buffer, att, this);
      } 
      else 
      {
         att.isReadMode = true;

         att.buffer.clear();
         att.channelClient.read(att.buffer, att, this);
      }
   }

   @Override
   public void failed(Throwable t, Attachment att) 
   {
      System.out.println("Connection with client broken");
   }
}