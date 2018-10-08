package com.mulin.letcoding.dynamicprograming.middle;

/**
 * Created by
 *
 * @author mulin
 * @date 2018/10/8
 * @description:

给定字符串 s 和 t ，判断 s 是否为 t 的子序列。

你可以认为 s 和 t 中仅包含英文小写字母。字符串 t 可能会很长（长度 ~= 500,000），而 s 是个短字符串（长度 <=100）。

字符串的一个子序列是原始字符串删除一些（也可以不删除）字符而不改变剩余字符相对位置形成的新字符串。（例如，"ace"是"abcde"的一个子序列，而"aec"不是）。

示例 1:
s = "abc", t = "ahbgdc"

返回 true.

示例 2:
s = "axc", t = "ahbgdc"

返回 false.


 */
public class IsSubsequence {

    public boolean isSubsequence(String s, String t) {

        char[] scs = s.toCharArray();
        char[] tcs = t.toCharArray();

        int sIdx = 0;
        int tIdx = 0;

        while (sIdx < scs.length) {

            while (tIdx < tcs.length) {
                if (s.charAt(sIdx) == t.charAt(tIdx)) {
                    sIdx++;
                    tIdx++;
                    break;
                }
                tIdx++;
            }


            if (tIdx >= tcs.length && sIdx < scs.length) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        IsSubsequence i = new IsSubsequence();

        String s = "abc", t = "ahbgdc";
        System.out.println(i.isSubsequence(s, t));
    }


}
