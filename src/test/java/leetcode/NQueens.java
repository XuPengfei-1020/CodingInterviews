package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * N 皇后问题。
 * 全排列解法，对棋盘放置进行全排列。然后在全排列的过程中对排列的方式进行“合法性剪枝”。
 * 有了另外一个剪枝思路是，对已经计算出的结果寻找“其他三个旋转对称结果（分别把棋盘旋转 90°，180°，270°后得到的结果）”，
 * 然后使用这些旋转计算出来的结果对后续的全排列计算进行剪枝（再剪掉一些计算量）。但是没有思考清楚在全排列的方式下，如何
 * 有效的利用这些旋转计算出来的结果来进行剪枝，以减少计算量。如果这个思路能想清楚实现方案，那会在“合法性剪枝”的基础上
 * 继续剪枝，让速度一骑绝尘！
 *
 * leetcode 结果：
 * Runtime: 2 ms, faster than 95.39% of Java online submissions for N-Queens.
 * Memory Usage: 36.2 MB, less than 100.00% of Java online submissions for N-Queens.
 * @author flying
 */
public class NQueens {
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> answers = new ArrayList<>();
        permutation(0, n, new int[n], answers);
        return answers;
    }

    /**
     * 进行全排列，在全排列的过程中进行剪枝。
     * @param row 当前排列的行
     * @param n 列的数量
     * @param trace，前序计算中已经排列好的 trace
     * @param answers，答案收集器
     */
    private void permutation(int row, int n, int[] trace, List<List<String>> answers) {
        for(int i = 0; i < n; i++) {
            if (check(row, i, trace)) {
                trace[row] = i;

                if (row != n - 1) {
                    permutation(row + 1, n, trace, answers);
                    continue;
                }

                List<String> answer = new ArrayList<>(trace.length);
                char[] r = new char[n];

                for (int j = 0; j < trace.length; j++) {
                    Arrays.fill(r, '.');
                    r[trace[j]] = 'Q';
                    answer.add(new String(r));
                }

                answers.add(answer);
            }
        }
    }

    /**
     * 计算一个 position 是否是可能的答案。
     * @param row position 行
     * @param col position 列
     * @param trace 前面几行已经摆放好的皇后
     * 思路如下：前面的每个已经放置好的皇后，对其所在的 [行，列，45°和 -45°的两个斜线] 都有霸权，
     * 即：这些线条上都容不下其他皇后了。
     * 计算当前的 position(row, col) 是否和前面的几个皇后冲突的方式就是计算当前的 position 是否在其他皇后的
     * “行、列、45°对角线、-45°对角线” 这四个线条任意一个线条的霸权上。
     *  行、列都好判断，两个对角线怎么判断呢？有一个简单结论：如果两个坐标 position1， position2 在 45°对角线上冲突，那么
     *  (position1.row + position.col == position2.row + position2.col) == true，反之亦然，互为充要。
     *  所以 45° 冲突的计算方式是 row + col， 那么 -45° 冲突的计算方式就是 row - col。
     *  这个简单的公式，直接省掉了很多内存，一个长度为 n 的 trace 数组，就可以对后续可能的位置快速校验合法性，
     *  从而达到高效剪枝的目的。
     */
    private boolean check(int row, int col, int[] trace) {
        for (int i = 0; i < row; i++) {
            int pos = trace[i];
            int idx1 = col + row;
            int idx2 = col - row;

            if (pos == col || (pos + i) == idx1 || (pos - i) == idx2) {
                return false;
            }
        }

        return true;
    }
}