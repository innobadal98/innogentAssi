package oct8Assi7ThreadStream.Assi2DeadLock;

import java.util.concurrent.locks.ReentrantLock;

public class DeadLock {
    private static final ReentrantLock resource1 = new ReentrantLock();
    private static final ReentrantLock resource2 = new ReentrantLock();

    public static void main(String[] args) {
        Thread threadA = new Thread(()->{
            try{
                resource1.lock();
                System.out.println("Thread A lock resource1");
                Thread.sleep(800);
                resource2.lock();
                System.out.println("Thread A lock resource2");
            }catch (InterruptedException e) {
                throw new RuntimeException(e);
            }finally {
                resource1.unlock();
                resource2.unlock();
            }
        });
        Thread threadB = new Thread(()->{
            try{
                resource2.lock();
                System.out.println("Thread B lock resource2");
                Thread.sleep(800);
                resource1.lock();
                System.out.println("Thread B lock resource1");
            }catch (InterruptedException e) {
                throw new RuntimeException(e);
            }finally {
                resource1.unlock();
                resource2.unlock();
            }
        });

        threadA.start();
        threadB.start();
    }

}
