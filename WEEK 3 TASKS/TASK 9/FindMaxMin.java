import java.util.Arrays;
import java.util.List;

public class FindMaxMin {
    public static void main(String[] args) {
        List<Integer> values = Arrays.asList(45, 12, 78, 34, 89, 23, 56);

        int max = values.stream()
                .max(Integer::compareTo)
                .orElse(0);

        int min = values.stream()
                .min(Integer::compareTo)
                .orElse(0);

        System.out.println("Number list: " + values);
        System.out.println("Maximum value: " + max);
        System.out.println("Minimum value: " + min);
    }
}