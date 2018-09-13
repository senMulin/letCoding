package com.mulin.letcoding.dynamicprograming.easy;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by
 *
 * @author mulin
 * @date 2018/9/13
 * @description:
给定一个字符串，你的任务是计算这个字符串中有多少个回文子串。

具有不同开始位置或结束位置的子串，即使是由相同的字符组成，也会被计为是不同的子串。

示例 1:

输入: "abc"
输出: 3
解释: 三个回文子串: "a", "b", "c".
示例 2:

输入: "aaa"
输出: 6
说明: 6个回文子串: "a", "a", "a", "aa", "aa", "aaa".
注意:

输入的字符串长度不会超过1000。
 */
public class PalindromicSubstrings {

    public int countSubstrings(String s) {
        int sum = 0;
        char[] cs = s.toCharArray();

        List<Integer> list = new LinkedList<>();
        for (int i = 0; i < cs.length -1; i++) {
            int size = 0;

            int p, q;

            if (cs[i] == cs[i+1]) {
                p = i - 1;
                q = i + 2;
                size++;

                while (p >= 0 && q < cs.length) {

                    if (cs[p] == cs[q]) {
                        size++;
                        p--;
                        q++;
                    }else {
                        break;
                    }
                }
            }

            p = i-1;
            q = i+1;

            while (p >= 0 && q < cs.length) {

                if (cs[p] == cs[q]) {
                    size++;
                    p--;
                    q++;
                }else {
                    break;
                }
            }

            list.add(size);
        }

        for (Integer i : list) {
            sum+= i;
        }

        sum+=cs.length;

        return sum;
    }
}
