package com.mulin.letcoding.dynamicprograming.middle;

import java.util.*;

/**
 * Created by
 *
 * @author mulin
 * @date 2018/9/27
 * @description:
给定正整数 n，找到若干个完全平方数（比如 1, 4, 9, 16, ...）使得它们的和等于 n。你需要让组成和的完全平方数的个数最少。

示例 1:

输入: n = 12
输出: 3
解释: 12 = 4 + 4 + 4.
示例 2:

输入: n = 13
输出: 2
解释: 13 = 4 + 9.
 */
public class PerfectSquares {

    List<Integer> list = new ArrayList();

    Map<Integer, Integer> map = new HashMap<>();

    public int numSquares(int n) {

        int num = 0;
        while ((num + 1) * (num + 1) <= n) {

            num++;
            list.add(num * num);
        }

        return findMin(n);
    }

    public int findMin(int n) {

        int sum = 1;
        if (n == 0) {
            return 0;
        }

        if (n < 0) {
            return Integer.MAX_VALUE;
        }

        int min = Integer.MAX_VALUE;

        for (Integer i : list) {

           Integer result = map.get(n - i);
           if (null == result) {
               result = findMin(n - i);
           }
           map.put(n - i, result);
           min = Math.min(min, result);
        }

        return sum + min;
    }

    public static void main(String[] args) {
        PerfectSquares p = new PerfectSquares();

        System.out.println(p.numSquares(88));
    }
}
