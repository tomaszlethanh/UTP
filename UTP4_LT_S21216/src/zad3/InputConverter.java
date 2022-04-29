package zad3;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class InputConverter <T> {

    private T elem;

    public InputConverter(T elem) {
        this.elem = elem;
    }

    public <R> R convertBy(Function... tablicaFunkcji) {
        List fncList = new ArrayList();

        fncList.add(tablicaFunkcji[0].apply(elem));
        for (int i = 1; i < tablicaFunkcji.length; i++){
            fncList.add(tablicaFunkcji[i].apply(fncList.get(i-1)));
        }

        return (R) fncList.get(fncList.size() -1);
    }
}
