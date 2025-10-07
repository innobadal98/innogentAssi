package oct6Assi5MultiThreading.ConsumerProducer;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("give the size of Bucket");
        CommonTread commonTread = new CommonTread(Integer.parseInt(sc.nextLine()));
        Producer p = new Producer(commonTread, 5, "Producer Tread");
        Consumer c = new Consumer(commonTread, 5, "Consumer Thread");
        p.start();
        c.start();
        try {
            p.join();
            c.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
