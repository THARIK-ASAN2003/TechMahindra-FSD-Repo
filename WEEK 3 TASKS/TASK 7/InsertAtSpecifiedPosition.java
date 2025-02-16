import java.util.LinkedList;
import java.util.Arrays;

public class InsertAtSpecifiedPosition {
    public static void main(String[] args) {
        System.out.println("=== Insert at specified position ===");
        LinkedList<String> list = new LinkedList<>(Arrays.asList("One", "Two", "Four"));
        System.out.println("Before insertion: " + list);
        list.add(2, "Three");
        System.out.println("After insertion at position 2: " + list);
    }
}