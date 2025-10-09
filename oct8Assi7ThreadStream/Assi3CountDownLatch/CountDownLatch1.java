package oct8Assi7ThreadStream.Assi3CountDownLatch;
import java.util.concurrent.CountDownLatch;

class Worker implements Runnable{
    String name;
    int timeTaken;
    CountDownLatch latch;

    public Worker(String name, int timeTaken, CountDownLatch latch) {
        this.name = name;
        this.timeTaken = timeTaken;
        this.latch = latch;
    }

    @Override
    public void run() {

        try {
            System.out.println(name + " start the execution");
            Thread.sleep(timeTaken);
            System.out.println(name + " end the execution");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }finally {
            latch.countDown();
        }
    }
}

public class CountDownLatch1 {
    public static void main(String[] args) {
        CountDownLatch latch = new CountDownLatch(3);

        Thread t1 = new Thread(new Worker("ThreadA", 600, latch));
        Thread t2 = new Thread(new Worker("ThreadB", 800, latch));
        Thread t3 = new Thread(new Worker("ThreadC", 2000, latch));

        t1.start();
        t2.start();
        t3.start();

        System.out.println("main thread run after the other thread executed");

        try{
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("all worker thread end the execution");
        System.out.println("this is a main thread");

    }
}
