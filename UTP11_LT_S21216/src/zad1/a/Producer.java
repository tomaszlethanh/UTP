package zad1.a;



import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;


public class Producer implements Runnable {
    Buffer buffer;
    ReentrantLock lock;
    Condition bufferFull;

    public Producer(Buffer b) {
        buffer = b;
        lock = new ReentrantLock();
        bufferFull = lock.newCondition();
    }

    @Override
    public void run() {
        Random random = new Random();
        boolean done = false;
        while(!done) {
            try {
                lock.lock();
                while (buffer.count >= buffer.size) {
                    bufferFull.await();
                }
                int randNum = random.nextInt(2001);
                buffer.put(randNum);
                System.out.println("Added " + randNum + " into buffer");
                bufferFull.signalAll();
                Thread.sleep(randNum);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }
}
