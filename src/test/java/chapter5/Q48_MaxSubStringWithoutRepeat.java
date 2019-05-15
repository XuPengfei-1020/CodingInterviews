package chapter5;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

/**
 * 题目：请从字符串中找出一个最长的不包含重复字符的子字符串，计
 * 算该最长子字符串的长度。假设字符串中只包含、'z'的字符。例如，在字
 * 符串"arabcacfr"中，最长的不含重复字符的子字符串是"ac什"，长度为4。
 *
 * @author flying
 */
public class Q48_MaxSubStringWithoutRepeat {
    /**
     * 使用一个 length 为 26 的数组来保存上一次字母出现的位置。
     * @param str
     * @return
     */
    private String maxSubStringWithoutRepeat(String str) {
        if (str == null) {
            throw new IllegalArgumentException("param is invalid");
        }

        String result = "";
        int[] lastRecord = new int[26];
        Arrays.fill(lastRecord, -1);
        int start = 0;

        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            int last = lastRecord[c - 'a'];

            if (last != -1 &&  last >= start) {
                result = result.length() < i - start ? str.substring(start, i) : result;
                start = last + 1;
            } else if (i == str.length() - 1) {
                result = result.length() < str.length() - start ? str.substring(start) : result;
            }

            lastRecord[c - 'a'] = i;
        }

        return result;
    }

    @Test
    public void test() {
        Assert.assertTrue(maxSubStringWithoutRepeat("arabcacfr").equals("rabc"));
        Assert.assertTrue(maxSubStringWithoutRepeat("arabacacfr").equals("acfr"));
    }
}