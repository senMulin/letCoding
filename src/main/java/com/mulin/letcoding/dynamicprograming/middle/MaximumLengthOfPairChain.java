package com.mulin.letcoding.dynamicprograming.middle;

/**
 * Created by
 * @author mulin
 * @date 2018/9/25
 * @description:

给出 n 个数对。 在每一个数对中，第一个数字总是比第二个数字小。

现在，我们定义一种跟随关系，当且仅当 b < c 时，数对(c, d) 才可以跟在 (a, b) 后面。我们用这种形式来构造一个数对链。

给定一个对数集合，找出能够形成的最长数对链的长度。你不需要用到所有的数对，你可以以任何顺序选择其中的一些数对来构造。

示例 :

输入: [[1,2], [2,3], [3,4]]
输出: 2
解释: 最长的数对链是 [1,2] -> [3,4]
注意：

给出数对的个数在 [1, 1000] 范围内。
 */
public class MaximumLengthOfPairChain {

    int maxIdx = 0, minIdx = 0,
    max = Integer.MIN_VALUE, min = Integer.MAX_VALUE;

    int[] dp;
    int[] visited;
    int[][] pairs;
    public int findLongestChain(int[][] pairs) {

        this.pairs = pairs;
        this.dp = new int[pairs.length];
        this.visited = new int[pairs.length];

        for (int i = 0; i < pairs.length; i++) {

            if (max < pairs[i][0]) {
                this.maxIdx = i;
                this.max = pairs[i][0];
            }

            if (min > pairs[i][1]) {
                this.minIdx = i;
                this.min = pairs[i][1];
            }

            dp[i] = -1;
        }

        this.dp[minIdx] = 1;
        this.visited[maxIdx] = 1;

        return find(maxIdx);
    }

    public int find(int idx) {
        int sum = 1;

        if (idx == this.minIdx) {
            return 1;
        }

        int max = 0;

        this.visited[idx] = 1;
        int val = pairs[idx][0];

        for (int i = 0; i < this.pairs.length; i++) {
            int result = 0;

            if (pairs[i][1] >= val) {
                continue;
            }

            if (this.visited[i] == 1) {
                continue;
            }
            result = dp[i];
            if (result == -1) {
                result = find(i);
            }

            max = result > max ? result : max;
        }

        visited[idx] = 0;
        sum+=max;
        dp[idx] = sum;

        return sum;
    }

    public static void main(String[] args) {
        MaximumLengthOfPairChain m = new MaximumLengthOfPairChain();

//        int[][] testcase = new int[][]{{-10,-8}, {8,9}, {-5,0}, {6,10}, {-6,-4}, {1,7}, {9,10}, {-4,7}};

        int[][] testcase = new int[][]{{3, 4}, {2, 4}, {1, 2}};

        System.out.println(m.findLongestChain(testcase));
    }
}
