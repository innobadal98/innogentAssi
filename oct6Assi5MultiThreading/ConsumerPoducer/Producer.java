package oct6Assi5MultiThreading.ConsumerPoducer;

public class Producer extends Thread {
    private CommonTread commonTread;
    private int n;

    public Producer(CommonTread commonTread, int n, String name) {
        super(name);
        this.commonTread = commonTread;
        this.n = n;
    }

    @Override
    public void run() {
        for (int i = 0; i < n; i++) {
            commonTread.produce(i);
            try{
                sleep(800);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
