/**
 *
 *  @author Le Thanh Tomasz S21216
 *
 */

package zad1;


import java.util.ArrayList;
import java.util.List;

public class ListCreator<T> extends  ArrayList<T> { // Uwaga: klasa musi być sparametrtyzowana

    java.util.List<T> lista = new ArrayList<>();

    public static <T> ListCreator<T> collectFrom(List<T> nowaLista){

        return lista;
    }

    public ListCreator<T> when(Selector<T> sel){
        return sel;
    }

    public List<T> mapEvery(Mapper<Integer, Integer> mapper){

    }
}  
