import java.util.LinkedList;
import java.util.Arrays;
import java.util.Iterator;

public class IterateInReverse {
    public static void main(String[] args) {
        System.out.println("=== Iterate in reverse ===");
        LinkedList<String> list = new LinkedList<>(Arrays.asList("First", "Second", "Third"));
        System.out.println("Original list: " + list);
        System.out.println("Reverse iteration:");
        Iterator<String> reverseIterator = list.descendingIterator();
        while (reverseIterator.hasNext()) {
            System.out.println(reverseIterator.next());
        }
    }
}