package chapter5;

import org.junit.Assert;
import org.junit.Test;

/**
 * 题目：数组中有一个数字出现的次数超过数组长度的一半，请找出这
 * 个数字。例如，输入一个长度为9的数组{1，2，3，2，2，2，5，4，2}。由于数字
 * 2在数组中出现了5次，超过数组长度的一半，内此输出2。
 *
 * @author flying
 */
public class Q39_MoreThanHalfInArray {
    /**
     * 打架算法, 让数据中不同的元素两两 pk,并且两个都死。
     * 最后数组中只剩下一个数值的元素，这个数值的数量肯定超过一半。
     */
    private int moreThanHalf(int[] array) {
        if (array == null || array.length == 0) {
            throw new IllegalArgumentException("param is null");
        }

        int lastNum = 0;
        int times = 0;

        for (int i : array) {
            if (times == 0) {
                lastNum = i;
                times = 1;
                continue;
            }

            times += i == lastNum ? 1 : -1;
        }

        return lastNum;
    }

    @Test
    public void test() {
        Assert.assertTrue(moreThanHalf(new int[] {1}) == 1);
        Assert.assertTrue(moreThanHalf(new int[] {1, 1, 2}) == 1);
        Assert.assertTrue(moreThanHalf(new int[] {1, 1, 1, 1, 1, 1, 1, 1, 2, 3, 4, 5, 6, 7}) == 1);
    }

}