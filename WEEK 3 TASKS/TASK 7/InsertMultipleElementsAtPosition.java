import java.util.LinkedList;
import java.util.Arrays;
import java.util.List;

public class InsertMultipleElementsAtPosition {
    public static void main(String[] args) {
        System.out.println("=== Insert multiple elements at position ===");
        LinkedList<String> list = new LinkedList<>(Arrays.asList("Start", "End"));
        List<String> elementsToAdd = Arrays.asList("New1", "New2", "New3");
        System.out.println("Before insertion: " + list);
        list.addAll(1, elementsToAdd);
        System.out.println("After insertion: " + list);
    }
}