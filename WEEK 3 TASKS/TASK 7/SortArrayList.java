import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class SortArrayList {
    public static void main(String[] args) {
        System.out.println("=== Sort ArrayList ===");
        ArrayList<String> list = new ArrayList<>(Arrays.asList("Zebra", "Lion", "Tiger", "Elephant"));
        System.out.println("Before sorting: " + list);
        Collections.sort(list);
        System.out.println("After sorting: " + list);
    }
}