package com.mulin.letcoding.dynamicprograming.middle;

import java.util.*;

/**
 * Created by
 * @author mulin
 * @date 2018/9/25
 * @description:
给定一个整数数组 nums ，你可以对它进行一些操作。

每次操作中，选择任意一个 nums[i] ，删除它并获得 nums[i] 的点数。之后，你必须删除每个等于 nums[i] - 1 或 nums[i] + 1 的元素。

开始你拥有 0 个点数。返回你能通过这些操作获得的最大点数。

示例 1:

输入: nums = [3, 4, 2]
输出: 6
解释:
删除 4 来获得 4 个点数，因此 3 也被删除。
之后，删除 2 来获得 2 个点数。总共获得 6 个点数。
示例 2:

输入: nums = [2, 2, 3, 3, 3, 4]
输出: 9
解释:
删除 3 来获得 3 个点数，接着要删除两个 2 和 4 。
之后，再次删除 3 获得 3 个点数，再次删除 3 获得 3 个点数。
总共获得 9 个点数。
注意:

nums的长度最大为20000。
每个整数nums[i]的大小都在[1, 10000]范围内。

 */
public class DeleteAndEarn {

    Map<Integer, Integer> map = new TreeMap<>();

    int min, max;

    int[] dp;
    public int deleteAndEarn(int[] nums) {
        if(nums.length == 0) {
            return 0;
        } else if (nums.length == 1) {
            return nums[0];
        }

        min = max = nums[0];
        map.put(nums[0], nums[0]);

        for (int i = 1; i < nums.length; i++) {
            Integer val = map.get(nums[i]);
            val = val == null ? nums[i] : val+nums[i];

            map.put(nums[i], val);

            min = Math.min(min, nums[i]);
            max = Math.max(max, nums[i]);
        }

        this.dp = new int[max+1];


        return Max(max);
    }

    public int Max(int max) {
        if (max < min) {
            return 0;
        }else if (max == min) {
            return map.get(min);
        } else if (max == min + 1) {
            if (map.get(max) == null) {
                return map.get(min);
            } else {
                return Math.max(map.get(max), map.get(min));
            }
        }

        Integer m1 = map.get(max);
        Integer m2 = map.get(max - 1);

        int result = dp[max];

        if (result == 0) {
            if (m1 != null && m2 != null) {
                dp[max] = Math.max(Max(max-2) + m1, Max(max-3) + m2);
            } else if (m1 == null && m2 != null) {
                dp[max] =  Math.max(Max(max-2), Max(max-3) + m2);
            } else if (m2 == null && m1 != null){
                dp[max] =  Math.max(Max(max-2) + m1, Max(max-3));
            } else {
                dp[max] =  Math.max(Max(max-1), Max(max-2));
            }
        }

        result = dp[max];
        return result;
    }
//
//    public int delete(Map<Integer, Integer> map) {
//        if (map.isEmpty()) {
//            return 0;
//        }
//
//        int max = 0;
//
//        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
//            Map<Integer, Integer> newMap = new HashMap<>(map);
//
//            newMap.remove(entry.getKey());
//            newMap.remove(entry.getKey()+1);
//            newMap.remove(entry.getKey()-1);
//
//            int result = dp[entry.getKey()];
//            if (result == 0) {
//                result = delete(newMap);
//                dp[entry.getKey()] = result + entry.getValue();
//            } else {
//                result -= entry.getValue();
//            }
//
//            max = Math.max(max, result + entry.getValue());
//        }
//        return max;
//    }

//    public int deleteAndEarn(int[] nums) {
//        this.nums = nums;
//        this.dp = new int[nums.length];
//        this.visited = new int[nums.length];
//
//        for (int i = 0; i < nums.length; i++) {
//            List<Integer> idxs = map.get(nums[i]);
//            if (null == idxs) {
//                idxs = new ArrayList<>();
//            }
//
//            idxs.add(i);
//            map.put(nums[i], idxs);
//
//        }
//
//        return delete(nums.length, -1);
//    }
//
//    public int delete(int counts, int idx) {
//        if (counts <= 0) {
//            return 0;
//        }
//
//        int max = 0;
//
//        for (int i = 0; i < nums.length; i++) {
//
//            if (visited[i] == 1 || i < idx) {
//                continue;
//            }
//
//            visited[i] = 1;
//            int result = dp[i];
//
//            if (result == 0) {
//
//                List<Integer> subOne = map.get(nums[i] - 1);
//                List<Integer> addOne = map.get(nums[i] + 1);
//
//                List<Integer> op = new ArrayList<>();
//
//                if (null != subOne) {
//                    op.addAll(subOne);
//                }
//                if (null != addOne) {
//                    op.addAll(addOne);
//                }
//
//                List<Integer> opIdx = new ArrayList<>();
//                int opCount = 0;
//
//                if (op != null && op.size()>0) {
//                    for (Integer idxI : op) {
//                        if (visited[idxI] == 0) {
//                            opCount++;
//                            opIdx.add(idxI);
//                            visited[idxI] = 1;
//                        }
//                    }
//                }
//
//                int tmp = delete(counts - opCount - 1, i);
//
//                result = Math.max(result, tmp);
//
//                reset(opIdx);
//            }
//
//            visited[i] = 0;
//
//            dp[i] = nums[i] + result;
//            max = Math.max(max, dp[i]);
//        }
//
//        return max;
//    }
//
//    public void reset(List<Integer> idxs) {
//
//        for (Integer idx : idxs) {
//            visited[idx] = 0;
//        }
//    }

    public static void main(String[] args) {
        DeleteAndEarn d = new DeleteAndEarn();

//        int[] testcase = new int[]{12,32,93,17,100,72,40,71,37,92,58,34,29,78,11,84,77,90,92,
//                35,12,5,27,92,91,23,65,91,85,14,42,28,80,85,38,71,62,82,66,3,33,33,55,60,48,78,
//                63,11,20,51,78,42,37,21,100,13,60,57,91,53,49,15,45,19,51,2,96,22,32,2,46,62,58,
//                11,29,6,74,38,70,97,4,22,76,19,1,90,63,55,64,44,90,51,36,16,65,95,64,59,53,93};

//        int[] testcase = {2, 2, 3, 3, 3, 4};
//        int[] testcase = {8,3,4,7,6,6,9,2,5,8,2,4,9,5,9,1,5,7,1,4};

//        int[] testcase = {8,3,4,14,6,6,9,2,5,8,10,4,9,5,9,1,5,7,1,4};

//        int[] testcase = {1,1,1,2,4,5,5,5,6};
        int[] testcase = {3, 1};

//        int[] testcase = new int[]{8,3,4,7,6,6,9,2,5,8,2,4,9,5,9,1,5,7,1,4};
        System.out.println(d.deleteAndEarn(testcase));
    }
}
