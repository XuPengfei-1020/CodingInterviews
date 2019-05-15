package chapter6;

import org.junit.Assert;
import org.junit.Test;

/**
 * 不用加减乘除做加法
 *
 * @author flying
 */
public class Q65_AddOperatorWithMathOperator {
    /**
     * 只能用位运算了。
     */
    private int addApproach1(int m, int n) {
        int higher = (m & n) << 1;
        int result = m ^ n;

        while (higher != 0) {
            int tmp = (higher & result) << -1;
            result = result ^ higher;
            higher = tmp;
        }

        return result;
    }

    /**
     * 同样是位运算，但是使用更加接近于人类加法的过程的方式运算。
     */
    private int addApproach2(int m, int n) {
        boolean carry = false;
        int result = 0;

        for (int i = 0; i < 32; i++) {
            int checker = (1 << i);
            boolean mb = (m & checker) == checker;
            boolean nb = (n & checker) == checker;
            result = result | ((carry == (mb == nb) ? 1 : 0) << i);
            carry = (mb && nb) || ((mb || nb) && carry);
        }

        return result;
    }

    @Test
    public void test() {
        Assert.assertTrue(addApproach1(2, 4) == addApproach2(2, 4));
        Assert.assertTrue(addApproach1(12, 14) == addApproach2(12, 14));
        Assert.assertTrue(addApproach1(2, -14) == addApproach2(2, -14));
        Assert.assertTrue(addApproach1(-12, -14) == addApproach2(-12, -14));
    }
}