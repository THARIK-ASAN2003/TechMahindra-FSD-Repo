import java.util.LinkedList;
import java.util.Arrays;

public class InsertAtEnd {
    public static void main(String[] args) {
        System.out.println("=== Insert at end ===");
        LinkedList<String> list = new LinkedList<>(Arrays.asList("Existing"));
        System.out.println("Before insertion: " + list);
        list.offerLast("New End");
        System.out.println("After insertion: " + list);
    }
}