import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class FindIndex {
    public static <T> int indexOf(List<T> list, T target) {
        for (int i = 0; i < list.size(); i++)
            if (Objects.equals(list.get(i), target))
                return i;
        return -1;
    }

    public static void main(String[] args) {
        List<String> fruits = Arrays.asList("apple", "banana", "cherry", "date");

        System.out.println("Index of 'cherry': " + indexOf(fruits, "cherry"));
        System.out.println("Index of 'fig': " + indexOf(fruits, "fig")); // -1 if not found
    }
}