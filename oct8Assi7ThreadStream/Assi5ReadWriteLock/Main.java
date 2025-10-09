package oct8Assi7ThreadStream.Assi5ReadWriteLock;

public class Main {
    public static void main(String[] args) {
        CounterClass count = new CounterClass();

        Thread t1 = new Thread(()-> count.readOperation(), "Thread A");
        Thread t2 = new Thread(()-> count.readOperation() , "Thread B");
        Thread t3 = new Thread(()->  count.readOperation(), "Thread C");
        Thread t4 = new Thread(()->  count.readOperation(), "Thread D");


        Thread t5 = new Thread(()-> count.writeOperation(), "Thread E");
        Thread t6 = new Thread(()-> count.writeOperation(), "Thread F");

        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
        t6.start();

    }
}
