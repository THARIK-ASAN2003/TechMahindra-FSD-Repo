import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class CopyArrayList {
    public static void main(String[] args) {
        System.out.println("=== Copy ArrayList ===");
        ArrayList<String> source = new ArrayList<>(Arrays.asList("Source1", "Source2", "Source3"));
        ArrayList<String> destination = new ArrayList<>(Arrays.asList("Dest1", "Dest2", "Dest3"));
        System.out.println("Before copying:");
        System.out.println("Source: " + source);
        System.out.println("Destination: " + destination);
        Collections.copy(destination, source);
        System.out.println("After copying:");
        System.out.println("Source: " + source);
        System.out.println("Destination: " + destination);
    }
}