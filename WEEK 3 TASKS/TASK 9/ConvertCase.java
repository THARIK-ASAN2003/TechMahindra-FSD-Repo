import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ConvertCase {
    public static void main(String[] args) {
        List<String> words = Arrays.asList("Java", "Python", "JavaScript", "Ruby");

        List<String> upperCase = words.stream()
                .map(String::toUpperCase)
                .collect(Collectors.toList());

        List<String> lowerCase = words.stream()
                .map(String::toLowerCase)
                .collect(Collectors.toList());

        System.out.println("Original: " + words);
        System.out.println("Uppercase: " + upperCase);
        System.out.println("Lowercase: " + lowerCase);
    }
}