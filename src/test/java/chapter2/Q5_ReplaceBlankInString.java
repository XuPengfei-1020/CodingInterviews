package chapter2;

import org.junit.Assert;
import org.junit.Test;

import java.util.Objects;

/**
 * 题目：请实现一个函数，把字符串中的每个空格替换成"％20"。例如，
 * 输入"Wearehappy.”，则输出"We%20are%20happy.
 *
 * @author flying
 */
public class Q5_ReplaceBlankInString {
    private String replaceBlank(String str) {
        if (str == null || str.length() == 0) {
            return str;
        }

        int blankCount = 0;

        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);

            if (c == ' ') {
                blankCount ++;
            }
        }

        char[] newStr = new char[str.length() + blankCount * 2];
        int j = newStr.length - 1;

        for (int i = str.length() - 1; i >= 0; i--) {
            if (str.charAt(i) == ' ') {
                newStr[j--] = '0';
                newStr[j--] = '2';
                newStr[j--] = '%';
            } else {
                newStr[j--] = str.charAt(i);
            }
        }

        return new String(newStr);
    }

    @Test
    public void test() {
        Assert.assertTrue(Objects.equals(replaceBlank(""), ""));
        Assert.assertTrue(Objects.equals(replaceBlank(null), null));
        Assert.assertTrue(Objects.equals(replaceBlank("Hello world"), "Hello%20world"));
        Assert.assertTrue(Objects.equals(replaceBlank(" Hello world "), "%20Hello%20world%20"));
        Assert.assertTrue(Objects.equals(replaceBlank("Helloworld"), "Helloworld"));
    }
}