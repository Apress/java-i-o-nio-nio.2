import java.io.IOException;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class DGServer
{
   final static int PORT = 10000;

   public static void main(String[] args) throws SocketException
   {
      System.out.println("Server is starting");
      DatagramSocket dgs = new DatagramSocket(PORT);
      try
      {
         System.out.println("Send buffer size = " + 
                            dgs.getSendBufferSize());
         System.out.println("Receive buffer size = " +
                            dgs.getReceiveBufferSize());
         byte[] data = new byte[100];
         DatagramPacket dgp = new DatagramPacket(data, data.length);
         while (true)
         {
            dgs.receive(dgp);
            System.out.println(new String(data));
            dgs.send(dgp);
         }
      }
      catch (IOException ioe)
      {
         System.err.println("I/O error: " + ioe.getMessage());
      }
   }
}