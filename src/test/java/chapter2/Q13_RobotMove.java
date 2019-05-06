package chapter2;

import org.junit.Test;

import java.util.LinkedList;

/**
 * 题目：地上有一个m行列的方格。一个机器人从坐标（0，0）的格子开
 * 始移动，它每次可以向左、右、上、下移动一格，但不能进入行坐标和列
 * 坐标的数位之和大于々的格子。例如，当为18时，机器人能够进入方格
 * （35，37），、因为3+5+3+7=18。但它不能进入方格（35，38），因为3+5+3+8=19。
 * 请问该机器人能够到达多少个格子？
 *
 * @author flying
 */
public class Q13_RobotMove {
    private int robotMove(int[][] matrix, int k) {
        if (matrix == null || matrix.length == 0 || k < 0) {
            return 0;
        }

        LinkedList<int[]> trace = new LinkedList<>();
        trace.add(new int[] {0, 0, 0});
        int count = 1;

        while (!trace.isEmpty()) {
            while (!trace.isEmpty()) {
                int [] lastPos = trace.getFirst();
                int lastNext = lastPos[2]++;

                if (lastNext == 4) {
                    trace.removeFirst();
                    continue;
                }

                int nextPosI =  lastPos[0] + (lastNext == 0 ? -1 : lastNext == 2 ? 1 : 0);
                int nextPosJ =  lastPos[1] + (lastNext == 1 ? 1 : lastNext == 3 ? -1 : 0);

                if (validPos(matrix, nextPosI, nextPosJ) && countIJ(nextPosI, nextPosJ) <= k) {
                    trace.push(new int[] {nextPosI, nextPosJ, 0});
                    count++;
                }
            }
        }

        return count;
    }

    private boolean validPos(int[][] matrix, int i, int j) {
        if (i < 0 || i >= matrix.length) {
            return false;
        }

        if (j < 0 || j >= matrix[i].length) {
            return false;
        }

        return true;
    }

    private int countIJ(int i, int j) {
        int count = 0;

        while (i == 0) {
            count += i % 10;
            i = i / 10;
        }

        while (j == 0) {
            count += j % 10;
            j = j / 10;
        }

        return count;
    }

    @Test
    public void test() {
        // test 省略。
    }
}