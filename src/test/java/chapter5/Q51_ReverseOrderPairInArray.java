package chapter5;

import javafx.util.Pair;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * 题目：在数组中的两个数字，如果前面一个数字大于后面的数字，
 * 则这两个数字组成一个逆序对。输入一个数组，求出这个数组中的逆序
 * 对的总数。例如，在数组{7，5，6，4}中，一共存在5个逆序对，分别是（7，6）、
 *
 * @author flying
 */
public class Q51_ReverseOrderPairInArray {
    // 排序要干的事儿就是消灭逆序对，把数组排序的过程就是消灭逆序对的过程。
    private int[] reverseOrder(int[] array, int start, int end, ArrayList collector) {
        if (array == null) {
            throw new IllegalArgumentException("param is invalid");
        }

        if (start > end) {
            throw new IllegalStateException("start is less than end");
        }

        if (start == end) {
            return new int[] {array[start]};
        }

        int mid = start + (end - start) / 2;
        int[] left = reverseOrder(array, start, mid, collector);
        int[] right = reverseOrder(array, mid + 1, end, collector);
        int[] merged = new int[end - start + 1];
        int i = 0, j = 0;

        for (;i < left.length && j < right.length;) {
            if (right[j] < left[i]) {
                for (int k = i; k < left.length; k++) {
                    collector.add(new Pair<Integer, Integer>(right[j], left[k]));
                }

                merged[i + j ] = right[j++];
            } else {
                merged[i + j ] = left[i++];
            }
        }

        for (; i < left.length; i++) {
            merged[i + j] = left[i];
        }

        for (; j < right.length; j++) {
            merged[i + j] = right[j];
        }

        return merged;
    }

    @Test
    public void test() {
        int[] array = new int[] {2, 3, 4, 5, 6, 1, 9, 8, 0};
        ArrayList<Pair<Integer, Integer>> result = new ArrayList<>();
        int[] ordered = reverseOrder(array, 0, array.length  - 1, result);
        System.out.println(Arrays.toString(ordered));

        for (Pair<Integer, Integer> pair : result) {
            System.out.println(pair.getKey() + "," + pair.getValue());
        }
    }
}