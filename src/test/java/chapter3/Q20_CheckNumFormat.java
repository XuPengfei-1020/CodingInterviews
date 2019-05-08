package chapter3;

import org.junit.Assert;
import org.junit.Test;

/**
 * 题目：请实现一个函数用来判断字符串是否表示数值（包括整数和小
 * 数）。例如，字符串"+100"、"5e2"、
 * "-123", "31416"及"-1E-16"都表示数
 * "一123"、
 * 值，但"12e"、"1a3．14"、
 * "1.2．3"、”+一5"及"12e+5．4"都不是。
 *
 * @author flying
 */
public class Q20_CheckNumFormat {
    /**
     * 题目稍微有些难，如果用责任链设计，应该会好实现一些，但是代码会多不少。
     * 总结一下，最多有一个 e, 从 e 分开 e 的前半段 A， 后半段 B
     * 1. A 必须存在，且可以在开头有一个 + 或者 - 号，可以在正负号后面最多有一个小数点. A 中的其他位置必须都是数字。
     * 2. B 可以不存在，若存在，则不能有小数点。其他规则和 A 一样。
     */
    private boolean valid(String num) {
        // 扫描一遍，寻找 e 或者 E。
        int index = -1;

        for (int i = 0; i < num.length(); i++) {
            if (num.charAt(i) == 'e' || num.charAt(i) == 'E') {
                if (index != -1) {
                    return false;
                }

                index = i;
            }
        }

        String part1 = index == -1 ? num : num.substring(0, index);
        String part2 = index == -1 ? null : num.substring(index + 1);

        if (part1 == null || part1.length() == 0 || (part2 != null && part2.length() == 0)) {
            return false;
        }

        return valid0(part1, true) && (part2 == null || valid0(part2, false));
    }

    // 校验非科学记数法个数的数字
    private boolean valid0(String num, boolean point) {
        boolean hasPoint = !point;

        for (int i = 0; i < num.length(); i++) {
            char c = num.charAt(i);

            if (c >= '0' && c <= '9') {
                continue;
            }

            if ((c == '+' || c == '-') && i == 0) {
                continue;
            }

            if (c == '.' && !hasPoint) {
                hasPoint = true;
                continue;
            }

            return false;
        }

        return true;
    }

    @Test
    public void test() {
        String[][] testCases = new String[][] {
            new String[] {"+100", "true"},
            new String[] {"+100.0", "true"},
            new String[] {"-100.01", "true"},
            new String[] {"-123", "true"},
            new String[] {"-123.123", "true"},
            new String[] {"-.123", "true"},
            new String[] {"+.123", "true"},
            new String[] {".123", "true"},
            new String[] {"5e2", "true"},
            new String[] {".5e2", "true"},
            new String[] {"0.5e2", "true"},
            new String[] {"-1.2e-34", "true"},
            new String[] {"-12e-0", "true"},
            new String[] {"-12e-123", "true"},
            new String[] {"-12e-1.23", "false"},
            new String[] {"0.5eE2", "false"},
            new String[] {"0.5e", "false"},
            new String[] {"0.5e1.2", "false"},
            new String[] {"0.5a", "false"},
            new String[] {"a1234", "false"},
            new String[] {"1.2.34", "false"},
            new String[] {"1.2e.34", "false"},
            new String[] {"1.2e.34", "false"}
        };

        for (String[] testCase : testCases) {
            boolean b = String.valueOf(valid(testCase[0])).equals(testCase[1]);

            if (!b) {
                System.out.println(testCase[0] + "," + testCase[1]);
            }

            Assert.assertTrue(b);
        }
    }
}