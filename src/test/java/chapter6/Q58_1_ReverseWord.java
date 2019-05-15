package chapter6;

import org.junit.Assert;
import org.junit.Test;

/**
 *
 * 题目一:翻转单词顺序。输入一个英文句子,翻转句子中单词的顺序,但单词内字符的顺序不变。
 * 为简单起见,标点符号和普通字母一样处理。例如输入字符串"I am a student.",则输出" student. a am I"。
 *
 * @author flying
 */
public class Q58_1_ReverseWord {
    /**
     * 先把整个反转过来，然后再逐个把每个单词反转一下。
     */
    private String reverseWord(String sentence) {
        if (sentence == null || sentence.length() == 0) {
            return sentence;
        }

        char[] sentenceArr = sentence.toCharArray();
        int index = 0;

        while (index <= sentenceArr.length / 2) {
            char tmp = sentenceArr[index];
            sentenceArr[index] = sentenceArr[sentenceArr.length - index - 1];
            sentenceArr[sentenceArr.length - index - 1] = tmp;
            index++;
        }

        index = 0;

        while (index < sentenceArr.length) {
            int nextIndex = index;

            while ((nextIndex + 1) < sentenceArr.length && sentenceArr[nextIndex + 1] != ' ') {
                nextIndex++;
            }

            // 反转 index - nextIndex
            int correspond = nextIndex;

            while (index < correspond) {
                char tmp = sentenceArr[index];
                sentenceArr[index] = sentenceArr[correspond];
                sentenceArr[correspond] = tmp;
                index++;
                correspond--;
            }

            index = nextIndex;

            while (index < sentenceArr.length && sentenceArr[index++] != ' ') {
            }
        }

        return new String(sentenceArr);
    }

    @Test
    public void test() {
        Assert.assertTrue(reverseWord("I am a student.").equals("student. a am I"));
    }
}