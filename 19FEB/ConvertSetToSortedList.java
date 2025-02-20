import java.util.*;

public class ConvertSetToSortedList {
    public static <T extends Comparable<? super T>> List<T> convertSetToSortedList(Set<T> set) {
        List<T> sortedList = new ArrayList<>(set);
        Collections.sort(sortedList);
        return sortedList;
    }

    public static void main(String[] args) {
        Set<Integer> set = new HashSet<>(Arrays.asList(3, 1, 2, 2, 3, 4));
        System.out.println(convertSetToSortedList(set));
    }
}
