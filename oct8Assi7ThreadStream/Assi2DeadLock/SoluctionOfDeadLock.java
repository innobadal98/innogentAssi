package oct8Assi7ThreadStream.Assi2DeadLock;

import java.util.concurrent.locks.ReentrantLock;

public class SoluctionOfDeadLock {
    public static void main(String[] args) {

        ReentrantLock r1 = new ReentrantLock();
        ReentrantLock r2 = new ReentrantLock();

        Runnable task = () -> {
            try{
                r1.lock();
                System.out.println("resource 1 is locked " + Thread.currentThread().getName());
                Thread.sleep(100);
                r2.lock();
                System.out.println("resource 2 is locked " + Thread.currentThread().getName());
            }catch (InterruptedException e) {
                throw new RuntimeException(e);
            }finally {
                r2.unlock();
                r1.unlock();
            }
        };

        Thread t1 = new Thread(task, "ThreadA");
        Thread t2 = new Thread(task, "ThreadB");

        t1.start();
        t2.start();
    }
}
