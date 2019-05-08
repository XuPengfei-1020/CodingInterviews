package chapter3;

import org.junit.Assert;
import org.junit.Test;

/**
 * 题目：请实现一个函数用来匹配包含'.'和'*' 的正则表达式。模式中的字
 * 符 '.' 表示任意一个字符，而 * 表示它前面的字符可以出现任意次（含0次）。
 * 在本题中，匹配是指字符串的所有字符匹配整个模式。例如，字符串"aaa"
 * 与模式"a.a"和"ab*ac*a"匹配，但与"aa．a"和"ab*a"均不匹配。
 *
 * @author flying
 */
public class Q19_SimpleRegularExpression {
    /**
     * 如果只有 .* 这样的操作，那么就是有两种运算元素，操作数和操作符，并且没有运算优先级，那么就简单了一些了。
     */
    private boolean match(String reg, String text) {
        if (reg == null || text == null) {
            throw new IllegalArgumentException("param is invalid");
        }

        // text 中的 index
        int index = 0;

        for (int i = 0; i < reg.length();) {
            if (index == text.length()) {
                // 匹配完毕了，看一下剩余的 reg 中是不是都是 * 号的 operand + operator 组合。
                while (i < reg.length()) {
                    if ((i + 1) == reg.length() || reg.charAt(i + 1) != '*') {
                        return false;
                    }

                    i += 2;
                }

                return true;
            }

            char operand = reg.charAt(i);
            char operator = ((i + 1) < reg.length()) ? reg.charAt(i + 1) : '0';
            char c = text.charAt(index++);

            if (operand == '.' || c == operand) {
                // 匹配上了, 如果 operator 是 *，那么 i 不动，继续匹配下一个 char，如果 operator 不是 *，那么 i 需要往前推进。
                i = i + (operator == '*' ? 0 : 1);
                continue;
            }

            if (operator == '*')  {
                // 不匹配，并且是 *，那么使用当前的 char 匹配下一个 operand
                i += 2;
                index --;
                continue;
            }

            return false;
        }

        // 是否是 reg 过早结束？
        return index == text.length();
    }

    /**
     * 把 reg 中多余的 * 合并成一个。
     * a***b > a*b
     */
    private String cleanReg(String reg) {
        if (reg == null || reg.length() == 0) {
            return reg;
        }

        StringBuilder sb = new StringBuilder();

        for (char c : reg.toCharArray()) {
            if (c != '*' || sb.length() == 0 || sb.charAt(sb.length() - 1) != '*') {
                sb.append(c);
            }
        }

        return sb.toString();
    }

    @Test
    public void test() {
        String[][] testCases = new String[][] {
            new String[] {"aaa", "aaa", "true"},
            new String[] {"aaa", "a*", "true"},
            new String[] {"aaa", "a*b*", "true"},
            new String[] {"aaa", "a*c*b*d*", "true"},
            new String[] {"aaac", "a*c*b*d*", "true"},
            new String[] {"aaacbd", "a*c*b*d*", "true"},
            new String[] {"aaabd", "a*c*b*d*", "true"},
            new String[] {"aaad", "a*c*b*d*", "true"},
            new String[] {"cccbd", "a*c*b*d*", "true"},
            new String[] {"ddddd", "a*c*b*d*", "true"},
            new String[] {"cccccd", "a*c*b*d*", "true"},
            new String[] {"cccccbd", "a*c*b*d*", "true"},
            new String[] {"abcd", "abcd", "true"},
            new String[] {"abcdddd", "abcd*", "true"},
            new String[] {"", "a*", "true"},
            new String[] {"", "a*", "true"},
            new String[] {"aaa", "a*b", "false"},
            new String[] {"aaa", "aaab", "false"},
            new String[] {"aaa", "a*b", "false"},
            new String[] {"aaac", "a*b*cd", "false"},
            new String[] {"aaac", "a*b*c*d", "false"},
            new String[] {"bbbccc", "a*b*c*d", "false"},
            new String[] {"cdd", "a*b*c*d", "false"},
            new String[] {"aba", "a*b*", "false"},
            new String[] {"abac", "a*b*a*cd", "false"},
        };

        for (String[] testCase : testCases) {
            boolean b = String.valueOf(match(testCase[1], testCase[0])) == testCase[2];

            if (!b) {
                System.out.println(testCase[0] + ", " + testCase[1] + ", " + testCase[2]);
            }

            Assert.assertTrue(b);
        }
    }
}
