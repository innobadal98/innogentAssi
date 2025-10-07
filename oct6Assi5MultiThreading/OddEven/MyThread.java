package oct6Assi5MultiThreading.OddEven;

public class MyThread{
    public boolean isOdd = true;

    public synchronized void evenPrint(int count){
        while(isOdd){
            try{ wait(); }
            catch (InterruptedException e){
                System.out.println(e.getMessage());
            }
        }
        System.out.println(count + " Even (" + Thread.currentThread().getName() + ")");
        isOdd = true;
        notify();
    }

    public synchronized void oddPrint(int count){
        while(!isOdd){
            try {
                wait();
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }
        System.out.println(count + " Odd (" + Thread.currentThread().getName() + ")");
        isOdd = false;
        notify();
    }

}
