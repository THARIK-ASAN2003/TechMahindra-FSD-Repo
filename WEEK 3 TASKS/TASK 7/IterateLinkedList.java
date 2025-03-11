import java.util.LinkedList;
import java.util.Arrays;
import java.util.ListIterator;

public class IterateLinkedList {
    public static void main(String[] args) {
        System.out.println("=== Iterate through LinkedList ===");
        LinkedList<String> list = new LinkedList<>(Arrays.asList("First", "Second", "Third"));

        System.out.println("Using for-each loop:");
        for (String item : list) {
            System.out.println(item);
        }

        System.out.println("\nUsing ListIterator:");
        ListIterator<String> listIterator = list.listIterator();
        while (listIterator.hasNext()) {
            System.out.println(listIterator.next());
        }
    }
}