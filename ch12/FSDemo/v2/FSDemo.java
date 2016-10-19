import java.io.IOException;

import java.nio.file.FileStore;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;

public class FSDemo
{
   public static void main(String[] args) throws IOException
   {
      FileSystem fsDefault = FileSystems.getDefault();
      for (FileStore fileStore: fsDefault.getFileStores())
         System.out.printf("Filestore: %s%n", fileStore);
   }
}