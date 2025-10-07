package oct6Assi5MultiThreading.ConsumerPoducer;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class CommonTread {
    private Queue<Integer> bucket = new ArrayDeque<>();
    private int capcity;

    public CommonTread(int capacity){
        this.capcity = capacity;
    }


    public synchronized void produce(int count){
        try {
            if(count==capcity){
                System.out.println("Production reached maximum limit");
                wait();
            }
            int iteamsToAdd = Math.min(count, capcity-bucket.size());
            for (int i = 1; i <= iteamsToAdd; i++) {
                bucket.add(i);
                System.out.println("Produced " + i);
            }
            notifyAll();
        }catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public synchronized void consume(int count){
        try{
            if(bucket.isEmpty()){
                System.out.println("buket is empty");
                wait();
            }
            int iteamToConsume = Math.min(count, bucket.size());
            for (int i = 0; i < iteamToConsume; i++) {
                int value = bucket.poll();
                System.out.println("Consume " + value);
            }
            notifyAll();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
