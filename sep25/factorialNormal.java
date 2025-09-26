package sep29;

import java.util.Scanner;

public class factorialNormal {
    public static long findFactorial(long n){
//        start value will be one we can not use zero here
        long value = 1 ;
        if(n==0){
            return 1;
        }
        // we run lopp from two because i = 1 we did not need here
        for (int i = 2; i <= n; i++) {
//            modifying value in every integration
            value = value*i;
        }
        return value;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("give the number");
//        taking number from user
       long value = findFactorial(Long.parseLong(sc.nextLine()));
        System.out.println(value);
    }
}
