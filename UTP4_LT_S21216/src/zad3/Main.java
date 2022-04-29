/**
 *
 *  @author Le Thanh Tomasz S21216
 *
 */

package zad3;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*<--
 *  niezbędne importy
 */
public class Main {
  public static void main(String[] args) {

    Function<String,List<String>> flines = e ->{
      try {
        return Files.readAllLines(Paths.get(e));
      } catch (IOException e1) {
        return null;
      }
    };

    Function<List<String>,String> join = e ->{
      StringBuilder sb = new StringBuilder();

      for (String s:e) {
        sb.append(s);
      }

      return sb.toString();
    };

    Function<String,List<Integer>> collectInts = e ->{
      List<Integer> tmpList = new ArrayList();
      Pattern p = Pattern.compile("-?\\d+");
      Matcher m = p.matcher(e);

      while(m.find()) {
        tmpList.add(Integer.parseInt(m.group()));
      }

      return tmpList;
    };

    Function<List<Integer>,Integer> sum = e ->{
      int suma = 0;

      for (Integer i:e)
        suma += i;

      return suma;
    };


    String fname = System.getProperty("user.home") + "/LamComFile.txt";
    InputConverter<String> fileConv = new InputConverter<>(fname);
    List<String> lines = fileConv.convertBy(flines);
    String text = fileConv.convertBy(flines, join);
    List<Integer> ints = fileConv.convertBy(flines, join, collectInts);
    Integer sumints = fileConv.convertBy(flines, join, collectInts, sum);

    System.out.println(lines);
    System.out.println(text);
    System.out.println(ints);
    System.out.println(sumints);

    List<String> arglist = Arrays.asList(args);
    InputConverter<List<String>> slistConv = new InputConverter<>(arglist);  
    sumints = slistConv.convertBy(join, collectInts, sum);
    System.out.println(sumints);

  }
}
