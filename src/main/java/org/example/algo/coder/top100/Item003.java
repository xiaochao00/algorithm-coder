package org.example.algo.coder.top100;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 003
 * https://leetcode-cn.com/problems/longest-substring-without-repeating-characters/
 *
 * @author shichao
 * @since 1.0.0
 * 2021/4/30 20:33
 */
public class Item003 {
    public static void main(String[] args) {
        int len = lengthOfLongestSubstring("abcabcbb");
        System.out.println(len);
        len = lengthOfLongestSubstring("bbbbb");
        System.out.println(len);
        len = lengthOfLongestSubstring("pwwkew");
        System.out.println(len);
        len = lengthOfLongestSubstring("dvdf");
        System.out.println(len);
        len = lengthOfLongestSubstring("tmmzuxt");
        System.out.println(len);
        len = lengthOfLongestSubstring("bpfbhmipx");
        System.out.println(len);
        len = lengthOfLongestSubstring("ckilbkd");
        System.out.println(len);
        len = lengthOfLongestSubstring("kdgjkjhglfp");
        System.out.println(len);
    }

    public static int lengthOfLongestSubstring(String s) {
        int[] noRepeatArray = getNoRepeatArray(s);
        System.out.println(s + " noRepeatArray:" + Arrays.toString(noRepeatArray));
        // 查找最长无重复的位置和长度
        int longestLength = 0;
        for (int i = 0; i < noRepeatArray.length; i++) {
            if (i == 0) {
                longestLength = 1;
                continue;
            }
            int curLength = noRepeatArray[i];
            if (curLength >= longestLength) {
                longestLength = curLength;
            }
        }
        //
        return longestLength;
    }

    /**
     * 获取字符串中 以每个位置处的字符为结尾的无重复串的长度
     *
     * @param s 字符串
     * @return 长度
     */
    public static int[] getNoRepeatArray(String s) {
        // 每个字符串的上一个出现的位置索引的映射关系
        Map<Character, Integer> charIndexMap = new HashMap<>(s.length());
        int[] noRepeatArray = new int[s.length()];
        char preChar = 0;
        for (int i = 0; i < s.length(); i++) {
            char curChar = s.charAt(i);
            if (i == 0) {
                preChar = curChar;
                noRepeatArray[i] = 1;
                charIndexMap.put(curChar, i);
                continue;
            }
            if (curChar == preChar) {
                noRepeatArray[i] = 1;
            } else {
                if (charIndexMap.containsKey(curChar)) {
                    // 搜索前一个字符的，无重复的开始位置
                    int startIndex = i - noRepeatArray[i - 1];
                    // 搜索当前字符的上一次出现的位置
                    int preIndex = charIndexMap.get(curChar);
                    if (preIndex >= startIndex) {
                        noRepeatArray[i] = i - preIndex;
                    } else {
                        noRepeatArray[i] = noRepeatArray[i - 1] + 1;
                    }
                } else {
                    noRepeatArray[i] = noRepeatArray[i - 1] + 1;
                }
            }
            charIndexMap.put(curChar, i);
            preChar = curChar;
        }
        return noRepeatArray;
    }
}