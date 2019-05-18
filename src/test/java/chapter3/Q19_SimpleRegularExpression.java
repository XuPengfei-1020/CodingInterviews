package chapter3;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashSet;

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
    public boolean match(String s, String p) {
        if (s == null || p == null) {
            throw new IllegalArgumentException("Param is invalid!");
        }

        if (p.length() == 0) {
            return s.length() == 0;
        }

        RegUnit head = null;
        RegUnit current = null;

        for(int i = 0; i < p.length(); i++) {
            char c = p.charAt(i);
            boolean star = i + 1 < p.length() && p.charAt(i + 1) == '*';

            if (star) {
                i++;
            }

            if (current == null) {
                current = new RegUnit(c, star);
                head = current;
                continue;
            }

            current.next = new RegUnit(c, star);
            current = current.next;
        }

        // mark as accpet
        RegUnit accept = new RegUnit(Integer.MIN_VALUE, false);
        current.next = accept;
        HashSet<RegUnit> curStates = new HashSet<>();
        HashSet<RegUnit> tempStates = new HashSet<>();

        while(head != null && head.star) {
            curStates.add(head);
            head = head.next;
        }

        if (head != null) {
            curStates.add(head);
        }

        for(int i = 0; i < s.length(); i++) {
            if (curStates.size() == 0) {
                return false;
            }

            char c = s.charAt(i);

            for(RegUnit state : curStates) {
                if (state.match(c)) {
                    state.moveToNexts(tempStates);
                }
            }

            curStates.clear();
            HashSet<RegUnit> temp = tempStates;
            tempStates = curStates;
            curStates = temp;
        }

        for (RegUnit state : curStates) {
            if (state == accept) {
                return true;
            }
        }

        return false;
    }

    class RegUnit {
        boolean star;
        boolean spot;
        int character;
        RegUnit next;

        RegUnit(int character, boolean star) {
            this.character = character;
            this.spot = character == '.';
            this.star = star;
        }

        boolean match(char c) {
            return spot || character == c;
        }

        void moveToNexts(HashSet<RegUnit> states) {
            if (star) {
                states.add(this);
            }

            RegUnit nextState = this.next;

            while(nextState != null && nextState.star) {
                states.add(nextState);
                nextState = nextState.next;
            }

            if (nextState != null) {
                states.add(nextState);
            }
        }
    }

    @Test
    public void test() {
        String[][] testCases = new String[][] {
            new String[] {"aaa", "aaa", "true"},
            new String[] {"ab", ".*..", "true"},
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
            new String[] {"", ".", "false"},
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
            boolean b = String.valueOf(match(testCase[0], testCase[1])) == testCase[2];

            if (!b) {
                System.out.println(testCase[0] + ", " + testCase[1] + ", " + testCase[2]);
            }

            Assert.assertTrue(b);
        }
    }
}