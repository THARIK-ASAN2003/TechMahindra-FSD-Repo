import java.util.LinkedList;
import java.util.Arrays;

public class InsertAtFront {
    public static void main(String[] args) {
        System.out.println("=== Insert at front ===");
        LinkedList<String> list = new LinkedList<>(Arrays.asList("Existing"));
        System.out.println("Before insertion: " + list);
        list.offerFirst("New Front");
        System.out.println("After insertion: " + list);
    }
}