import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class SortStrings {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Zack", "Alice", "Bob", "Charlie", "David");

        List<String> ascendingSort = names.stream()
                .sorted()
                .collect(Collectors.toList());

        List<String> descendingSort = names.stream()
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());

        System.out.println("Original list: " + names);
        System.out.println("Ascending order: " + ascendingSort);
        System.out.println("Descending order: " + descendingSort);
    }
}