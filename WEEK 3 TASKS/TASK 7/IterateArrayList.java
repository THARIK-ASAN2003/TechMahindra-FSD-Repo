import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public class IterateArrayList {
    public static void main(String[] args) {
        System.out.println("=== Iterate through ArrayList ===");
        ArrayList<String> items = new ArrayList<>(Arrays.asList("Item1", "Item2", "Item3", "Item4"));

        System.out.println("Using for loop:");
        for (int i = 0; i < items.size(); i++) {
            System.out.println(items.get(i));
        }

        System.out.println("\nUsing enhanced for loop:");
        for (String item : items) {
            System.out.println(item);
        }

        System.out.println("\nUsing iterator:");
        Iterator<String> iterator = items.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}