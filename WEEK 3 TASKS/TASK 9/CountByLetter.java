import java.util.Arrays;
import java.util.List;

public class CountByLetter {
    public static void main(String[] args) {
        List<String> fruits = Arrays.asList("apple", "banana", "apricot", "cherry", "date", "blueberry");
        char targetLetter = 'a';

        long count = fruits.stream()
                .filter(s -> s.toLowerCase().startsWith(String.valueOf(targetLetter)))
                .count();

        System.out.println("Fruits list: " + fruits);
        System.out.println("Number of fruits starting with '" + targetLetter + "': " + count);
    }
}