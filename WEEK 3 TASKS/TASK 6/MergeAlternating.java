import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MergeAlternating {
    public static <T> List<T> mergeAlternating(List<T> list1, List<T> list2) {
        List<T> merged = new ArrayList<>();
        int i = 0;
        while (i < list1.size() || i < list2.size()) {
            if (i < list1.size())
                merged.add(list1.get(i));
            if (i < list2.size())
                merged.add(list2.get(i));
            i++;
        }
        return merged;
    }

    public static void main(String[] args) {
        List<Integer> list1 = Arrays.asList(1, 3, 5);
        List<Integer> list2 = Arrays.asList(2, 4, 6, 8);

        System.out.println("Merged alternating list: " + mergeAlternating(list1, list2));
    }
}