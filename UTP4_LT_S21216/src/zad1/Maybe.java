package zad1;

import java.util.NoSuchElementException;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class Maybe <T> {

    T x;

    public Maybe(T x){
        this.x = x;
    }

    public Maybe(){}

    public static <T> Maybe<T> of(T x){
        return new Maybe<>(x);
    }

    public void ifPresent(Consumer<T> cons){
        if (isPresent())
            cons.accept(x);
    }

    public <S> Maybe<S> map(Function<T, S> func){
        if(isPresent())
            return new Maybe<>(func.apply(x));
        return new Maybe<>();
    }

    public T get(){
        if (!isPresent())
            throw new NoSuchElementException("maybe is empty");
        return x;
    }

    public boolean isPresent(){
        return x != null;
    }

    public T orElse(T defVal){
        if (isPresent())
            return x;
        else
            return defVal;
    }

    public Maybe<T> filter(Predicate<T> pred){
        if (pred.test(x) || !isPresent())
            return this;
        return new Maybe<>();
    }

    @Override
    public String toString() {
        if(isPresent())
            return "Maybe has value " + x;
        return "Maybe is empty";
    }
}
