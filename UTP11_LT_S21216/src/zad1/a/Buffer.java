package zad1.a;


import java.util.LinkedList;
import java.util.Queue;

public class Buffer {
    Queue<Integer> bufferQueue;
    int size;
    int count;

    public Buffer(int size) {
        bufferQueue = new LinkedList<>();
        this.size = size;
    }

    public void put(int n) {
        bufferQueue.add(n);
        count++;
    }

    public int get() {
        int val = bufferQueue.remove();
        return val;
    }

}
