import java.util.*;

public class Searching_Algorithm_Performance {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = 1000000;
        int[] arr = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = i;
        }

        int target = input.nextInt();

        HashSet<Integer> set = new HashSet<>();
        TreeSet<Integer> treeSet = new TreeSet<>();

        for (int i = 0; i < n; i++) {
            set.add(arr[i]);
            treeSet.add(arr[i]);
        }

        long start = System.nanoTime();
        boolean linearSearchResult = linearSearch(arr, target);
        long end = System.nanoTime();

        if (linearSearchResult) {
            System.out.println("Element found in the array.");
        } else {
            System.out.println("Element not found in the array.");
        }

        System.out.println("Time taken by Array Search: " + (end - start) + " nanoseconds");

        start = System.nanoTime();
        boolean hashSetSearchResult = hashSetSearch(set, target);
        end = System.nanoTime();

        if (hashSetSearchResult) {
            System.out.println("Element found in the HashSet.");
        } else {
            System.out.println("Element not found in the HashSet.");
        }

        System.out.println("Time taken by HashSet Search: " + (end - start) + " nanoseconds");

        start = System.nanoTime();
        boolean treeSetSearchResult = treeSetSearch(treeSet, target);
        end = System.nanoTime();

        if (treeSetSearchResult) {
            System.out.println("Element found in the TreeSet.");
        } else {
            System.out.println("Element not found in the TreeSet.");
        }

        System.out.println("Time taken by TreeSet Search: " + (end - start) + " nanoseconds");

        input.close();
    }

    public static boolean linearSearch(int[] arr, int target) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == target) {
                return true;
            }
        }
        return false;
    }

    public static boolean hashSetSearch(HashSet<Integer> set, int target) {
        return set.contains(target);
    }

    public static boolean treeSetSearch(TreeSet<Integer> treeSet, int target) {
        return treeSet.contains(target);
    }

}