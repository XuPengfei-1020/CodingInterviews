package chapter6;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * 题目二:和为s的连续正数序列。
 * 输入一个正数s,打印出所有和为s的连续正数序列(至少含有两个数)。
 * 例如,输入15,由于1+2+3+4+5=4+5+6=7+8=15,所以打印出3个连续序列1~5、4~6和7~8。
 *
 * @author flying
 */
public class Q57_2_FindTowNumWhichSumIsSpeNum {
    /**
     * 先找出最小的序列，即从 1 开始累加。
     * 1. 从 1 开始累加，如果发现累加的和小于指定的数字，那么继续往后累加。
     * 2. 如果累加到一个数字，和突然大于指定的数字，那么尝试将开头的第一个元素去掉，并且须保证去掉开头的元素后，
     * 序列中的数字个数不能小于 2.
     * 3，如果在上述过程中，找到了一个合法序列，那个这个合法序列就是第一个合法序列。如果找不到，那么就不存在这样的序列。
     * 4. 如果上一个合法序列的数字个数大于 2， 那么把上一个合法序列后面加上一个元素，然后重复 2-3 步骤。发现下一个合法序列。
     * 5. 重复第4 步骤，发现所有合法序列。
     */
    private int[][] find(int specify) {
        int sum = 3;
        int  start = 1, index = 2;
        ArrayList<int[]> results = new ArrayList<>();

        while (start != index) {
            if (sum == specify) {
                results.add(new int[] {start, index});
                sum += ++index;
                continue;
            }

            if (sum > specify) {
                sum -= start++;
                continue;
            }

            sum += ++index;
        }

        return results.toArray(new int[0][]);
    }

    @Test
    public void test() {
        int[][] results = find(15);

        for (int[] result : results) {
            Assert.assertTrue(Arrays.equals(result, new int[]{1, 5})
                || Arrays.equals(result, new int[]{4, 6})
                || Arrays.equals(result, new int[]{7, 8})
            );
        }
    }
}