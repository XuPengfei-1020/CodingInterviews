package chapter6;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

/**
 * 题目:把n个骰子扔在地上,所有骰子朝上一面的点数之和为s。输入n,打印出s的所有可能的值出现的概率。
 *
 * @author flying
 */
public class Q60_SumOfDices {
    /**
     * 使用循环的方式求全排列。
     * 直接初始化一个 6^num 数量的数组。
     * 第一次扔一个骰子，填充 (0-5) 位置填充上(1-6) 6 个数字
     * 此后每一次都以前一次的筛子结果作为 base 结果，记作 base，然后开始复制 base 结果，一共复制 6 次
     * (模拟当前扔下骰子可能出现的 6 个数字)，并且每次复制，都在原来 base 的基础上逐个增加 i（第 i 次复制）。
     * 复制后的 6 个结果按照复制先后顺序合并起来作为最新结果。
     */
    private int[] premutation(int num) {
        int[] result = new int[(int) Math.pow(6, num)];
        int index = 1;

        for (int i = 1; i <= 6; i++) {
            result[i - 1] = i;
        }

        while (index ++ < 6) {
            int prevNum = (int) Math.pow(6, index - 1);

            for (int i = 6; i > 0; i--) {
                for (int j = 0; j < prevNum; j++) {
                    result[prevNum * (i - 1) + j] = result[j] + i;
                }
            }
        }

        int[] ratio = new int[5 * num + 1];

        for (int i : result) {
            if (i > 36) {
                System.out.println(i);
            }
            ratio[i - num] += 1;
        }

        return ratio;
    }

    /**
     * 使用另外一种方式计算全排列, 逐个设置每一位数字。
     */
    public int[] premutation2(int[] premutation, int index, int[] ratio) {
        if (index == premutation.length) {
            int sum = 0;

            for (int i : premutation) {
                sum += i;
            }

            ratio[sum - premutation.length]++;
            return ratio;
        }

        int num = 6;

        for (int i = 1; i <= num; i ++) {
            premutation[index] = i;
            premutation2(premutation, index + 1, ratio);
        }

        return ratio;
    }

    @Test
    public void test() {
        Assert.assertTrue(Arrays.equals(premutation2(new int[6], 0, new int[31]), premutation(6)));
    }
}