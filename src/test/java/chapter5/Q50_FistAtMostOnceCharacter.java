package chapter5;

import java.util.LinkedHashMap;

/**
 * 题目一：字符串中第一个只出现一次的字符。，
 * 在字符串中找出第一个只出现一次的字符。如输入"abaccdeff'，则
 * 输出'b'。
 *
 * @author flying
 */
public class Q50_FistAtMostOnceCharacter {
    /**
     * 使用一个 linked hash 表来记录那些出现过的字符，以及他们的次数。代码太多简单，引起不适。。。不写了。
     */
    private char atMostOnceChar(String s) {
        if (s == null) {
            throw new IllegalArgumentException("param is invalid");
        }

        LinkedHashMap<Character, Integer> cache = new LinkedHashMap<>();
        return 0;
    }
}