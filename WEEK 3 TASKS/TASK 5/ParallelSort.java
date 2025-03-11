import java.util.Arrays;

public class ParallelSort {
    static void parallelSort(int[] arr) throws InterruptedException {
        int mid = arr.length / 2;
        int[] left = Arrays.copyOfRange(arr, 0, mid);
        int[] right = Arrays.copyOfRange(arr, mid, arr.length);

        Thread t1 = new Thread(() -> Arrays.sort(left));
        Thread t2 = new Thread(() -> Arrays.sort(right));
        t1.start(); t2.start();
        t1.join(); t2.join();

        merge(arr, left, right);
    }

    static void merge(int[] arr, int[] left, int[] right) {
        int i = 0, j = 0, k = 0;
        while (i < left.length && j < right.length)
            arr[k++] = (left[i] <= right[j]) ? left[i++] : right[j++];
        while (i < left.length) arr[k++] = left[i++];
        while (j < right.length) arr[k++] = right[j++];
    }

    public static void main(String[] args) throws InterruptedException {
        int[] arr = {9, 3, 7, 1, 5, 4, 8, 6, 2};
        System.out.println("Original array: " + Arrays.toString(arr));
        parallelSort(arr);
        System.out.println("Sorted array: " + Arrays.toString(arr));
    }
}