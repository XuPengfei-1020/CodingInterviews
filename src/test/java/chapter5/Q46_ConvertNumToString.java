package chapter5;

import org.junit.Assert;
import org.junit.Test;

/**
 * 题目：给定一个数字，我们按照如下规则把它翻译为字符串：0翻译成
 * “a”，1翻译成"b” ... ，11翻译成“L”，
 * ，25翻译成"z”。求一个数字可能有多个翻译。例如，12258有5种不同的翻译，分别是“bccfi”、“bwfi" bczi mcfi mzi
 * 。请编程实现一个函数，用来计算一个数字有多少中不同的翻译方法。
 *
 * @author flying
 */
public class Q46_ConvertNumToString {
    /**
     * 类斐波那契问题，青蛙跳台阶问题。
     * 1. 如果只有一位字符，那么就一种翻译方式。
     * 2. 如果是两位数字，那么如果这两位数 < 26 & >= 10 </>,那么有两种方式，否则有一种方式。
     * 3. 如果是 n 位数字，那么有 最后两位 < 26 & >= 10 ? count(n-2) + count(n - 1) : count(n - 1)
     */
    private int countTransilateWays(int num) {
        int lengthOfNum = 1;
        int check = 10;

        while (num / check != 0) {
            lengthOfNum++;
            check = check * 10;
        }

        if (lengthOfNum <= 1) {
            return lengthOfNum;
        }

        int prevOfPrev = 0;
        int prev = 1;
        int lastNum = num / (int) Math.pow(10, lengthOfNum - 1) % 10;

        while (lengthOfNum-- > 1) {
            int nowNum = num / (int) Math.pow(10, lengthOfNum - 1) % 10;
            int lastTowNum = lastNum * 10 + nowNum;
            int count = ((lastTowNum > 9 && lastTowNum < 26) ? prevOfPrev : 0) + prev;
            prevOfPrev = prev;
            prev = count;
        }

        return prev;
    }

    @Test
    public void test() {
        Assert.assertTrue(countTransilateWays(12258) == 5);
        Assert.assertTrue(countTransilateWays(999999999) == 1);
    }
}