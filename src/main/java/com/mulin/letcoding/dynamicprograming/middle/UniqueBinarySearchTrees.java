package com.mulin.letcoding.dynamicprograming.middle;

/**
 * Created by
 * @author mulin
 * @date 2018/9/13
 * @description:
 *
给定一个整数 n，求以 1 ... n 为节点组成的二叉搜索树有多少种？

示例:

输入: 3
输出: 5
解释:
给定 n = 3, 一共有 5 种不同结构的二叉搜索树:

1         3     3      2      1
 \       /     /      / \      \
  3     2     1      1   3      2
 /     /       \                 \
2     1         2                 3
 */
public class UniqueBinarySearchTrees {

    public int numTrees(int n) {
        int[] dp = new int[n+1];

        if (n == 0) {
            return 0;
        }else if (n == 1) {
            return 1;
        } else if (n == 2) {
            return 2;
        }

        dp[1] = 1;
        dp[2] = 2;

        for (int i = 3; i < dp.length; i++) {

            int num = 0;
            num += 2 * dp[i - 1];

            for (int j = 2; j < i; j++) {
                num+= dp[i-j] * dp[j-1];
            }

            dp[i] = num;
        }
        return dp[n];
    }
}
