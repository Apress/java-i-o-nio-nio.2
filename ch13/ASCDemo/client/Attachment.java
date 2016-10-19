import java.nio.ByteBuffer;

import java.nio.channels.AsynchronousSocketChannel;

public class Attachment 
{
   public AsynchronousSocketChannel channel;
   public boolean isReadMode;
   public ByteBuffer buffer;
   public Thread mainThd;
}