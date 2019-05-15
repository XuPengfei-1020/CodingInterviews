package chapter5;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

/**
 * 题目：输入一个正整数数组，把数组里所有数字拼接起来排成一个数，
 * 打印能拼接出的所有数字中最小的一个，例如，输入数组{3，32，321}，则打
 * 印出这3个数字能排成的最小数字321323。。
 *
 * @author flying
 */
public class Q45_MinimumNum {
    /**
     * 1. 如果想要拼凑出来的数字最小，那么第一位一定得是最小的。并且前面的几位都要尽可能的小，比如说1， 2， 3 那么 1
     * 肯定在前面比较合适。
     * 2. 但是呢，在确定第一个数字的过程中，有可能有多个数字的第一个位是相同的，比如 3，34，这时候该怎么办呢？
     * 比较他们的第二位， 把没有第二位的，补足和第一位相同的数字。比较 3(3) 和 34，3(3) 胜出，结果就是 3 在前面。如果有
     * 第二位也相同的，那么一直比较下去(补上去的数字就是循环原数字的过程)，直到最长的那个数字都已经延长到自己原本的 2 倍了，或者剩下最后一个数字。
     * 举个例子，100， 100101 如何比较， 比较到第三位，仍然部分胜负，此时没多比较一次，就需要在 100 后面多补足一个数字，
     * 这个数字依次是 1，0，0，1，0，0 就是循环本数字的过程，怎么样，是不是想到了 KMP 算法！
     * 3. 如果比到最后一位，还是有些数字不分胜负，那么好办，把这些数字合起来，谁在前谁在后无所谓。
     *
     * 4. 递归上述过程即可。
     * 在上述比较的过程中，充满了很多重复的比较。倒不如直接来个排序呢！
     * @return
     */
    private int[] minimumNum(int[] array) {
        if (array == null) {
            throw new IllegalArgumentException("param is invalid");
        }

        quickSort(array, 0, array.length - 1);
        return array;
    }

    /**
     * 因为 java 的 comparator 带泛型，只能比较引用类型的数字，所以这儿手动写一个快速排序。
     */
    private void quickSort(int[] array, int start, int end) {
        if (start >= end) {
            return;
        }

        int left = start;
        int right = end;

        while (left < right) {
            while (left < right && lessThan(array[left], array[right])) {
                right--;
            }

            if (left < right) {
                int temp = array[left];
                array[left] = array[right];
                array[right] = temp;
                left++;
            }

            while (left < right && lessThan(array[left], array[right])) {
                left++;
            }

            if (left < right) {
                int temp = array[right];
                array[right] = array[left];
                array[left] = temp;
                right--;
            }
        }

        quickSort(array, start, left - 1);
        quickSort(array, left + 1, end);
    }

    private boolean lessThan(int i, int j) {
        int lengthI = 1;
        int check = 10;

        while (i % check != i) {
            check *= 10;
            lengthI ++;
        }

        int lengthJ = 1;
        check = 10;

        while (j % check != j) {
            check *= 10;
            lengthJ ++;
        }

        if (lengthI == lengthJ) {
            return i <= j;
        }

        for (int index = 0; index < lengthI * 2 || index < lengthJ * 2; index++) {
            int numi = i / (int) Math.pow(10, lengthI - index % lengthI - 1) % 10;
            int numj = j / (int) Math.pow(10, lengthJ - index % lengthJ - 1) % 10;

            if (numi != numj) {
                return numi < numj;
            }
        }

        return true;
    }

    @Test
    public void test() {
        int[] array = new int[] {0,2,3,4,5,6,1,9,8,7,6,5};
        quickSort(array, 0, array.length -1);
        Assert.assertTrue(lessThan(1,1) == true);
        Assert.assertTrue(lessThan(11,1) == true);
        Assert.assertTrue(lessThan(1,11) == true);
        Assert.assertTrue(lessThan(121,12) == true);
        Assert.assertTrue(lessThan(12, 121) == false);
        Assert.assertTrue(lessThan(12, 121) == false);
        Assert.assertTrue(Arrays.equals(minimumNum(new int[] {3, 32, 321}), new int[] {321, 32, 3}));
        Assert.assertTrue(Arrays.equals(minimumNum(new int[] {3, 3, 3}), new int[] {3, 3, 3}));
        Assert.assertTrue(Arrays.equals(minimumNum(new int[] {111, 11112, 11113}), new int[] {111, 11112, 11113}));
        Assert.assertTrue(Arrays.equals(minimumNum(new int[] {101, 101010101, 101010101}), new int[] {101010101, 101010101, 101}));
    }
}