package chapter5;

import org.junit.Assert;
import org.junit.Test;

/**
 * 题目：输入”个整数，找出其中最小的个数。例如，输入4、5、1、
 * 6、2、7、3、8这8个数字，则最小的4个数字是1、2、3、4。
 */
public class Q40_LastKInArray {
    /**
     * 第一个算法，要修改数组，思想来源于快速排序。
     */
    public int lastKApproach1(int[] array, int k) {
        if (array == null || array.length == 0 || k >= array.length) {
            throw new IllegalArgumentException("param is invalid");
        }

        int i = -1;

        while (i != k) {
            i = partition(array, i > k ? 0 : i + 1, i > k ? i - 1 : array.length - 1);
        }

        return array[k];
    }

    private int partition(int[] array, int start, int end) {
        if (start == end) {
            return start;
        }

        int i = start; int j = end;
        int base = array[i];

        while (i < j) {
            while (i < j && array[j] >= base) {
                j --;
            }

            if (i < j) {
                array[i] = array[j];
                array[j] = base;
                i++;
            }

            while (i < j && array[i] <= base) {
                i ++;
            }

            if (i < j) {
                array[j] = array[i];
                array[i] = base;
                j--;
            }
        }

        return i;
    }

    /**
     * 小顶堆算法。
     * @return
     */
    public int lastKApproach2(int[] array, int k) {
        return 0;
    }

    @Test
    public void test() {
        for (int i = 0; i < 10; i++) {
            int[] array = new int[] {1, 0, 9, 8, 2, 3, 7, 6, 4, 5};
            Assert.assertTrue(lastKApproach1(array, i) == i);
            Assert.assertTrue(lastKApproach2(array, i) == i);
        }
    }
}