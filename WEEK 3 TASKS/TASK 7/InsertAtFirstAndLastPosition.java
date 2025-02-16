import java.util.LinkedList;
import java.util.Arrays;

public class InsertAtFirstAndLastPosition {
    public static void main(String[] args) {
        System.out.println("=== Insert at first and last position ===");
        LinkedList<String> list = new LinkedList<>(Arrays.asList("Middle"));
        System.out.println("Before insertion: " + list);
        list.addFirst("First");
        list.addLast("Last");
        System.out.println("After insertion: " + list);
    }
}