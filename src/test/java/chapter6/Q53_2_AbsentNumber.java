package chapter6;

import org.junit.Assert;
import org.junit.Test;

/**
 * 题目二:0 ~ n-1中缺失的数字。
 * 个长度为n-1的递增排序数组中的所有数字都是唯一的,并且每个数字都在范围0~n-1之内。
 * 在范围0~n-1内的n个数字中有且只有一个数字不在该数组中,请找出这个数字。
 *
 * @author flying
 */
public class Q53_2_AbsentNumber {
    /**
     * 正常情况下，所有的数字肯定都和自己的下标对应，找到那个下标和自己所在的数字不对应的位置，那个下标就是缺失的。
     */
    private int absentNumber(int[] array) {
        if (array == null || array.length < 2 || array[0] != 0 || array[array.length - 1] != array.length) {
            throw new IllegalStateException("param is invalid");
        }

        int left = 0, right = array.length;

        while(left <= right) {
            int mid = left + (right - left) / 2;

            if (mid != array[mid]) {
                right = mid - 1;
                continue;
            }

            if (mid == array[mid] && array[mid + 1] != (mid + 1)) {
                return mid + 1;
            }

            left = mid + 1;
        }

        throw new IllegalStateException("param is invalid");
    }

    @Test
    public void test() {
        Assert.assertTrue(absentNumber(new int[] {0, 1, 2, 4, 5, 6, 7}) == 3);
        Assert.assertTrue(absentNumber(new int[] {0, 2, 3, 4, 5, 6, 7}) == 1);
        Assert.assertTrue(absentNumber(new int[] {0, 1, 2, 3, 4, 5, 7}) == 6);
    }
}