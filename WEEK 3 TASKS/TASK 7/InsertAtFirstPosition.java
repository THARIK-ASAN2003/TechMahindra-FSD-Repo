import java.util.ArrayList;
import java.util.Arrays;

public class InsertAtFirstPosition {
    public static void main(String[] args) {
        System.out.println("=== Insert at first position ===");
        ArrayList<String> list = new ArrayList<>(Arrays.asList("One", "Two", "Three"));
        System.out.println("Before insertion: " + list);
        list.add(0, "Zero");
        System.out.println("After insertion: " + list);
    }
}