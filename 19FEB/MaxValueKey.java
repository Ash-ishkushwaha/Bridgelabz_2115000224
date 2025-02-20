import java.util.*;

public class MaxValueKey {
    public static <K, V extends Comparable<V>> K findMaxValueKey(Map<K, V> map) {
        return Collections.max(map.entrySet(), Map.Entry.comparingByValue()).getKey();
    }

    public static void main(String[] args) {
        Map<String, Integer> map = new HashMap<>();
        map.put("A", 10);
        map.put("B", 20);
        map.put("C", 15);

        System.out.println(findMaxValueKey(map));
    }
}
