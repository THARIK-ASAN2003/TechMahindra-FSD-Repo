import java.util.Arrays;

public class EqualsVsDeepEquals {
    public static void main(String[] args) {
        int[] arr1 = {1, 2, 3};
        int[] arr2 = {1, 2, 3};
        int[] arr3 = arr1; // Reference to arr1

        System.out.println("Using equals(): " + Arrays.equals(arr1, arr2)); // Compares values
        System.out.println("Using equals(): " + (arr1.equals(arr2))); // Compares references
        System.out.println("Using deepEquals(): " + Arrays.deepEquals(new Object[]{arr1}, new Object[]{arr2}));
    }
}