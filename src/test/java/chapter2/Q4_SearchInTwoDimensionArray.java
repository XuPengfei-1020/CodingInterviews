package chapter2;

import org.junit.Assert;
import org.junit.Test;

/**
 * 二维数组中的查找
 * 题目：在一个二维数组中，每一行都按照从左到右递增的顺序排序，
 * 每一列都按照从上到下递增的顺序排序。请完成一个函数，输入这样的一
 * 个二维数组和一个整数，判断数组中是否含有该整数。
 * 1  2  8  9
 * 2  4  9  12
 * 4  7  10 13
 * 6  8  11 15
 * 期望：查找 6，可以找到，查找 5，找不到。
 *
 * @author flying
 */
public class Q4_SearchInTwoDimensionArray {
    private boolean searchITwoDimensionArray(int[][] array, int target) {
        if (array == null || array.length == 0) {
            return false;
        }

        int jlimit = 0;

        outer:
        for (int i = array.length - 1; i >= 0; i--) {
            int[] inner = array[i];

            inner:
            for (int j = jlimit; j < inner.length; j++) {
                if (inner[j] == target) {
                    System.out.println("At :" + i + "," + j);
                    return true;
                }

                if (inner[j] > target) {
                    continue outer;
                }

                if (inner[j] < target) {
                    jlimit++;
                }
            }
        }

        return false;
    }

    @Test
    public void test() {
        int[][] array = new int[][] {
            {1, 2,  8,  9},
            {2, 4, 9, 12},
            {4, 7, 10, 13},
            {6, 8, 11, 15}
        };

        Assert.assertTrue(searchITwoDimensionArray(array, 11));
        Assert.assertTrue(searchITwoDimensionArray(array, 12));
        Assert.assertFalse(searchITwoDimensionArray(array, 5));
    }
}