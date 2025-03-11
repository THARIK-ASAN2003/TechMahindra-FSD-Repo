import java.util.Objects;

public class CompareArrays {
    public static <T> boolean compareArrays(T[] arr1, T[] arr2) {
        if (arr1.length != arr2.length)
            return false;
        for (int i = 0; i < arr1.length; i++)
            if (!Objects.equals(arr1[i], arr2[i]))
                return false;
        return true;
    }

    public static void main(String[] args) {
        Integer[] arr1 = { 1, 2, 3, 4 };
        Integer[] arr2 = { 1, 2, 3, 4 };
        Integer[] arr3 = { 1, 2, 4, 3 };

        System.out.println("arr1 vs arr2: " + compareArrays(arr1, arr2)); // true
        System.out.println("arr1 vs arr3: " + compareArrays(arr1, arr3)); // false
    }
}