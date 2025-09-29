package sep26;

import java.util.*;

public class ArrayListVsLinkList {
    public static long calculateTimeOfInsertion(List <Integer> list, int  size){
        // insertion
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < size; i++) {
            list.add(i);
        }
        long endTime = System.currentTimeMillis();
        return endTime - startTime;
    }
    public static long calculateTimeOfDeletion(List <Integer> list){
        Iterator itr = list.iterator();
        // delete
        long startTime = System.currentTimeMillis();
        while(itr.hasNext()){
            itr.next();
            itr.remove();
        }
        long endTime = System.currentTimeMillis();
        return endTime - startTime;
    }

    public static void main(String[] args) {
        int sizes [] = {10000, 50000, 100000};
        for(int size : sizes){

            ArrayList <Integer> list = new ArrayList<>();
            long arrayListInsertionTime = calculateTimeOfInsertion(list, size);
            long arrayListdeletionTime = calculateTimeOfDeletion(list);



            LinkedList <Integer> Linklist = new LinkedList<>();
            long linkListInsertionTime = calculateTimeOfInsertion(Linklist, size);
            long linkListdeletionTime = calculateTimeOfDeletion(Linklist);


            System.out.println("arrayListInsertionTime - "+ arrayListInsertionTime);
            System.out.println("linkListInsertionTime - "+ linkListInsertionTime);

            System.out.println("arrayListdeletionTime - "+ arrayListdeletionTime);
            System.out.println("linkListdeletionTime - "+ linkListdeletionTime);
            System.out.println();
        }
    }
}
