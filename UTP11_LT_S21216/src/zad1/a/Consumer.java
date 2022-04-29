package zad1.a;



import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Consumer implements Runnable{
    Buffer buffer;
    Condition bufferEmpty;
    ReentrantLock lock;
    public Consumer(Buffer b) {
        buffer = b;
        lock = new ReentrantLock();
        bufferEmpty = lock.newCondition();
    }

    @Override
    public void run() {
        while(true){
            Random random = new Random();
            try {
                lock.lock();
                while (buffer.count == 0) {
                    bufferEmpty.await();
                }

                System.out.println(buffer.get() + "is out");
                Thread.sleep(random.nextInt(2001));
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                bufferEmpty.signalAll();
                lock.unlock();
            }
        }
    }
}
