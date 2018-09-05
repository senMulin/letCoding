package com.mulin.letcoding.other;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by
 * @author mulin
 * @date 2018/9/4
 * @description:
在一个矩阵分布的地图中,有 M * N 个地点,其中包含K个仓库.
从仓库出发送货物需要沿东西或南北移动(不可走斜线),
移动一次距离加1, 请实现下面方法, 计算每个地点到最近仓库的距离.

int[][] function(int m, int n, int[] Kx, int[] Ky)

参数说明:
m, n 代表东西和南北方向的地点数
Kx 代表仓库在矩阵中的横坐标数组
Ky 代表仓库在矩阵中的纵坐标数组
返回值为 二维数组, 标识 M * N 个点没个点到最近仓库的距离.

例 m = 3, n = 3, Kx = {0}, Ky = {0}, 只有一个仓库的情况下, 返回结果如下
0, 1, 2
1, 2, 3
2, 3, 4
 */
public class Logistics {

    static int[][] path;

    public static int[][] closestDistance(int m, int n, int[] Kx, int[] Ky) {
        int[][] matrix = new int[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = 1;
            }
        }

        for (int i = 0; i < Kx.length; i++) {
            int kx = Kx[i];
            int ky = Ky[i];

            matrix[kx][ky] = 0;
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {

                if (matrix[i][j] != 0) {
                    path = new int[m][n];

                    matrix[i][j] = dfs(i, j, m-1, n-1, matrix);
                }
            }
        }

        return matrix;
    }

    public static Integer dfs(int i, int j, int m, int n, int[][] matrix) {

        Integer up = -1, down = -1, left = -1, right = -1;

        if (matrix[i][j] == 0) {
            return 0;
        }

        if (path[i][j] == 1) {
            return -1;
        }

        path[i][j] = 1;

        if (i < m) {
            down = dfs(i + 1, j, m, n, matrix);
        }
        if (i > 0) {
            up = dfs(i - 1, j, m, n, matrix);
        }
        if (j < n) {
            right = dfs(i, j + 1, m, n, matrix);
        }
        if (j > 0){
            left = dfs(i, j - 1, m, n, matrix);
        }

        path[i][j] = 0;

        int closest = Integer.MAX_VALUE;

        List<Integer> direction = new ArrayList<>(4);

        if (-1 != up) {
            direction.add(up);
        }
        if (-1 != down) {
            direction.add(down);
        }
        if (-1 != left) {
            direction.add(left);
        }
        if (-1 != right) {
            direction.add(right);
        }

        for (Integer in : direction) {
            closest = in < closest ? in : closest;
        }

        if (closest == Integer.MAX_VALUE) {
            return -1;
        }

        return closest + 1;
    }

    public static void main(String[] args) {
        int m = 3, n = 3;
        int[] Kx = {0, 2}, Ky = {0, 2};

        int[][] result = closestDistance(m, n, Kx, Ky);

        for (int i = 0; i < result.length; i++) {
            for (int j = 0; j < result[0].length; j++) {
                System.out.print(result[i][j]+" ");
            }
            System.out.println("\n");
        }
    }
}
