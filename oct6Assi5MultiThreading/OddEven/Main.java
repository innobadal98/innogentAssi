package oct6Assi5MultiThreading.OddEven;

public class Main {
    public static void main(String[] args) {

        MyThread commonThread = new MyThread();

        Thread t1 = new Thread(
                ()->{
                    for (int i = 1; i <= 10; i+=2) {
                        commonThread.oddPrint(i);
                    }
                }, "Odd Thread"
        );
        Thread t2 = new Thread(
                () -> {
                    for (int i = 2; i <= 10; i+=2) {
                        commonThread.evenPrint(i);
                    }
                }, "Even Thread"
        );
//        Thread.currentThread().setName("Odd Thread");
        t1.start();
//        Thread.currentThread().setName("Even Thread");
        t2.start();
    }
}
