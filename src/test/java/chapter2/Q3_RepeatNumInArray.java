package chapter2;

import org.junit.Assert;
import org.junit.Test;

/**
 * 找出数组中重复的数字。
 * 在一个长度为”的数组里的所有数字都在0～“]的范围内数组中某
 * 些数字是重复的，但不知道有几个数字重复了，也不知道每个数字重复了
 * 几次9请找出数组中任意一个重复的数字。例如，如果输入长度为7的数
 * 组{2，3，1，0，2，5，3}，那么对应的输出是重复的数字2或者3。
 *
 * @author flying
 */
public class Q3_RepeatNumInArray {
    /**
     * 修改原有的数组内容
     * 时间复杂度：O(n), 空间复杂度 O(1)
     */
    private int approach1(int[] array) {
        if (array == null || array.length == 0) {
            throw new IllegalArgumentException("param is invalid");
        }

        for (int i = 0; i < array.length; i++) {
            while (i != array[i]) {
                int num = array[i];

                if (array[num] == num) {
                    return num;
                }

                // swap i,num
                array[i] = array[num];
                array[num] = num;
            }
        }

        throw new IllegalArgumentException("No repeat value");
    }

    /**
     * 不修改原有的数组内容，需要创建一个辅助数组，需要 O(n) 的空间
     * 时间复杂度：O(n), 空间复杂度 O(n)
     */
    private int approach2(int[] array) {
        if (array == null || array.length == 0) {
            throw new IllegalArgumentException("Param is invalid");
        }

        boolean[] extar = new boolean[array.length];

        for (int i : array) {
            if (extar[i]) {
                return i;
            }

            extar[i] = true;
        }

        throw new IllegalArgumentException("No repeat value");
    }

    @Test
    public void test() {
        int[] array = new int[]{2, 3, 1, 0, 2, 5, 3};
        int repeat = approach1(array);
        Assert.assertTrue(repeat == 2 || repeat == 3);
        array = new int[]{2, 3, 1, 0, 2, 5, 3};
        repeat = approach2(array);
        Assert.assertTrue(repeat == 2 || repeat == 3);
    }
}