package leetcode;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/longest-substring-without-repeating-characters/
 * Given a string, find the length of the longest substring without repeating characters.
 *
 * Example 1:
 *
 * Input: "abcabcbb"
 * Output: 3
 * Explanation: The answer is "abc", with the length of 3.
 *
 * Example 2:
 *
 * Input: "bbbbb"
 * Output: 1
 * Explanation: The answer is "b", with the length of 1.
 *
 * Example 3:
 *
 * Input: "pwwkew"
 * Output: 3
 * Explanation: The answer is "wke", with the length of 3.
 *              Note that the answer must be a substring, "pwke" is a subsequence and not a substring.
 *
 * 实现思路：
 * 1. 遍历整个字符串，使用两个下标记录字串 start init= 0，i 为遍历下标
 * 2. 利用 hash，存储每一个字符上次出现的位置（这里假设为字符都是英文字符 -128 ~ 127，使用数字实现，更高效）。
 * 3. 每遍历一个字符，都检查一下上次字符出现的位置是否在 start 于 i 之内。
 *      如果当前字符上次出现的位置 p 在 start 于 i 之间，那么 (start ~ i-1) 就构成了一个局部的无重复的子字符串，记录一下。
 *          并且将 star 的位置向右移动到 p + 1 这里。
 *      如果当前字符上次出现位置 p 不在 start 和 i 之间，那么继续移动 i。
 *    每次移动 i，都把 i 位置的字符于 i 的关系，记录到 hash（或者数字）中。
 * 4. 循环 3 过程，记录所有的 （start ~ i） 对，找最长的即可。
 *
 * @author flying
 */
public class LongestSubstringWithoutRepeatingCharacters {
    public int lengthOfLongestSubstring(String str) {
        if (str == null) {
            throw new IllegalArgumentException("param is invalid");
        }

        if (str.length() == 0) {
            return 0;
        }

        int length = str.length();
        int maxLength = -1;
        int[] lastRecord = new int[256];
        Arrays.fill(lastRecord, -1);
        int start = 0;

        for (int i = 0; i < length - 1; i++) {
            int idx = str.charAt(i) + Byte.MAX_VALUE;
            int last = lastRecord[idx];

            if (last >= start) {
                maxLength = Math.max(maxLength, i - start);
                start = last + 1;
            }

            lastRecord[idx] = i;
        }

        int last = lastRecord[str.charAt(str.length() - 1) + Byte.MAX_VALUE];
        return Math.max(maxLength, last >= start ? length - start - 1 : length - start);
    }
}