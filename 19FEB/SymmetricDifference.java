import java.util.*;

public class SymmetricDifference {
    public static <T> Set<T> symmetricDifference(Set<T> set1, Set<T> set2) {
        Set<T> diffSet = new HashSet<>(set1);
        diffSet.addAll(set2);
        Set<T> common = new HashSet<>(set1);
        common.retainAll(set2);
        diffSet.removeAll(common);
        return diffSet;
    }

    public static void main(String[] args) {
        Set<Integer> set1 = new HashSet<>(Arrays.asList(1, 2, 3));
        Set<Integer> set2 = new HashSet<>(Arrays.asList(3, 4, 5));
        System.out.println(symmetricDifference(set1, set2));
    }
}
