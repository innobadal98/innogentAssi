package oct8Assi7ThreadStream.Assi7PSvsES;

import java.util.*;
import java.util.concurrent.*;
import java.util.stream.*;

public class Main {
    public static void main(String[] args) throws Exception {


        List<Integer> numbers = IntStream.rangeClosed(1, 200_000).boxed().toList();

        // Normal Stream
        long start1 = System.currentTimeMillis();
        long sum1 = numbers.stream()
                .mapToLong(Main::heavyWork)
                .sum();
        long end1 = System.currentTimeMillis();
        System.out.println("Normal Stream sum: " + sum1 + " | Time: " + (end1 - start1) + " ms");


        // Parallel Stream
        long start2 = System.currentTimeMillis();
        long sum2 = numbers.parallelStream()
                .mapToLong(Main::heavyWork)
                .sum();
        long end2 = System.currentTimeMillis();
        System.out.println("Parallel Stream sum: " + sum2 + " | Time: " + (end2 - start2) + " ms");


        // ExecutorService
        int threads = 4;
        ExecutorService executor = Executors.newFixedThreadPool(threads);

        int chunkSize = numbers.size() / threads;
        List<Future<Long>> futures = new ArrayList<>();

        long start3 = System.currentTimeMillis();

        // Divide work into parts
        for (int i = 0; i < threads; i++) {
            int start = i * chunkSize;
            int end = (i == threads - 1) ? numbers.size() : start + chunkSize;
            List<Integer> part = numbers.subList(start, end);

            futures.add(executor.submit(() ->
                    part.stream().mapToLong(Main::heavyWork).sum()
            ));
        }

        long total = 0;
        for (Future<Long> f : futures) {
            total += f.get();
        }

        long end3 = System.currentTimeMillis();
        executor.shutdown();

        System.out.println("ExecutorService sum: " + total + " | Time: " + (end3 - start3) + " ms");
    }

//   long task ----
    private static long heavyWork(long n) {
        long result = n;
        for (int i = 0; i < 500; i++) {
            result = (result * result) % 999983;
        }
        return result;
    }
}
