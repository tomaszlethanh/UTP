package zad1;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

public class ListCreator<T> { // Uwaga: klasa musi być sparametrtyzowana

    private List<T> lista;

    public ListCreator (List<T> lista){
        this.lista = lista;
    }

    public static<T> ListCreator<T> collectFrom(List<T> nowaLista){
        return new ListCreator<>(nowaLista);
    }


    public ListCreator<T> when(Predicate<T> sel){
        List<T> tmp = new ArrayList<>();
        for (T element : lista){
            if (sel.test(element))
                tmp.add(element);
        }
        lista = tmp;
        return this;
    }


    public <S> List<S> mapEvery(Function<T, S> mapper){
        List<S> tmp = new ArrayList<>();
        for (T element : lista){
            tmp.add(mapper.apply(element));
        }

        return tmp;
    }
}

