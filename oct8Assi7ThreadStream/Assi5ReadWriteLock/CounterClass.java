package oct8Assi7ThreadStream.Assi5ReadWriteLock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class CounterClass {
    private int count = 0;
    private final ReadWriteLock lock = new ReentrantReadWriteLock();

    Lock readLock = lock.readLock();
    Lock writeLock = lock.writeLock();

    public void writeOperation(){
        writeLock.lock();
        try {
            Thread.sleep(800);
            count++;
            System.out.println(Thread.currentThread().getName() + " write operation" + " count - " + count);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }finally {
            writeLock.unlock();
        }
    }

    public void readOperation(){
        readLock.lock();
        try {
            Thread.sleep(800);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        try{
            System.out.println(Thread.currentThread().getName() + " read operation " + " count - " + count);
        }finally {
            readLock.unlock();
        }
    }
}
