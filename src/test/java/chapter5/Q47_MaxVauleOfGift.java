package chapter5;

import org.junit.Assert;
import org.junit.Test;

/**
 * 题目：在一个m * n的棋盘的每一格都放有一个礼物，每个礼物都有一
 * 定的价值（价值大于0）。你可以从棋盘的左上角开始拿格子里的礼物，并
 * 每次向右或者向下移动一格，直到到达棋盘的右下角，给定一个棋盘及其
 * 上面的礼物，请计算你最多能拿到多少价值的礼物？
 *
 * @author flying
 */
public class Q47_MaxVauleOfGift {
    /**
     * 跟抢银行是一个题，对于每一个格子，取到的最大值 max(m,n) = max(max(m-1, n), max(m, n-1)) + value(m, n)
     * 动态规划，一个递归搞定。
     * 可以使用一个二维数组缓存中间结果，来避免重复计算。
     */
    private int maxValueOfGift(int[][] gifts, int i, int j) {
        if(i == 0 && i == j) {
            return gifts[i][j];
        }

        if (j == 0) {
            return maxValueOfGift(gifts, i - 1, j) + gifts[i][j];
        }

        if (i == 0) {
            return maxValueOfGift(gifts, i, j - 1) + gifts[i][j];
        }

        return Math.max(maxValueOfGift(gifts, i - 1, j), maxValueOfGift(gifts, i, j - 1)) + gifts[i][j];
    }

    @Test
    public void test() {
        int[][] gifts = new int[][] {
            {1, 10, 3, 8},
            {12, 2, 9, 6},
            {5, 7, 4, 11},
            {3, 7, 16, 5},
        };

        Assert.assertTrue(maxValueOfGift(gifts, 3, 3) == 53);
    }
}