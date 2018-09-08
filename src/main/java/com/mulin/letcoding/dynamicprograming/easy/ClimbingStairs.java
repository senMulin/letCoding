package com.mulin.letcoding.dynamicprograming.easy;

/**
 * Created by
 *
 * @author mulin
 * @date 2018/9/7
 * @description:
假设你正在爬楼梯。需要 n 阶你才能到达楼顶。

每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？

注意：给定 n 是一个正整数。

示例 1：

输入： 2
输出： 2
解释： 有两种方法可以爬到楼顶。
1.  1 阶 + 1 阶
2.  2 阶
示例 2：

输入： 3
输出： 3
解释： 有三种方法可以爬到楼顶。
1.  1 阶 + 1 阶 + 1 阶
2.  1 阶 + 2 阶
3.  2 阶 + 1 阶

 */
public class ClimbingStairs {

    static int tmp[];
    public static int climbStairs(int n) {
        tmp = new int[n + 1];
        return fibonacci(n);

    }

    public static int fibonacci(int n) {
        if (n == 1) {
            return 1;
        } else if (n == 2) {
            return 2;
        } else {
            if (tmp[n] != 0) {
                return tmp[n];
            } else {

                int t = fibonacci(n-1) + fibonacci(n-2);
                tmp[n] = t;
                return t;
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(climbStairs(44));
    }

}

