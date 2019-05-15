package chapter6;

import org.junit.Assert;
import org.junit.Test;

/**
 * 题目:从扑克牌中随机抽5张牌,判断是不是一个顺子,即这5张牌是不是连续的。
 * 2~10为数字本身,A为1,J为11,Q为12,K为13,而大、小王可以看成任意数字。
 *
 * @author flying
 */
public class Q61_10JQKAInPoker {
    /**
     * 来一个 长度为 13 的数组，然后按照 index 填充数组中的内容。并且记录大小王的个数。
     * 遍历数组，遍历到第一个元素后，能持续遍历5个元素的（有空缺就用大小王补上），就是顺子。
     */
    private boolean isSequence(byte[] poker) {
        byte universal = 0;
        byte[] checker = new byte[13];

        for (byte b : poker) {
            if (b == 0) {
                universal ++;
                continue;
            }

            checker[b - 1] = 1;
        }

        int i = 0;

        while (checker[i] != 1 || checker[(i == 0 ? 12 : i - 1)] != 0) {
            i++;
        }

        int sequence = 0;

        while (sequence++ < 5) {
            if (checker[i++ % checker.length] != 1 && universal-- == 0) {
                return false;
            }
        }

        return true;
    }

    @Test
    public void test() {
        Assert.assertTrue(isSequence(new byte[] {1,2,3,4,5}));
        Assert.assertTrue(isSequence(new byte[] {2,3,4,5,6}));
        Assert.assertTrue(isSequence(new byte[] {1,2,3,12,13}));
        Assert.assertTrue(isSequence(new byte[] {9,10,11,12,13}));
        Assert.assertTrue(isSequence(new byte[] {1,2,0,12,13}));
        Assert.assertTrue(isSequence(new byte[] {9,0,0,12,8}));
        Assert.assertTrue(isSequence(new byte[] {9,0,0,12,13}));
        Assert.assertFalse(isSequence(new byte[] {9,10,7,12,13}));
        Assert.assertFalse(isSequence(new byte[] {9,10,8,12,13}));
        Assert.assertFalse(isSequence(new byte[] {1,2,4,12,13}));
        Assert.assertFalse(isSequence(new byte[] {1,2,3,4,6}));
        Assert.assertFalse(isSequence(new byte[] {2,4,6,8,10}));
        Assert.assertFalse(isSequence(new byte[] {1,2,3,8,13}));
    }
}