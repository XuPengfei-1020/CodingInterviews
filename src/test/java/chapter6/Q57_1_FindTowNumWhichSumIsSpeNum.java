package chapter6;

import javafx.util.Pair;
import org.junit.Assert;
import org.junit.Test;

/**
 * 题目一:和为s的两个数字。输入一个递增排序的数组和一个数字s,在数组中查找两个数,使得它们的和正好是s。
 * 如果有多对数字的和等于s,则输出任意一对即可。
 *
 * @author flying
 */
public class Q57_1_FindTowNumWhichSumIsSpeNum {
    /**
     * 因为数组是递增排序的，因此可以使用两个指针 left, right，分别初始化在开头和结尾。
     * 1. 如果两个指针指向的数字之和大于要指定的数字，那么 right --，
     * 2. 如果两个指针指向的数字之和小于指定的数字，那么 left++,
     * 3. 如果两个指针指向的数字之和等于指定的数字，那么返回两个指针的位置
     * 4. 如果两个指针相遇了，那么返回 null（没有满足指定条件的两个数字）。
     *
     * @param array
     */
    private Pair<Integer, Integer> find(int[] array, int specify) {
        if (array == null || array.length <= 1) {
            throw new IllegalArgumentException("param is invalid");
        }

        int start = 0, end = array.length - 1;

        while (start < end) {
            int sum = array[start] + array[end];

            if (sum == specify) {
                return new Pair<>(array[start], array[end]);
            }

            if (sum > specify) {
                end --;
                continue;
            }

            start ++;
        }

        return null;
    }

    @Test
    public void test() {
        Pair<Integer, Integer> result = find(new int[] {1, 2, 4, 7, 11, 15}, 15);
        Assert.assertTrue(result.getKey() == 4 && result.getValue() == 11);
        Assert.assertTrue(find(new int[] {1, 2, 4, 7, 11, 15}, 7) == null);
    }
}