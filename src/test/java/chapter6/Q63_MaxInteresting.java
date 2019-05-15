package chapter6;

import org.junit.Assert;
import org.junit.Test;

/**
 * 题目:假设把某股票的价格按照时间先后顺序存储在数组中,请问买卖该股票一次可能获得的最大利润是多少?
 * 例如,一只股票在某些时间节点的价格为{9,11,8,5,7,12,16,14}。
 * 如果我们能在价格为5的时候买入并在价格为16时卖出,则能收获最大的利润11
 *
 * @author flying
 */
public class Q63_MaxInteresting {
    /**
     * 假设 i 为卖出的价格。那么我们只用找到卖出价格的时候，前面的数字中的最小值，相减即为利润。
     * 利润最大的一组就是最好的买卖时机。
     */
    private int maxInteresting(int[] price) {
        int maxInteresting = 0;
        int min = Integer.MAX_VALUE;

        for (int i : price) {
            maxInteresting = Math.max(i - min, maxInteresting);
            min = Math.min(i, min);
        }

        return maxInteresting;
    }

    @Test
    public void test() {
        Assert.assertTrue(maxInteresting(new int[] {9,11,8,5,7,12,16,14}) == 11);
    }
}