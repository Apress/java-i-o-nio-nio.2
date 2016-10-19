import java.io.IOException;

import java.nio.ByteBuffer;

import java.nio.charset.Charset;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.nio.file.attribute.UserDefinedFileAttributeView;

public class UDAVDemo
{
   public static void main(String[] args) throws IOException
   {
      if (args.length != 2)
      {
         System.err.println("usage: java UDAVDemo path w | l | r | d");
         return;
      }
      Path path = Paths.get(args[0]);
      UserDefinedFileAttributeView udfav =
         Files.getFileAttributeView(path,
                                    UserDefinedFileAttributeView.class);
      switch (args[1].charAt(0))
      {
         case 'W':
         case 'w': udfav.write("file.description", 
                               Charset.defaultCharset().encode("sample"));
                   break;

         case 'L':
         case 'l': for (String name: udfav.list())
                       System.out.println(name);
                   break;

         case 'R':
         case 'r': int size = udfav.size("file.description");
                   ByteBuffer buf = ByteBuffer.allocateDirect(size);
                   udfav.read("file.description", buf);
                   buf.flip();
                   System.out.println(Charset.defaultCharset().decode(buf));
                   break;

         case 'D':
         case 'd': udfav.delete("file.description");
      }
   }
}