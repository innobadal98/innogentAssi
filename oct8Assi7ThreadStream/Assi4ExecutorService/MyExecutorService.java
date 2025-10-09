package oct8Assi7ThreadStream.Assi4ExecutorService;

import java.io.*;
import java.util.*;
import java.util.concurrent.*;

public class MyExecutorService {
    public static void main(String[] args) {


        File folder = new File("C:\\Users\\badal\\IdeaProjects\\javaAssi\\src\\oct8Assi7ThreadStream\\Assi4ExecutorService");


        File[] files = folder.listFiles((dir, name) -> name.endsWith(".txt"));
        if (files == null || files.length == 0) {
            System.out.println("No text files found!");
            return;
        }

        // Create thread pool of 3 threads
        ExecutorService executor = Executors.newFixedThreadPool(3);

        // To store results
        List<Future<Integer>> results = new ArrayList<>();


        for (File file : files) {
            Future<Integer> future = executor.submit(() -> {
                int count = 0;
                try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                    while (br.readLine() != null) {
                        count++;
                    }
                    System.out.println(Thread.currentThread().getName() +
                            " counted " + count + " lines in " + file.getName());
                } catch (IOException e) {
                    System.out.println("Error reading file: " + file.getName());
                }
                return count;
            });
            results.add(future);
        }

        //  Collect results
        int totalLines = 0;
        for (Future<Integer> f : results) {
            try {
                totalLines += f.get(); // Wait for each thread to finish
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


        System.out.println("\nTotal lines in all files = " + totalLines);

        executor.shutdown();
    }
}
