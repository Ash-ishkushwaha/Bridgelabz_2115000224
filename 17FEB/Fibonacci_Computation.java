import java.util.Scanner;

public class Fibonacci_Computation {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        long start = System.nanoTime();
        System.out.println(n + "th Fibonacci Number is: " + fibonacciUsingIterative(n));
        long end = System.nanoTime();

        System.out.println("Time taken by Iterative Fibonacci: " + (end - start) + " nanoseconds");

        start = System.nanoTime();
        System.out.println(n + "th Fibonacci Number is: " + fibonacciUsingRecursive(n));
        end = System.nanoTime();

        System.out.println("Time taken by Recursive Fibonacci: " + (end - start) + " nanoseconds");
        sc.close();
    }

    public static int fibonacciUsingIterative(int n) {
        int a = 0;
        int b = 1;
        for (int i = 0; i < n; i++) {
            int c = a + b;
            a = b;
            b = c;
        }
        return a;

    }

    public static int fibonacciUsingRecursive(int n) {
        if (n <= 1) {
            return n;
        }
        return fibonacciUsingRecursive(n - 1) + fibonacciUsingRecursive(n - 2);
    }
}