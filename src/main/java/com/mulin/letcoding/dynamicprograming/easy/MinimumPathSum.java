package com.mulin.letcoding.dynamicprograming.easy;

/**
 * Created by
 * @author mulin
 * @date 2018/9/10
 * @description:
给定一个包含非负整数的 m x n 网格，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。

说明：每次只能向下或者向右移动一步。

示例:

输入:
[
[1,3,1],
[1,5,1],
[4,2,1]
]
输出: 7
解释: 因为路径 1→3→1→1→1 的总和最小。
 */
public class MinimumPathSum {

    int[][] grid;
    int[][] tmp;
    public int minPathSum(int[][] grid) {
        this.grid = grid;
        this.tmp = new int[grid.length][grid[0].length];

        for (int i = 0; i < grid.length; ++i) {
            for (int j = 0; j < grid[0].length; ++j) {
                tmp[i][j] = -1;
            }
        }

        return minPath(grid.length - 1, grid[0].length - 1);
    }

    public int minPath(int m, int n) {
        if (m == 0 && n == 0) {
            return grid[0][0];
        }

        int l = tmp[m][n - 1];
        int t = tmp[m - 1][n];

        if (n == 0) {
            if (t == -1) {
                t = minPath(m - 1, n);
                tmp[m - 1][n] = t;
            }

            return  t + grid[m][n];
        } else if (m == 0) {

            if (l == -1) {
                l = minPath(m, n - 1);
                tmp[m][n - 1] = l;
            }
            return l + grid[m][n];
        } else {
            if (t == -1) {
                t = minPath(m - 1, n);
                tmp[m - 1][n] = t;
            }

            if (l == -1) {
                l = minPath(m, n - 1);
                tmp[m][n - 1] = l;
            }

            return Math.min(t, l) + grid[m][n];
        }
    }
}
