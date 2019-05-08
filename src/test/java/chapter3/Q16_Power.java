package chapter3;

import org.junit.Assert;
import org.junit.Test;

/**
 * 题目：实现函数doublePower(doublebase，intexponent)，求base的
 * exponent次方。、不得使用库函数，同时不需要考虑大数问题。
 *
 * @author flying
 */
public class Q16_Power {
    /**
     * 求 m 的 n 次方, 不用考虑大数问题，越界就越界了吧。
     */
    private double power(int m, int n) {
        if (n == 0) {
            return 1;
        }

        if (n > 0) {
            int result = m;

            while (n-- > 1) {
                result = result * m;
            }

            return result;
        }

        // n < 0
        if (m == 0) {
            throw new IllegalArgumentException("M is 0");
        }

        return (double) 1 / power(m, -n);
    }

    @Test
    public void test() {
        Assert.assertTrue(power(2, 3) == 8);
        Assert.assertTrue(power(2, 0) == 1);
        Assert.assertTrue(power(2, 1) == 2);
        Assert.assertTrue(power(2, -1) == (double) 1/2);
        Assert.assertTrue(power(0, 0) == 0 || power(0, 0) == 1);
        Assert.assertTrue(power(2, -5) == (double) 1/Math.pow(2, 5));
    }
}
