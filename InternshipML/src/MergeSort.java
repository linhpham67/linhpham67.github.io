import java.util.ArrayList;
import java.util.List;

public class MergeSort {
    public static <T extends Comparable<T>> List<T> mergesort(List<T> unsorted) {
        if (unsorted.size() <= 1) {
            return unsorted; // base case
        }

        int midPoint = unsorted.size() / 2;
        var left = mergesort(unsorted.subList(0, midPoint));
        var right = mergesort(unsorted.subList(midPoint, unsorted.size()));

        var sorted = new ArrayList<T>();
        // Merging
        int l = 0; // index into next value in left
        int r = 0; // index into next value in right
        while (l < left.size() || r < right.size()) {
            if (r >= right.size()
                    || (l < left.size() && left.get(l).compareTo(right.get(r)) < 0)) {
                sorted.add(left.get(l));
                l += 1;
            } else {
                sorted.add(right.get(r));
                r += 1;
            }
        }

        return sorted;
    }

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(4);
        list.add(7);
        list.add(1);
        list.add(3);
        list.add(4);
        list.add(2);
        list.add(6);

        System.out.println(mergesort(list));

    }

}