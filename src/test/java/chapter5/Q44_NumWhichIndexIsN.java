package chapter5;

import org.junit.Assert;
import org.junit.Test;

/**
 * 题目：数字以0123456789101H2131415“．的格式序列化到一个字符序
 * 列中。在这个序列中，第5位（从0开始计数）是5，第13位是1,第19
 * 位是4，等等。请写一个函数，求任意第”位对应的数字。
 *
 * @author flying
 */
public class Q44_NumWhichIndexIsN {
    /**
     * 第一时间想到的，是一个一个数，那样肯定不行啊，太慢了。找规律吧。
     * 1. 第一个 0 看着不顺眼，先把第一个 0 干掉不算，n = n-1;
     * 2. 位数为 1 的有 9 个，2 的有 90 个，3的有 900 个，位数有 k 个的有 9 * 10^(k-1) 个 (k > 0)。
     * 3. 我们把每段具有相同位数的数字，算作一个 partition, partition 的两端分别记作 partition.left, partition.right.
     * 4. 看规律：partition_1 = [1, 9], partition_2 = [partition_1.right + 1， partition_1.right + 2 * （9 * 10^(2 - 1)]
     *      partition_k = [partition_(k-1).right + 1, partition_(k-1).right + k * (9 * 10^(k-1))]
     * 5. 那么给定一个 index n， 我们直到它落在那个 k 的区间 partition 上。
     * 假如 k 落到 m 区间上，那么 (k - partition_m.right) 就是 k ，在 m 区间的相对位置，记作 m_index_k,
     * 那么 m_index_k / m + 10^(m-1) 就能得到一个数字 num，最终的结果：index(k) 就在 num 中。
     * 在 num 中，第 m_index_k % m 个字符(从 0 算起)，就是 index(k)
     */
    private int indexOf(int k) {
        if(k < 0) {
            throw new IllegalArgumentException("param is invalid");
        }

        if (k == 0) {
            return 0;
        }

        k --;
        int rightOfLastPartition = 1;
        int partition = 1;

        for (; ; partition++) {
            int rightOfNextPartition = rightOfLastPartition + 9 * pow(partition - 1);

            if (k <= rightOfNextPartition) {
                break;
            }

            rightOfLastPartition = rightOfNextPartition;
        }

        // 计算 k 的相对位置
        int mIndex = (k - rightOfLastPartition + 1);
        int num = mIndex / partition + pow(partition - 1);
        int indexOfNum = mIndex % partition;
        return num / pow(partition - indexOfNum - 1) % 10;
    }

    /**
     * 返回 10 的 n 次方。
     * @param n
     * @return
     */
    private int pow(int n) {
        int result = 1;

        while (n-- > 0) {
            result *= 10;
        }

        return result;
    }

    @Test
    public void test() {
        Assert.assertTrue(indexOf(0) == 0);
        Assert.assertTrue(indexOf(9) == 9);
        Assert.assertTrue(indexOf(13) == 1);
        Assert.assertTrue(indexOf(19) == 4);
    }
}