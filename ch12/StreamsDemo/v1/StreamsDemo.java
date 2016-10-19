import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.nio.file.attribute.BasicFileAttributes;

import java.util.List;

import java.util.function.BiPredicate;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamsDemo
{
   public static void main(String[] args)
   {
      if (args.length != 2)
      {
         System.err.println("usage: java StreamsDemo dirpath ext");
         return;
      }
      BiPredicate<Path, BasicFileAttributes> predicate = (path, attrs) -> 
         attrs.isRegularFile() && 
         path.getFileName().toString().endsWith(args[1]);
      try (Stream<Path> stream = Files.find(Paths.get(args[0]), 1, 
           predicate)) 
      {
         List<Path> entries = stream.collect(Collectors.toList());
         for (Path entry: entries)
            System.out.println(entry);
      }
      catch (IOException ioe)
      {
         ioe.printStackTrace();
      }
   }
}