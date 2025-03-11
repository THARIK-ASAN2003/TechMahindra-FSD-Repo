import java.util.Arrays;

public class ArrayCopyRangeExample {
    public static void main(String[] args) {
        int[] original = {5, 10, 15, 20, 25, 30};
        int[] copiedRange = Arrays.copyOfRange(original, 1, 4); // Copies elements from index 1 to 3

        System.out.println("Original Array: " + Arrays.toString(original));
        System.out.println("Copied Range: " + Arrays.toString(copiedRange));
    }
}