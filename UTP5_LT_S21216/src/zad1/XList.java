package zad1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.stream.Collectors;

public class XList<T> extends ArrayList<T> {

    public XList(){
        super();
    }


    public XList(Collection<T> c){
        super(c);
    }


    public XList(T... arg){
        super(Arrays.asList(arg));
    }

    public static <T> XList<T> of(Collection<T> c){
        return new XList<>(c);
    }

    public static <T> XList<T> of(T... arg){
        return new XList<T>(arg);
    }

    public static <T> XList<T> charsOf(String s){
        List<T> result = new ArrayList<>();
        for (int i = 0; i < s.length(); i++){
            result.add((T) String.valueOf(s.charAt(i)));
        }
        return new XList<T>(result);
    }

    public static <T> XList<T> tokensOf(String s){
        List result = new ArrayList<>(Arrays.asList(s.split("\\s")));
        return new XList<T>(result);
    }

    public static <T> XList<T> tokensOf(String s, String sep){
        List result = new ArrayList<>(Arrays.asList(s.split(sep)));
        return new XList<T>(result);
    }

    public XList<T> union(Collection<T> c){
        List<T> result = new ArrayList<>();
        result.addAll(this);
        result.addAll(c);
        return new XList<>(result);
    }

    public XList<T> union(T... arg){
        List<T> result = new ArrayList<>();
        result.addAll(this);
        result.addAll(Arrays.asList(arg));
        return new XList<>(result);
    }

    public XList<T> diff(Collection<T> c) {
        List<T> result = new ArrayList<>(this);
        result.removeAll(c);
        return new XList<>(result);
    }

    public XList<T> unique(){
        List<T> result = this.stream().distinct().collect(Collectors.toList());
        return new XList<>(result);
    }

    public <R> XList<R> collect(Function<T, R> func){
        List<R> result = new ArrayList<>();
        for (T elem : this)
            result.add(func.apply(elem));
        return new XList<>(result);
    }

    public String join(String s) {
        StringBuilder sb = new StringBuilder();
        for (T elem : this) {
            sb.append(elem.toString()).append(s);
        }
        return sb.toString();
    }

    public String join() {
        StringBuilder sb = new StringBuilder();
        for (T elem : this) {
            sb.append(elem.toString());
        }
        return sb.toString();
    }

    public void forEachWithIndex(BiConsumer<T, Integer> biConsumer){
        for (int i = 0; i < this.size(); i++){
            biConsumer.accept(this.get(i), i);
        }
    }

    public XList<T> combine() {

        return new XList<T>();
    }


}
