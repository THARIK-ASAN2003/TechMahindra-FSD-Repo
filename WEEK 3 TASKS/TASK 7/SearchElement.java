import java.util.ArrayList;
import java.util.Arrays;

public class SearchElement {
    public static void main(String[] args) {
        System.out.println("=== Search for element ===");
        ArrayList<String> list = new ArrayList<>(Arrays.asList("Apple", "Banana", "Orange", "Grape"));
        String searchElement = "Orange";
        int index = list.indexOf(searchElement);
        System.out.println("List: " + list);
        System.out.println("Element '" + searchElement + "' found at index: " + index);
    }
}