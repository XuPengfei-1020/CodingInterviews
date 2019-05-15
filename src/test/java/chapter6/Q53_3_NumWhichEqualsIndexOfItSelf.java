package chapter6;

import org.junit.Assert;
import org.junit.Test;

/**
 * 题目三:数组中数值和下标相等的元素。
 * 假设一个单调递增的数组里的每个元素都是整数并且是唯一的。请编程实现一个函数,找出数组中任意一个数值等于其下标的元素。
 * 例如,在数组(-3,-1,1,3,5}中,数字3和它的下标相等。
 *
 * @author flying
 */
public class Q53_3_NumWhichEqualsIndexOfItSelf {
    /**
     * 二分查找
     */
    private int numWhichEqualsIndexOfItSelf(int[] array) {
        if (array == null) {
            throw new IllegalArgumentException("param is invalid");
        }

        int start = 0, end = array.length - 1;

        while (start <= end) {
            int mid = start + (end - start) / 2;

            if (array[mid] == mid) {
                return mid;
            }

            if (array[mid] > mid) {
                end = mid - 1;
                continue;
            }

            start = mid + 1;
        }

        throw new IllegalArgumentException("param is invalid");
    }

    @Test
    public void test() {
        Assert.assertTrue(numWhichEqualsIndexOfItSelf(new int[] {-1,0,2,4,6,7}) == 2);
        Assert.assertTrue(numWhichEqualsIndexOfItSelf(new int[] {0,2,3,4,6,7}) == 0);
        Assert.assertTrue(numWhichEqualsIndexOfItSelf(new int[] {-2,-1,0,1,4}) == 4);
    }
}