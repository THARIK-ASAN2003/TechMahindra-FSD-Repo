import java.util.LinkedList;
import java.util.Arrays;

public class AppendToLinkedList {
    public static void main(String[] args) {
        System.out.println("=== Append to LinkedList ===");
        LinkedList<String> list = new LinkedList<>(Arrays.asList("One", "Two"));
        System.out.println("Before append: " + list);
        list.add("Three");
        System.out.println("After append: " + list);
    }
}