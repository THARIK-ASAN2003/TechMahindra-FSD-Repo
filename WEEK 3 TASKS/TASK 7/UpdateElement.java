import java.util.ArrayList;
import java.util.Arrays;

public class UpdateElement {
    public static void main(String[] args) {
        System.out.println("=== Update element ===");
        ArrayList<String> list = new ArrayList<>(Arrays.asList("Old1", "Old2", "Old3"));
        System.out.println("Before update: " + list);
        list.set(1, "New2");
        System.out.println("After update: " + list);
    }
}