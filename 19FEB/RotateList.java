import java.util.*;

public class RotateList {
    public static <T> List<T> rotateList(List<T> list, int positions) {
        int size = list.size();
        positions = positions % size;
        List<T> rotated = new ArrayList<>(list.subList(positions, size));
        rotated.addAll(list.subList(0, positions));
        return rotated;
    }

    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(10, 20, 30, 40, 50);
        int rotateBy = 2;
        System.out.println(rotateList(numbers, rotateBy));
    }
}
