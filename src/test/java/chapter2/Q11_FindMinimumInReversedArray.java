package chapter2;

import org.junit.Assert;
import org.junit.Test;

/**
 * 题目：把一个数组最开始的若干个元素搬到数组的末尾，我们称之为
 * 数组的旋转。输入一个递增排序的数组的一个旋转，输出旋转数组的最小
 * 元素。例如，数组{3，4，5，1，2}为{1，2，3，4，5}的一个旋转，该数组的最小
 * 值为1。
 *
 * @author flying
 */
public class Q11_FindMinimumInReversedArray {
    /**
     * 话不多说，放码过来
     * @param array
     * @return
     */
    private int findMinimum(int[] array, int start, int end) {
        if (array == null || array.length == 0) {
            throw new IllegalArgumentException("Param is invalid");
        }

        if (start >= end) {
            return array[start];
        }

        int mid = start + (end - start) / 2;

        if (array[mid] > array[array.length - 1]) {
            return findMinimum(array, mid + 1, end);
        } else {
            return findMinimum(array, start, mid - 1);
        }
    }

    @Test
    public void test() {
        int[] reserved = {3, 4, 5, 1, 2};
        Assert.assertTrue(findMinimum(reserved, 0, reserved.length - 1) == 1);
        reserved = new int[]{1,2,3,4,5};
        Assert.assertTrue(findMinimum(reserved, 0, reserved.length - 1) == 1);
        reserved = new int[]{5,4,3,2,1};
        Assert.assertTrue(findMinimum(reserved, 0, reserved.length - 1) == 1);
        reserved = new int[]{1,1,1,1,1,1,1};
        Assert.assertTrue(findMinimum(reserved, 0, reserved.length - 1) == 1);
    }
}