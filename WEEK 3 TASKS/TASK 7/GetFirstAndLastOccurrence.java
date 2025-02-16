import java.util.LinkedList;
import java.util.Arrays;

public class GetFirstAndLastOccurrence {
    public static void main(String[] args) {
        System.out.println("=== Get first and last occurrence ===");
        LinkedList<String> list = new LinkedList<>(Arrays.asList("A", "B", "A", "C", "A", "D"));
        String element = "A";
        System.out.println("List: " + list);
        System.out.println("First occurrence of '" + element + "': " + list.indexOf(element));
        System.out.println("Last occurrence of '" + element + "': " + list.lastIndexOf(element));
    }
}