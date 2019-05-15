package chapter6;

import org.junit.Assert;
import org.junit.Test;

/**
 * 题目二:数组中唯一只出现一次的数字。在一个数组中除一个数字只出现一次之外,其他数字都出现了三次。
 * 请找出那个只出现一次的数字。
 *
 * @author flying
 */
public class Q56_2_FindNumWhichAppearOnce {
    /**
     * 我们把数组中所有数字的二进制表示的每一位都加起来。
     * 如果某一位的和能被3整除,那么那个只出现一次的数字二进制表示中对应的那一位是0:否则就是1。
     */
    private int find(int[] array) {
        if (array == null) {
            throw new IllegalArgumentException("param is invalid");
        }

        int[] count = new int[32];

        for (int i : array) {
            String bi = Integer.toBinaryString(i);

            for (int index = bi.length() - 1; index >= 0; index --) {
                count[count.length - index - 1] += ('1' == bi.charAt(index)) ? 1 : 0;
            }
        }

        int result = 0;

        for (int i = 0; i < count.length; i++) {
            result = result | (count[i] % 3 == 0 ? 0 : 1 << (count.length - i - 1));
        }

        return result;
    }

    @Test
    public void test() {
        Assert.assertTrue(find(new int[] {1,1,1, -1, 2,2,2,2}) == -1);
        Assert.assertTrue(find(new int[] {1,1,1, 3, 2,2,2,2}) == 3);
    }
}