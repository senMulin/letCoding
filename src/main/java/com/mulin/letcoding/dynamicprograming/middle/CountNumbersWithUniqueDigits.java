package com.mulin.letcoding.dynamicprograming.middle;

/**
 * Created by
 *
 * @author mulin
 * @date 2018/10/22
 * @description:
给定一个非负整数 n，计算各位数字都不同的数字 x 的个数，其中 0 ≤ x < 10^n 。

示例:

输入: 2
输出: 91
解释: 答案应为除去 11,22,33,44,55,66,77,88,99 外，在 [0,100) 区间内的所有数字。
 */
public class CountNumbersWithUniqueDigits {

    public int countNumbersWithUniqueDigits(int n) {
        int ans=0;
        int temp=1;
        int result=9;
        int i=n;

        while(i!=0)
        {
            temp *= result--;
            i--;
        }

        ans += temp;

        while(n!=0){

            temp=1;
            result=9;
            i=n-1;

            while(i--!=0)
            {
                temp*=result--;

            }

            ans+=(n*temp);
            n--;
        }
        return ans;

    }
}
