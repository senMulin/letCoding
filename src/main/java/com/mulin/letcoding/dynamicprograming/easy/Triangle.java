package com.mulin.letcoding.dynamicprograming.easy;

import java.util.List;

/**
 * Created by
 *
 * @author mulin
 * @date 2018/9/12
 * @description:

给定一个三角形，找出自顶向下的最小路径和。每一步只能移动到下一行中相邻的结点上。

例如，给定三角形：

[
[2],
[3,4],
[6,5,7],
[4,1,8,3]
]
自顶向下的最小路径和为 11（即，2 + 3 + 5 + 1 = 11）。

说明：

如果你可以只使用 O(n) 的额外空间（n 为三角形的总行数）来解决这个问题，那么你的算法会很加分。
 */
public class Triangle {

    int[][] tmp;
    List<List<Integer>> triangle;
    public int minimumTotal(List<List<Integer>> triangle) {

        int size = triangle.size();

        if (size == 1) {
            return triangle.get(0).get(0);
        }

        this.triangle = triangle;
        this.tmp = new int[size][size];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                this.tmp[i][j] = -1;
            }
        }
        return min(0, 0);
    }

    public int min(int deep, int weight) {
        if (deep == tmp.length - 1) {
            return triangle.get(deep).get(weight);
        }

        int t1 = tmp[deep + 1][weight];

        if (t1 == -1) {
            t1 = min(deep + 1, weight);
            tmp[deep + 1][weight] = t1;
        }

        int t2 = tmp[deep + 1][weight + 1];
        if (t2 == -1) {
            t2 = min(deep + 1, weight + 1);
            tmp[deep + 1][weight + 1] = t2;
        }

        return Math.min(t1, t2) + triangle.get(deep).get(weight);
    }

}
