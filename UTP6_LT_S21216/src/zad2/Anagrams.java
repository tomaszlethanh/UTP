/**
 *
 *  @author Le Thanh Tomasz S21216
 *
 */

package zad2;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Anagrams {
    String fileName;
    List<String> inputList;

    public Anagrams(String fileName){
        this.fileName = fileName;
        inputList = new ArrayList<>();
        try {
            Scanner sc = new Scanner(new File(fileName));
            while (sc.hasNext()) {
                inputList.add(sc.next());
            }
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }



    public List<List<String>> getSortedByAnQty(){
        List<String> checkList = new ArrayList<>();
        List<List<String>> mainList = new ArrayList<>();
        List<String> sortedWords = new ArrayList<>();

        for (String s : inputList){
            sortedWords.add(sortString(s));
        }

        for (int i = 0; i < sortedWords.size(); i++){
            if (!checkList.contains(inputList.get(i))) {
                List<String> anagramList = new ArrayList<>();
                for (int j = 0; j < sortedWords.size(); j++) {
                    if (sortedWords.get(i).equals(sortedWords.get(j))) {
                        anagramList.add(inputList.get(j));
                        checkList.add(inputList.get(j));
                    }
                }
                Collections.sort(anagramList);
                mainList.add(anagramList);
            }
        }
        mainList.sort((l1, l2) -> l1.get(0).compareTo(l2.get(0)));
        mainList.sort((l1, l2) -> l2.size() - l1.size());
        return mainList;
    }



    public String getAnagramsFor(String word) {
        List<List<String>> tmpArr = new ArrayList<>(this.getSortedByAnQty());
        for (int i = 0; i < tmpArr.size(); i++){
            List<String> innerArr = new ArrayList<>(tmpArr.get(i));
            for (int j = 0; j < innerArr.size(); j++){
                if (word.equals(innerArr.get(j))){
                    innerArr.remove(innerArr.get(j));
                    return "" + word +": " + innerArr.toString();
                }
            }
        }
        return "" + word + ": " + null;
    }



    public static String sortString(String string) {
        char[] tempArr = string.toCharArray();
        Arrays.sort(tempArr);
        return new String(tempArr);
    }

}  
