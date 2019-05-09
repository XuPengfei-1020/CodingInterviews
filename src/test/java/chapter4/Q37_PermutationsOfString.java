package chapter4;

import datastructure.SimpleLinkedNode;
import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashSet;

/**
 * 题目：输入一个字符串，打印出该字符串中字符的所有排列。例如，
 * 输入字符串abc，则打印出由字符a、b、c所能排列出来的所有字符串abc、
 * acb、bac、bca、cab和cba。
 *
 * @author flying
 */
public class Q37_PermutationsOfString {
    /**
     * 动态规划，开头是 a 的，后边可以由其他非a的内容组成。
     *
     * @param abc 字符串内容。
     */
    private ArrayList<String> permutationsOfString(char [] abc) {
        if (abc == null || abc.length == 0) {
            return null;
        }

        ArrayList<String> results = new ArrayList<>();

        if (abc.length == 1) {
            results.add(new String(abc));
            return results;
        }

        for (int i = 0; i < abc.length; i++) {
            char c = abc[i];
            ArrayList<String> follows = permutationsOfString(deleteChar(abc, c));

            for (String s : follows) {
                results.add(c + s);
            }
        }

        return results;
    }

    /**
     * 删除某个指定的 char
     */
    private char[] deleteChar(char[] abc, int a) {
        if (abc == null || abc.length == 0) {
            return null;
        }

        char[] result = new char[abc.length - 1];
        int j = 0;

        for (int i = 0; i < abc.length; i++) {
            if (abc[i] != a) {
                result[j++] = abc[i];
            }
        }

        return result;
    }

    @Test
    public void test() {
        ArrayList<String> results = permutationsOfString(new char[] {'a', 'b', 'c'});
        HashSet<String> expects = new HashSet<>();
        expects.add("abc");
        expects.add("acb");
        expects.add("bac");
        expects.add("bca");
        expects.add("cab");
        expects.add("cba");

        for (String s : results) {
            Assert.assertTrue(expects.remove(s));
        }

        Assert.assertTrue(expects.size() == 0);

        results = permutationsOfString(new char[] {'a'});
        expects.add("a");

        for (String s : results) {
            Assert.assertTrue(expects.remove(s));
        }

        Assert.assertTrue(expects.size() == 0);
    }
}