package zad1.a;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class Main {
    public static void main (String[] args) {
        Buffer buffer = new Buffer(6);
        Producer producer = new Producer(buffer);
        Consumer consumer = new Consumer(buffer);
        ExecutorService es = Executors.newCachedThreadPool();
        es.execute(producer);
        es.execute(consumer);
    }
}
