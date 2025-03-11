import java.util.Arrays;
import java.util.List;

public class SumEvenOdd {
    public static <T extends Number> long[] sumEvenOdd(List<T> list) {
        long evenSum = 0, oddSum = 0;
        for (T num : list) {
            long val = num.longValue();
            if (val % 2 == 0)
                evenSum += val;
            else
                oddSum += val;
        }
        return new long[]{ evenSum, oddSum };
    }

    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);
        long[] sums = sumEvenOdd(numbers);

        System.out.println("Even sum: " + sums[0] + ", Odd sum: " + sums[1]);
    }
}