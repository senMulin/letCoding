package com.mulin.letcoding.dynamicprograming.easy;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by
 *
 * @author mulin
 * @date 2018/9/12
 * @description:

如果一个数列至少有三个元素，并且任意两个相邻元素之差相同，则称该数列为等差数列。

例如，以下数列为等差数列:

1, 3, 5, 7, 9
7, 7, 7, 7
3, -1, -5, -9
以下数列不是等差数列。

1, 1, 2, 5, 7


数组 A 包含 N 个数，且索引从0开始。数组 A 的一个子数组划分为数组 (P, Q)，P 与 Q 是整数且满足 0<=P<Q<N 。

如果满足以下条件，则称子数组(P, Q)为等差数组：

元素 A[P], A[p + 1], ..., A[Q - 1], A[Q] 是等差的。并且 P + 1 < Q 。

函数要返回数组 A 中所有为等差数组的子数组个数。



示例:

A = [1, 2, 3, 4]

返回: 3, A 中有三个子等差数组: [1, 2, 3], [2, 3, 4] 以及自身 [1, 2, 3, 4]。
 */
public class ArithmeticSlices {


    int[] tmp;
    public int numberOfArithmeticSlices(int[] A) {

        int sum = 0;

        this.tmp = new int[A.length];

        if (A.length < 3) {
            return 0;
        }

        tmp[2] = 1;

        for (int i = 3; i < A.length; i++) {
            tmp[i] = tmp[i-1] + (i-1);
        }


        List<Integer> list = new LinkedList<>();

        int num = 0;

        for (int i = 1; i < A.length-1; i++) {
            if (A[i] - A[i - 1] == A[i + 1] - A[i]) {
                num++;
            } else {
                if (num > 0) {
                    list.add(num+2);
                }

                num = 0;
            }
        }

        if (num > 0) {
            list.add(num+2);
        }

        for (Integer i : list) {
            sum += tmp[i-1];
        }

        return sum;
    }


}
