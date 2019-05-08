package chapter4;

import org.junit.Assert;
import org.junit.Test;

/**
 * 题目：输入一个矩阵，按照从外向里以顺时针的顺序依次打印出每一
 * 个数字。例如，如果输入如下矩阵：
 *
 * @author flying
 */
public class Q29_PrintMatrixClockWise {
    /**
     * 来上下左右四个界限，指针碰到界限就转向。
     * 没啥好说的，上代码！
     */
    private int[] printMatrixClockWise(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[1] == null || matrix[1].length == 0) {
            return null;
        }

        int topLimit = 0;
        int leftLimit = 0;
        int bottomLimit = matrix.length - 1;
        int rightLimit = matrix[1].length - 1;
        int[] result = new int[(rightLimit + 1) * (bottomLimit + 1)];
        int index = 0;
        int forward = 0;

        while (topLimit <= bottomLimit && rightLimit <= rightLimit) {
            if (forward == 0) {
                // 遍历上边的, 从 left -> right
                for (int i = leftLimit; i <= rightLimit; i++) {
                    result[index++] = matrix[topLimit][i];
                }

                topLimit++;
            }

            if (forward == 1) {
                // 遍历右边的，从 top -> bottom
                for (int i = topLimit; i <= bottomLimit; i++) {
                    result[index++] = matrix[i][rightLimit];
                }

                rightLimit--;
            }

            if (forward == 2) {
                // 遍历底部的，从 right -> left
                for (int i = rightLimit; i >= leftLimit; i--) {
                    result[index++] = matrix[bottomLimit][i];
                }

                bottomLimit--;
            }

            if (forward == 3) {
                // 遍历左侧的，从 bottom -> top
                for (int i = bottomLimit; i >= topLimit; i--) {
                    result[index++] = matrix[i][leftLimit];
                }

                leftLimit++;
            }

            forward = (forward + 1) % 4;
        }

        return result;
    }

    @Test
    public void test() {
        int[][] matrix = new int[][] {
            { 0,  1,  2,  3,  4,  5,  6,  7,  8},
            {21, 22, 23, 24, 25, 26, 27, 28,  9},
            {20, 35, 34, 33, 32, 31, 30, 29, 10},
            {19, 18, 17, 16, 15, 14, 13, 12, 11},
        };

        int[] result = printMatrixClockWise(matrix);

        for (int i = 0; i < matrix.length * matrix[1].length; i++) {
            Assert.assertTrue(result[i] == i);
        }
    }
}