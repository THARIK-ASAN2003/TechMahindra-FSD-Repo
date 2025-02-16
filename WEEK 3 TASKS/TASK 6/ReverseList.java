import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ReverseList {
    public static <T> List<T> reverseList(List<T> list) {
        List<T> reversed = new ArrayList<>(list);
        Collections.reverse(reversed);
        return reversed;
    }

    public static void main(String[] args) {
        List<String> letters = new ArrayList<>(Arrays.asList("a", "b", "c", "d"));

        System.out.println("Original list: " + letters);
        System.out.println("Reversed list: " + reverseList(letters));
    }
}