import java.util.Arrays;

public class AlternateElementsCopy {
    public static void main(String[] args) {
        int[] originalArray = {10, 20, 30, 40, 50, 60, 70, 80};
        
        // Calculate the size of the new array (half the size of the original, rounded up)
        int newSize = (originalArray.length + 1) / 2;
        int[] alternateArray = new int[newSize];

        // Copy alternate elements
        int index = 0;
        for (int i = 0; i < originalArray.length; i += 2) {
            alternateArray[index++] = originalArray[i];
        }

        // Print the original and copied arrays
        System.out.println("Original Array: " + Arrays.toString(originalArray));
        System.out.println("Alternate Elements Array: " + Arrays.toString(alternateArray));
    }
}