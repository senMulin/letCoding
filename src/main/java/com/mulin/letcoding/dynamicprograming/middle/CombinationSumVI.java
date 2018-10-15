package com.mulin.letcoding.dynamicprograming.middle;

import java.util.Arrays;

/**
 * Created by
 *
 * @author mulin
 * @date 2018/10/15
 * @description:
给定一个由正整数组成且不存在重复数字的数组，找出和为给定目标正整数的组合的个数。

示例:

nums = [1, 2, 3]
target = 4

所有可能的组合为：
(1, 1, 1, 1)
(1, 1, 2)
(1, 2, 1)
(1, 3)
(2, 1, 1)
(2, 2)
(3, 1)

请注意，顺序不同的序列被视作不同的组合。

因此输出为 7。
 */
public class CombinationSumVI {

    int[] dp;
    int[] nums;
    int min = Integer.MAX_VALUE;
    int max = Integer.MIN_VALUE;

    public int combinationSum4(int[] nums, int target) {

        if (nums.length == 0) {
            return  0;
        }
        this.nums = nums;
        this.dp = new int[target + 1];

        Arrays.sort(nums);
        min = nums[0];
        max = nums[nums.length-1];

        if (min > target) {
            return 0;
        }

        for (int i = 0; i < target+1; i++) {
            dp[i] = -1;
        }

        for (int i : nums) {
            if (i <= target) {
                dp[i] = combination(i) + 1;
            }
        }

        return dp[target] != -1 ? dp[target] : combination(target);
    }

    public int combination(int target) {

        if (target < min) {
            return 0;
        }

        int total = 0;

        for (int i : nums) {

            if (i <= target) {
                int t = target - i;

                if (t >= min) {
                    int n = dp[t];

                    if (n == -1) {
                        n = combination(t);
                    }
                    dp[t] = n;

                    total += n;
                }
            }

        }

        return total;
    }

    public static void main(String[] args) {
        CombinationSumVI c = new CombinationSumVI();

        int[] nums = new int[]{2,1,3};
        int target = 35;

        System.out.println(c.combinationSum4(nums, target));
        System.out.println();
    }
}
