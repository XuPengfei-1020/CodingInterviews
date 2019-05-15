package chapter6;

import org.junit.Assert;
import org.junit.Test;

/**
 * 题目一:数字在排序数组中出现的次数。统计一个数字在排序数组中出现的次数。
 * 例如,输入排序数组{1,2,3,3,3,3,4,5}和数字3,由于3在这个数组中出现了4次,因此输出4
 *
 * @author flying
 */
public class Q53_1_CountTimesOfNumInSortedArray {
    /**
     * 二分查找的变种题目。查找第一个出现和最后一次出现的位置。两个位置相减 + 1 即为最终答案。
     */
    private int timesOfNum(int[] array, int n) {
        if (array == null) {
            throw new IllegalStateException("param is invalid");
        }

        if (array.length == 0) {
            return 0;
        }

        return findLast(array, n) - findFirst(array, n) + 1;
    }

    private int findFirst(int[] array, int n) {
        int left = 0;
        int right = array.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (array[mid] == n && (mid == 0 || array[mid - 1] != n)) {
                return mid;
            }

            if (array[mid] >= n) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        throw new IllegalStateException(n + " can not found");
    }

    private int findLast(int[] array, int n) {
        int left = 0;
        int right = array.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (array[mid] == n && (mid == array.length - 1 || array[mid + 1] != n)) {
                return mid;
            }

            if (array[mid] <= n) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        throw new IllegalStateException(n + " can not found");
    }

    @Test
    public void test() {
        Assert.assertTrue(timesOfNum(new int[] {1, 2, 3, 3, 3, 3, 4, 5}, 3) == 4);
        Assert.assertTrue(timesOfNum(new int[] {1, 2, 3, 3, 3, 3, 4, 5}, 1) == 1);
        Assert.assertTrue(timesOfNum(new int[] {1, 2, 3, 3, 3, 3, 4, 5}, 5) == 1);
    }
}