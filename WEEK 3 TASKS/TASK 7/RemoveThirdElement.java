import java.util.ArrayList;
import java.util.Arrays;

public class RemoveThirdElement {
    public static void main(String[] args) {
        System.out.println("=== Remove third element ===");
        ArrayList<String> list = new ArrayList<>(Arrays.asList("One", "Two", "Three", "Four"));
        System.out.println("Before removal: " + list);
        list.remove(2);
        System.out.println("After removal: " + list);
    }
}