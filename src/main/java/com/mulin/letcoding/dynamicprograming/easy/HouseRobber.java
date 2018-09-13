package com.mulin.letcoding.dynamicprograming.easy;

/**
 * Created by
 * @author mulin
 * @date 2018/9/10
 * @description:
你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。

给定一个代表每个房屋存放金额的非负整数数组，计算你在不触动警报装置的情况下，能够偷窃到的最高金额。

示例 1:

输入: [1,2,3,1]
输出: 4
解释: 偷窃 1 号房屋 (金额 = 1) ，然后偷窃 3 号房屋 (金额 = 3)。
偷窃到的最高金额 = 1 + 3 = 4 。
示例 2:

输入: [2,7,9,3,1]
输出: 12
解释: 偷窃 1 号房屋 (金额 = 2), 偷窃 3 号房屋 (金额 = 9)，接着偷窃 5 号房屋 (金额 = 1)。
偷窃到的最高金额 = 2 + 9 + 1 = 12 。
 */
public class HouseRobber {

    int[] nums;
    int[] maxRobs;
    public int rob(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        this.nums = nums;
        this.maxRobs = new int[nums.length];

        return maxRob(nums.length + 1);

    }

    public int maxRob(int step) {
        if (step == 0) {
            return nums[0];
        } else if (step == 1) {
            return nums[1];
        } else if (step == 2) {
            return nums[0] + nums[2];
        } else {
            int s2 = maxRobs[step - 2];

            if (s2 == 0) {
                s2 = maxRob(step - 2);
                maxRobs[step - 2] = s2;
            }

            int s3 = maxRobs[step - 3];

            if (s3 == 0) {
                s3 = maxRob(step - 3);
                maxRobs[step - 3] = s3;
            }

            return step == nums.length + 1 ? Math.max(s2, s3) : Math.max(s2, s3) + nums[step];
        }
    }
}
