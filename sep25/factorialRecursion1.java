package sep29;

import java.math.BigInteger;
import java.util.Scanner;

public class factorialRecursion1 {
    // Recursive method to calculate factorial using BigInteger
    // Method code itself is stored in the Method Area.
    // Each call creates a new stack frame.
    public static BigInteger factorial(BigInteger n){
//        if value is Zero or it return value one
        if(n.equals(BigInteger.ZERO)||n.equals(BigInteger.ONE)){
            return BigInteger.ONE;
        }
        // it creates new stack frame until n=0;
        // n = n*factorial(n-1)
        return n.multiply(factorial(n.subtract(BigInteger.ONE)));
    }
    public static void main(String[] args) {
        // main() method code is in the Method Area, its execution frame is in the Thread Stack.

        System.out.print("Enter a number for factorial: ");
        Scanner sc = new Scanner(System.in);
        // 'sc' is a reference variable on the Stack pointing to a Scanner object in the Heap.

        int input = sc.nextInt();
        // 'input' is a primitive local variable stored in the Stack Frame of main()

        if (input < 0) {
            System.out.println("Factorial is not defined for negative numbers.");
        } else {
            // BigInteger.valueOf(input) returns a BigInteger object on the Heap
            // 'n' reference variable is stored on Stack
            BigInteger n = BigInteger.valueOf(input);

            // factorial(n) pushes multiple stack frames (one per recursive call) on the Thread Stack
            System.out.println("Factorial of " + input + " is : " + factorial(n));
        }
    }
}
