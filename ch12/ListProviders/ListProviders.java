import java.nio.file.spi.FileSystemProvider;

import java.util.List;

public class ListProviders
{
   public static void main(String[] args)
   {
      List<FileSystemProvider> providers = 
         FileSystemProvider.installedProviders();
      for (FileSystemProvider provider: providers)
         System.out.println(provider);
   }
}