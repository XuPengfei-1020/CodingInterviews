package chapter2;

import org.junit.Assert;
import org.junit.Test;

import java.util.LinkedList;

/**
 * 题目：请设计一个函数，、用来判断在一个矩阵中是否存在一条包含某
 * 字符串所有字符的路径。路径可以从矩阵中的任意一格开始，每一步可以
 * 在矩阵中向左、右、上、下移动一格。如果一条路径经过了矩阵的某一格，
 * 那么该路径不能再次进入该格子。例如，在下面的3×4的矩阵中包含一条
 * 字符串"bfce”的路径（路径中的字母用下画线标出但矩阵中不包含字
 * 符串"abfb”的路径，因为字符串的第一个字符b占据了矩阵中的第一行第
 * 二个格子之后，路径不能再次进入这个格子。
 * {'a', 'b', 't', 'g'},
 * {'c', 'f', 'c', 's'},
 * {'j', 'd', 'e', 'h'}
 *
 * @author flying
 */
public class Q12_PathInMatrix {
    /**
     * 思路：通过一个 Linked 存放 trace，trace 中有三个信息，i,j 坐标，以及下一个应该是它的上下左右四个周边节点的哪一个。
     * 0 表示还没有访问过下一个节点，1 表示已经访问过上节点，2表示已经访问过左边的节点，3表示已经访问过下边的节点，
     * 4 表示已经访问过左边的节点。
     * @return null 表示找不到一条 trace。
     */
    private LinkedList<int[]> pathInMatrix(char[][] matrix, char[] path) {
        if (matrix == null || matrix.length == 0) {
            return null;
        }

        LinkedList<int[]> trace = new LinkedList<>();

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] == path[0]) {
                    trace.add(new int[] {i, j, 0});

                    while (!trace.isEmpty()) {
                        if (trace.size() == path.length) {
                            return trace;
                        }

                        int [] lastPos = trace.getFirst();
                        int failure = lastPos[2]++;

                        if (failure == 4) {
                            trace.removeFirst();
                            continue;
                        }

                        int nextPosI =  lastPos[0] + (failure == 0 ? -1 : failure == 2 ? 1 : 0);
                        int nextPosJ =  lastPos[1] + (failure == 1 ? 1 : failure == 3 ? -1 : 0);

                        if (validPos(matrix, nextPosI, nextPosJ) && matrix[nextPosI][nextPosJ] == path[trace.size()]) {
                            trace.push(new int[] {nextPosI, nextPosJ, 0});
                        }
                    }
                }
            }
        }

        return null;
    }

    private boolean validPos(char[][] matrix, int i, int j) {
        if (i < 0 || i >= matrix.length) {
            return false;
        }

        if (j < 0 || j >= matrix[i].length) {
            return false;
        }

        return true;
    }

    private boolean checkTrace(char[][] matrix, LinkedList<int[]> trace, char[] target) {
        if (trace == null || trace.size() != target.length) {
            return false;
        }

        int i = target.length - 1;

        for (int[] point : trace) {
            if (matrix[point[0]][point[1]] != target[i--]) {
                return false;
            }
        }

        return true;
    }

    @Test
    public void test() {
        char[][] matrix = new char[][] {
            {'a', 'b', 't', 'g'},
            {'c', 'f', 'c', 's'},
            {'j', 'd', 'e', 'h'}
        };

        char[] target = new char[] {'a', 'b', 't', 'g'};
        LinkedList<int[]> trace = pathInMatrix(matrix, target);
        Assert.assertTrue(checkTrace(matrix, trace, target));
        target = new char[] {'a', 'c', 'f', 'd','e','c','t','g','s','h'};
        trace = pathInMatrix(matrix, target);
        Assert.assertTrue(checkTrace(matrix, trace, target));
        target = new char[] {'a', 'b', 't', 'g', 'c','t','g','s','h'};
        trace = pathInMatrix(matrix, target);
        Assert.assertFalse(checkTrace(matrix, trace, target));
    }
}