import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class ShuffleArrayList {
    public static void main(String[] args) {
        System.out.println("=== Shuffle ArrayList ===");
        ArrayList<String> list = new ArrayList<>(Arrays.asList("A", "B", "C", "D", "E"));
        System.out.println("Before shuffling: " + list);
        Collections.shuffle(list);
        System.out.println("After shuffling: " + list);
    }
}