import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

public class PipedStreamsDemo
{
   final static int LIMIT = 10;

   public static void main(String[] args) throws IOException
   {
      final PipedOutputStream pos = new PipedOutputStream();
      final PipedInputStream pis = new PipedInputStream(pos);
      Runnable senderTask = () -> {
                                     try
                                     {
                                        for (int i = 0 ; i < LIMIT; i++)
                                           pos.write((byte) 
                                                     (Math.random() * 256));
                                     }
                                     catch (IOException ioe)
                                     {
                                        ioe.printStackTrace();
                                     }
                                     finally
                                     {
                                        try
                                        {
                                           pos.close();
                                        }
                                        catch (IOException ioe)
                                        {
                                           ioe.printStackTrace();
                                        }
                                     }
                                  };
      Runnable receiverTask = () -> {
                                       try
                                       {
                                          int b;
                                          while ((b = pis.read()) != -1)
                                             System.out.println(b);
                                       }
                                       catch (IOException ioe)
                                       {
                                          ioe.printStackTrace();
                                       }
                                       finally
                                       {
                                          try
                                          {
                                             pis.close();
                                          }
                                          catch (IOException ioe)
                                          {
                                             ioe.printStackTrace();
                                          }
                                       }
                                    };
      Thread sender = new Thread(senderTask);
      Thread receiver = new Thread(receiverTask);
      sender.start();
      receiver.start();
   }
}