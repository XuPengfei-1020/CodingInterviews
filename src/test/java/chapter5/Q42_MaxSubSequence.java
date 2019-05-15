package chapter5;

import org.junit.Assert;
import org.junit.Test;

/**
 * 题目：输入一个整型数组，数组里有正数也有负数。数组中的一个或
 * 连续多个整数组成一个子数组。求所有子数组的和的最大值。要求时间复
 * 杂度为 O(n)。
 *
 * @author flying
 */
public class Q42_MaxSubSequence {
    /**
     * 1. 把数组的变化，看作是连绵起伏的山谷。连续的非负数序列是一段山，连续的负数序列是一段谷，山谷交错。
     * 2. 山和谷都以自己最右侧的坐标命名，记作 n
     * 2. 如果只有一段山 n，那么最大值就是这段山的面积 sum(n)。
     * 3. 在一段山的基础上添加谷 n，那么最大值是 sun(山n) 的面积, 然后把这段山谷整合起来成为一段山，整合后的山的面积是：
     *      sum(山-谷) > 0  ? sum(山-谷) : 0;， 这个记作 sum'(n)， sum'(n) 不等于 sum(n), sum(n)
     * 4. 这样的话，长度为 n 的山谷序列中，最大值连续序列 max_n = max(max(n-1), sum(n) + sum'(n -1))。
     * 5. 从第一段开始求，一直递推到 第 n 段，可以在 o(n) 的时间内搞定。
     * 为了让代码看上去行数更少，我合并了一些连续的赋值运算，以及省略掉了大括号。
     */
    private int maxSubSequence(int[] array) {
        if (array == null)
            throw new IllegalArgumentException("param is invalid");

        int max = 0, sumMount = 0;

        for (int i : array)
            max = Math.max(max, sumMount = Math.max(0, sumMount + i));

        return max;
    }

    @Test
    public void test() {
        Assert.assertTrue(maxSubSequence(new int[] {1}) == 1);
        Assert.assertTrue(maxSubSequence(new int[] {0}) == 0);
        Assert.assertTrue(maxSubSequence(new int[] {}) == 0);
        Assert.assertTrue(maxSubSequence(new int[] {-1, -2, -3, -4, -5}) == 0);
        Assert.assertTrue(maxSubSequence(new int[] {1, -2, 3, 10, -4, 7, 2, -5}) == 18);
    }
}