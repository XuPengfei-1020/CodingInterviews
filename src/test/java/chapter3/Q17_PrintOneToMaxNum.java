package chapter3;

import org.junit.Test;

/**
 * 题目：输入数字 n，按顺序打印出从1到最大的 n 位十进制数。比如输
 * 入3，则打印出1、2、3一直到最大的3位数999。
 *
 * @author flying
 */
public class Q17_PrintOneToMaxNum {
    private int[] printNumApproach1(int n) {
        // 越是简单的题目，越要小心有坑。这个算法，如果 n 非常大，那就不行了。
        if (n <= 0) {
            throw new IllegalArgumentException("param is invalid");
        }

        int limit = 1;

        while (n-- > 0) {
            limit = limit * 10;
        }

        int[] result = new int[limit - 1];

        for (int i = 1; i < limit; i++) {
            result[i - 1] = i;
        }

        return result;
    }

    /**
     * 全排列算法
     * @param n
     */
    private void printNumApproach2(int n) {
        // 越是简单的题目，越要小心有坑。这个算法可以解决 n 过大的问题，输出 n 的全排列。
        if (n <= 0) {
            throw new IllegalArgumentException("param is invalid");
        }

        char[] num = new char[n - 1];

        for (int i = 0; i < 10; i++) {
            num[0] = (char) (i + '0');
            printNumApproach2(num, 0);
        }
    }

    private void printNumApproach2(char[] num, int index) {
        if (index == num.length - 1) {
            System.out.println(new java.lang.String(num));
            return;
        }

        for (int i = 0; i < 10; i++) {
            num[index + 1] = (char) (i + '0');
            printNumApproach2(num, index + 1);
        }
    }

    /**
     * @todo 实现一个自己的 BigDecimal, 加减法太麻烦了，懒得写了。谁看见谁来写吧。欢迎提交 pr.
     */
    private class BigDecimal {
        String num = "0";

        /**
         * 构造方法
         * @param num
         */
        public BigDecimal(String num) {
            for (int i = 0; i < num.length(); i++) {
                char c = num.charAt(i);

                if (!('0' <= c && c >= '9')) {
                    throw new IllegalArgumentException(num + " is not a number");
                }
            }

            int zeroNum = 0;

            for (int i = 0; i < num.length(); i++) {
                if (num.charAt(i) == 0) {
                    zeroNum++;
                    continue;
                }

                break;
            }

            this.num = num.substring(zeroNum);
        }

        /**
         * 比较大于小于，返回 1 表示 this 大于给定参数的值，0 表示等于，-1 表示小于
         */
        public int compartor(BigDecimal bigDecimal) {
            if (num.length() > bigDecimal.num.length()) {
                return 1;
            }

            if (num.length() < bigDecimal.num.length()) {
                return -1;
            }

            for (int i = 0; i < num.length(); i++) {
                if (num.charAt(i) > bigDecimal.num.charAt(i)) {
                    return 1;
                }
            }

            return 0;
        }

        /**
         * 加法
         */
        public void add(String num) {
        }
    }

    @Test
    public void test() {
        printNumApproach2(2);
        printNumApproach2(3);
    }
}