package chapter6;

import javafx.util.Pair;
import org.junit.Assert;
import org.junit.Test;

/**
 * 题目一:数组中只出现一次的两个数字一个整型数组里除两个数字之外,其他数字都出现了两次。
 * 请写程序找出这两个只出现一次的数字。要求时间复杂度是O(n),空间复杂度是O(1)。
 *
 * @author flying
 */
public class Q56_1_FindTowNumWhichAppearOnce {
    /**
     * 1. 使用异或，遍历一遍，把其他的数字都干掉。得到一个数字就是这两个数字的异或结果。
     * 然后呢，这个数字的在二进制中，第一个 1 肯定是这两个数字异或出来的，那么这两个数字的二进制的这个位置，一个是 1，
     * 一个是 0. 然后我们按照这个标准对数组中的数字进行分组，然后分别对分成两组后的数组各做异或，最终得到的两个结果
     * 就是两个数字。
     */
    private Pair find(int[] array) {
        if (array == null || array.length == 0 || array.length % 2 != 0) {
            throw new IllegalArgumentException("param is invalid");
        }

        int combined = 0;

        for (int i : array) {
            combined ^= i;
        }

        int a = 0;
        int b = 0;
        int judge = 1;

        while ((combined & judge) != judge) {
            judge = judge << 1;
        }

        for (int i : array) {
            if ((i & judge) == judge) {
                a ^= i;
            } else {
                b ^= i;
            }
        }

        return new Pair(a, b);
    }

    @Test
    public void test() {
        Pair<Integer, Integer> pair = find(new int[] {1,1,3,4});
        Assert.assertTrue((pair.getKey() == 3 && pair.getValue() == 4) ||
            (pair.getKey() == 4 && pair.getValue() == 3));

        pair = find(new int[] {1,1,2, 2, 3, 3, 4, 4, 5, 6});
        Assert.assertTrue((pair.getKey() == 5 && pair.getValue() == 6) ||
            (pair.getKey() == 6 && pair.getValue() == 5));
    }
}