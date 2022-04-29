/**
 *
 *  @author Le Thanh Tomasz S21216
 *
 */
package zad1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
  public static void main(String[] args) throws IOException {
    List<String> dictionary = Files.readAllLines(Paths.get("unixdict.txt"));
    Collections.sort(dictionary);
    Map<String, List<String>> anagram = dictionary.stream().collect(Collectors.groupingBy(Main::sort));
    int a = anagram.values().stream().max(Comparator.comparing(List::size)).get().size();
    List<List<String>> result = anagram.values().stream().filter(e -> e.size() == a)
            .sorted((Comparator.comparing(o -> o.get(0))))
            .collect(Collectors.toList());
    result.forEach(e -> {
      e.forEach(s -> System.out.print(s + " "));
      System.out.println();});
  }

  public static String sort(String s) {
    char[] tmp = s.toCharArray();
    Arrays.sort(tmp);
    return new String(tmp);
  }
}


