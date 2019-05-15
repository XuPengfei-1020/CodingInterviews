package chapter5;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

/**
 * 题目：输入”个整数，找出其中最小的 k 个数。例如，输入4、5、1、
 * 6、2、7、3、8这8个数字，则最小的4个数字是 1、2、3、4。
 */
public class Q40_FirstKInArray {
    /**
     * 第一个算法，要修改数组，思想来源于快速排序。
     * k 从 0开始。
     */
    public int[] firstKApproach1(int[] array, int k) {
        if (array == null || array.length == 0 || k >= array.length) {
            throw new IllegalArgumentException("param is invalid");
        }

        int i = -1;

        while (i != k) {
            i = partition(array, i > k ? 0 : i + 1, i > k ? i - 1 : array.length - 1);
        }

        recursePartition(array, 0, i);
        return Arrays.copyOf(array, i + 1);
    }

    private void recursePartition(int[] array, int start, int end) {
        if (start >= end) {
            return;
        }

        int mid = partition(array, start, end);
        recursePartition(array, start, mid - 1);
        recursePartition(array, mid + 1, end);
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
     * 小顶堆算法。最大堆，最小堆，有些难写，找个地方专门写它，这里先省略掉了。
     * @return
     */
    public int firstKApproach2(int[] array, int k) {
        return 0;
    }

    @Test
    public void test() {
        int[] array = new int[] {1, 0, 9, 8, 2, 3, 7, 6, 4, 5};

        for (int i = 0; i < 10; i++) {
            int[] correct = new int[i + 1];

            for (int j = 0; j <= i; j++) {
                correct[j] = j;
            }

            Assert.assertTrue(Arrays.equals(firstKApproach1(array, i), correct));
        }
    }
}