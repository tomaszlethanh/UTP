package zad1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;


public class ProgLang {
    Map<String, Collection<String>> langsMap;
    Map<String, Collection<String>> progsMap;

    ProgLang(String fileName){
        try {
            Scanner scanner = new Scanner(new File(fileName));
            langsMap = new LinkedHashMap<>();
            while(scanner.hasNextLine()){
                String[] splitArr = scanner.nextLine().split("\\t");
                List<String> nameList = new ArrayList<>(Arrays.asList(splitArr).subList(1, splitArr.length));
                nameList = nameList.stream().distinct().collect(Collectors.toList());
                langsMap.put(splitArr[0], nameList);
            }
            scanner.close();
        }catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        progsMap = new LinkedHashMap<>();
        for (Map.Entry<String, Collection<String>> e : langsMap.entrySet()){
            String language = e.getKey();
            for (String name : e.getValue()){
                if (progsMap.containsKey(name)){
                    progsMap.get(name).add(language);
                }
                else{
                    Set<String> langSet = new HashSet<>();
                    langSet.add(language);
                    progsMap.put(name, langSet);
                }
            }
        }
    }


    public Map<String, Collection<String>> getLangsMap(){
        return langsMap;
    }


    public Map<String, Collection<String>> getProgsMap(){
        return progsMap;
    }



    public Map<String, Collection<String>> getLangsMapSortedByNumOfProgs(){
        Comparator<Map.Entry<String, Collection<String>>> comparator = Comparator
                .comparing(e -> e.getValue().size(), Comparator.reverseOrder());
        return sorted(getLangsMap(), comparator);
    }


    public Map<String, Collection<String>> getProgsMapSortedByNumOfLangs() {
        Comparator<Map.Entry<String, Collection<String>>> comparator = Comparator
                .comparing(e -> e.getValue().size(), Comparator.reverseOrder());
        return sorted(getProgsMap(), comparator);
    }


    public Map<String, Collection<String>> getProgsMapForNumOfLangsGreaterThan(int n){
        Predicate<Map.Entry<String, Collection<String>>> predicate =  e -> e.getValue().size() > n;
        return filtered(getProgsMap(), predicate);
    }



    public static <K, V> Map<K, V> sorted(Map<K, V> map, Comparator<Map.Entry<K, V>> comparator){
        Map<K, V> returnMap = map.entrySet().stream()
                .sorted(comparator)
                .collect(Collectors.
                        toMap(Map.Entry::getKey, Map.Entry::getValue, (v1, v2) -> v1, LinkedHashMap::new));
        return returnMap;
    }

    public static <K, V> Map<K, V> filtered(Map<K, V> map, Predicate<Map.Entry<K, V>> predicate){
        Map<K, V> returnMap = map.entrySet().stream()
                .filter(predicate)
                .collect(Collectors.
                        toMap(Map.Entry::getKey, Map.Entry::getValue, (v1, v2) -> v1, LinkedHashMap::new));
        return returnMap;
    }

}
