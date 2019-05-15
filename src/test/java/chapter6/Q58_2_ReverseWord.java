package chapter6;

import org.junit.Assert;
import org.junit.Test;

/**
 * 题目二:左旋转字符串。字符串的左旋转操作是把字符串前面的若干个字符转移到字符串的尾部。
 * 请定义一个函数实现字符串左旋转操作的功能。
 * 比如,输入字符串" abcdefg"和数字2,该函数将返回左旋转两位得到的结果" cdefgab"
 *
 * @author flying
 */
public class Q58_2_ReverseWord {
    private String reverse(String word, int pos) {
        if (word == null || pos >= word.length()) {
            throw new IllegalArgumentException("param is invalid");
        }

        char[] sentenceArr = word.toCharArray();
        reverseWord(sentenceArr, 0, sentenceArr.length - 1);
        // 从 pos 位置，各反转一下。
        reverseWord(sentenceArr, 0, sentenceArr.length - pos - 1);
        reverseWord(sentenceArr, sentenceArr.length - pos, sentenceArr.length - 1);
        return new String(sentenceArr);
    }

    private void reverseWord(char[] arr, int begin, int end) {
        while (begin < end) {
            char tmp = arr[begin];
            arr[begin] = arr[end];
            arr[end] = tmp;
            begin ++;
            end --;
        }
    }

    @Test
    public void test() {
        Assert.assertTrue(reverse("abcdefg", 2).equals("cdefgab"));
    }
}