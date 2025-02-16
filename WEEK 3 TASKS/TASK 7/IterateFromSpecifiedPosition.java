import java.util.LinkedList;
import java.util.Arrays;
import java.util.ListIterator;

public class IterateFromSpecifiedPosition {
    public static void main(String[] args) {
        System.out.println("=== Iterate from specified position ===");
        LinkedList<String> list = new LinkedList<>(Arrays.asList("A", "B", "C", "D", "E"));
        System.out.println("Full list: " + list);
        System.out.println("Iterating from position 2:");
        ListIterator<String> positionIterator = list.listIterator(2);
        while (positionIterator.hasNext()) {
            System.out.println(positionIterator.next());
        }
    }
}