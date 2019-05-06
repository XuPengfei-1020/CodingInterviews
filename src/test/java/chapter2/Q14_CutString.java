package chapter2;

import org.junit.Assert;
import org.junit.Test;

/**
 * 题目：给你一根长度为 n 的绳子，请把绳子剪成 m 段、m，n 都是整数，
 * n > 1 并且 m > 1），每段绳子的长度记为 k[0]，k[1]，..., k[m]。请问 k[0] * k[1] * ... * k[m]
 * 可能的最大乘积是多少？例如，当绳子的长度是8时，我们把它剪成长
 * 度分别为2、3、3的三段，此时得到的最大乘积是 18。
 * 解体思路：动态规划。
 * 贪婪算法需要用数据推断，代码则很简单，这里不再证明。
 *
 * @author flying
 */
public class Q14_CutString {
    // 动态规划问题，如何优化。1. 缓存算过的数值， 2.从底部向上算(本题目没思考适用与否)
    // f(n) = for [i in n] max(f(i), f(n - i))
    public int maxProduct(int n) {
        if (n == 0 || n == 1) {
            return n;
        }

        int result = n;

        for (int i = 1; i < n; i++) {
            result = Math.max(maxProduct(i) * maxProduct(n - i), result);
        }

        return result;
    }

    @Test
    public void test() {
        Assert.assertTrue(maxProduct(3) == 3);
        Assert.assertTrue(maxProduct(5) == 6);
        Assert.assertTrue(maxProduct(8) == 18);
    }
}