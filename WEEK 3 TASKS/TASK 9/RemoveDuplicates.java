import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class RemoveDuplicates {
    public static void main(String[] args) {
        List<String> duplicateList = Arrays.asList("apple", "banana", "apple", "cherry", "date", "banana");
        
        List<String> uniqueList = duplicateList.stream()
                .distinct()
                .collect(Collectors.toList());

        System.out.println("Original list with duplicates: " + duplicateList);
        System.out.println("List with duplicates removed: " + uniqueList);
    }
}