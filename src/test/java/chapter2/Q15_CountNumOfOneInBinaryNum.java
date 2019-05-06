package chapter2;

import org.junit.Assert;
import org.junit.Test;

/**
 * 题目：请实现一个函数，输入一个整数，输出该数二进制表示中1 的
 * 个数。例如，把9表示成二进制是1001，有2位是1。因此，如果输入9，
 * 则该函数输出2。
 */
public class Q15_CountNumOfOneInBinaryNum {
    private int countNumOfOneInBinaryNumApproach1(int num) {
        int count = 0;

        while (num != 0) {
            if ((num & 1) == 1) {
                count ++;
            }

            num >>>= 1;
        }

        return count;
    }

    /**
     * 这个方式会给面试官带来惊喜。不解释了，看代码。
     */
    private int countNumOfOneInBinaryNumApproach2(int num) {
        int count = 0;

        while (num != 0) {
            count ++;
            num = (num - 1) & num;
        }

        return count;
    }

    @Test
    public void test() {
        int[][] testCases = new int[][] {
            {0, 0},
            {1, 1},
            {2, 1},
            {4, 1},
            {5, 2},
            {7, 3},
            {-1, 32},
            {Integer.MIN_VALUE, 1},
            {Integer.MIN_VALUE - 1, 31},
            {Integer.MIN_VALUE + 1, 2}
        };

        for (int[] testCase : testCases) {
            Assert.assertTrue(countNumOfOneInBinaryNumApproach1(testCase[0]) == testCase[1]);
            Assert.assertTrue(countNumOfOneInBinaryNumApproach2(testCase[0]) == testCase[1]);
        }
    }
}