package chapter6;

import org.junit.Assert;
import org.junit.Test;

/**
 * 约瑟夫环问题, 关于数学推论，网上一搜一堆。也不大容易看懂。
 * 这里给一个使用数组模拟的过程把，在实现上简单一些的。但是性能肯定不如最后推导出来的公式。
 *
 * @author flying
 */
public class Q62_JosephusCycle {
    /**
     * n 是一开的人数，m 选择要杀掉的认数的那个循环数字。
     */
    private int josephusCycle(int n, int m) {
        // 1 表示已经被杀掉了。
        byte[] bird = new byte[n];
        int killed = 0;
        int lastKilled = bird.length - 1;

        while (killed < bird.length - 1) {
            int pos = m % (n - killed++);
            pos = pos == 0 ? (n - killed + 1) : pos;

            for (int i = lastKilled + 1; ; i++) {
                if (bird[i % n] == 0 && --pos == 0) {
                    bird[i % n] = 1;
                    lastKilled = i % n;
                    break;
                }
            }
        }

        for (int i = 0; i < bird.length; i++) {
            if (bird[i] == 0) {
                return i;
            }
        }

        return -1;
    }

    /**
     * 数学公式，如何推导出来的，我也没仔细看，当作第一个函数的验证函数吧。
     */
    private int josephusCycleApproach02(int n, int m) {
        if (n < 1 || m < 1) {
            return -1;
        }

        int last = 0;

        for (int i = 2; i <= n; i++) {
            last = (last + m) % i;
        }

        return last;
    }

    @Test
    public void test() {
        Assert.assertTrue(josephusCycle(3, 2) == josephusCycleApproach02(3, 2));
        Assert.assertTrue(josephusCycle(10, 7) == josephusCycleApproach02(10, 7));
        Assert.assertTrue(josephusCycle(100, 7) == josephusCycleApproach02(100, 7));
    }
}