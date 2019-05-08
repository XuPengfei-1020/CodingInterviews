package chapter3;

import org.junit.Assert;
import org.junit.Test;

/**
 * 题目：输入一个整数数组，实现一个函数来调整该数组中数字的顺序，
 * 使得所有奇数位于数组的前半部分，所有偶数位于数组的后半部分。
 *
 * @author flying
 */
public class Q21_1_RegularizedOddBeforeEven {
    /**
     * 拿到这个题目，第一时间想到的是快速排序，不过还有些关键的地方不太一样。。。
     * 两个坐标 i = 0， j = array.length - 1
     * i 和 j 分别往对方方向走，直到 i 坐标的是偶数，j 坐标的是奇数，然后将这两个数对调，
     * 对调后 i j 分别前进一个格。直到它们相遇，o(n) 时间内解决。
     * 注意边界检查。
     *
     * @param nums
     */
    private void regularized(int[] nums) {
        if (nums == null || nums.length < 1) {
            return;
        }

        int i = 0, j = nums.length - 1;

        while (i < j) {
            boolean leftIsOdd = (nums[i] & 1) == 1;
            boolean rightIsEven = (nums[j] & 1) == 0;

            if (leftIsOdd) {
                i++;
            }

            if (rightIsEven) {
                j--;
            }

            if (!leftIsOdd && !rightIsEven) {
                int temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;
            }
        }
    }

    /**
     * 检查效果。
     */
    private boolean check(int[] nums) {
        boolean even = false;

        for (int num : nums) {
            if ((num & 1) == 1) {
                if (even) {
                    return false;
                }
            } else {
                even = true;
            }
        }

        return true;
    }

    @Test
    public void test() {
        int[][] testCases = new int[][] {
            new int[]{1,2,3,4,5,6,7,8},
            new int[]{1},
            new int[]{1,1,1,1,1},
            new int[]{2,2,2,2,2,2,2},
        };

        for (int[] testCase : testCases) {
            regularized(testCase);
            Assert.assertTrue(check(testCase));
        }
    }
}