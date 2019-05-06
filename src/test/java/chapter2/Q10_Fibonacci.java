package chapter2;

import org.junit.Assert;

/**
 * 斐波那契数列题目。
 * f(n) = n == 0 ? 0 : n == 1 ? 1 : f(n -1) + f(n - 2)
 * @author flying
 */
public class Q10_Fibonacci {
    /**
     * 题目一：求斐波那契数列的第 n 项
     * 写一个函数，输入“，求斐波那契（Fibonacci）数列的第项。
     * 最简单的实现方式，代码很简单，性能很受伤。
     */
    private long fibonacciApproach1(int idx) {
        if (idx == 0 || idx == 1) {
            return idx;
        }

        return fibonacciApproach1(idx - 1) + fibonacciApproach1(idx - 2);
    }

    /**
     * 题目一：求斐波那契数列的第 n 项
     * 写一个函数，输入“，求斐波那契（Fibonacci）数列的第项。
     * 倒着计算，减少了大量的重复计算。
     */
    private long fibonacciApproach2(int idx) {
        if (idx == 0 || idx == 1) {
            return idx;
        }

        int result = 0;
        int prev = 1;
        int prevOfPrev = 0;

        for (int i = 2; i <= idx; i++) {
            result = prevOfPrev + prev;
            prevOfPrev = prev;
            prev = result;
        }

        return result;
    }

    /**
     * 青蛙跳台阶问题.
     * @param steps 台阶数量
     * 解体思路：青蛙要么从 n - 1 台阶跳上来，要么从 n - 2 台阶上跳上来。
     * 那么跳 n 台阶的跳法个数为：num(n - 1) + num(n - 2), 就是斐波那契。
     * 同样，方格子覆盖问题也是斐波那契额，还有一些变种的，比如 f(n) = f(n - 1) + f(n - 3)这样的问题。
     * 类似的题目还有：f(n) = max(f(n - 1), f(n - 2)), f(n) = min(f(n - 1), f(n - 2)) 等等。
     */
    private long forgJumpSteps(int steps) {
        // 就是个斐波那契，这里不再实现了
        return -1;
    }

    public void test() {
        Assert.assertTrue(fibonacciApproach1(10) == fibonacciApproach2(10));
        Assert.assertTrue(fibonacciApproach1(0) == fibonacciApproach2(0));
        Assert.assertTrue(fibonacciApproach1(15) == fibonacciApproach2(15));
    }
}