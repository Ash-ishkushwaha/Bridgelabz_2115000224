import java.util.Scanner;

public class String_Concatenation_Performance {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        String[] arr = new String[n];

        for (int i = 0; i < n; i++) {
            arr[i] = sc.next();
        }

        long start = System.nanoTime();
        String concatenatedString = concatenateStrings(arr);
        long end = System.nanoTime();

        System.out.println("Time taken by String Concatenation: " + (end - start) / 1e6 + " nanoseconds");

        start = System.nanoTime();
        concatenatedString = concatenateUsingStringBuilder(arr);
        end = System.nanoTime();

        System.out.println("Time taken by StringBuilder Concatenation: " + (end - start) / 1e6 + " nanoseconds");

        start = System.nanoTime();
        concatenatedString = concatenateUsingStringBuffer(arr);
        end = System.nanoTime();

        System.out.println("Time taken by StringBuffer Concatenation: " + (end - start) / 1e6 + " nanoseconds");

        sc.close();
    }

    public static String concatenateStrings(String[] arr) {
        String result = "";
        for (int i = 0; i < arr.length; i++) {
            result += arr[i];
        }
        return result;
    }

    public static String concatenateUsingStringBuilder(String[] arr) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            sb.append(arr[i]);
        }
        return sb.toString();
    }

    public static String concatenateUsingStringBuffer(String[] arr) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < arr.length; i++) {
            sb.append(arr[i]);
        }
        return sb.toString();
    }
}