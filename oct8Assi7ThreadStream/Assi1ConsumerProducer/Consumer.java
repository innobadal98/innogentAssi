package oct6Assi5MultiThreading.ConsumerProducer;

public class Consumer extends Thread {
    private CommonTread commonTread;
    private int n;
    private String name;
    public Consumer(CommonTread commonTread, int n, String name){
        super(name);
        this.commonTread = commonTread;
        this.n = n;

    }
    @Override
    public void run() {
        for (int i = 0; i < n; i++) {
            commonTread.consume(i);
            try {
                sleep(800);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
