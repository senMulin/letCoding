package com.mulin.letcoding.tree.easy;

import com.mulin.letcoding.tree.TreeNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by
 *
 * @author mulin
 * @date 2018/9/3
 * @description:
给定一个二叉树，它的每个结点都存放着一个整数值。

找出路径和等于给定数值的路径总数。

路径不需要从根节点开始，也不需要在叶子节点结束，但是路径方向必须是向下的（只能从父节点到子节点）。

二叉树不超过1000个节点，且节点数值范围是 [-1000000,1000000] 的整数。

示例：

root = [10,5,-3,3,2,null,11,3,-2,null,1], sum = 8

       10
      /  \
     5   -3
    / \    \
   3   2   11
  / \   \
 3  -2   1

返回 3。和等于 8 的路径有:

1.  5 -> 3
2.  5 -> 2 -> 1
3.  -3 -> 11
 */
public class PathSumIII {
    int count = 0;
    List<Integer> nodes = new ArrayList<>();

    public int pathSum(TreeNode root, int sum) {
        recursive(root, sum);

        return count;
    }

    public void recursive(TreeNode node, int sum) {
        if (null == node) {
            return;
        }

        nodes.add(node.val);

        int val = 0;

        for (int i = 0; i < nodes.size(); i++) {
            val += nodes.get(nodes.size() - 1 - i);
            if (val == sum) {
                count++;
            }
        }

        recursive(node.left, sum);
        recursive(node.right, sum);

        nodes.remove(nodes.size() - 1);
    }

    /**
     * 动态规划解法
     * @param root
     * @param sum
     * @return
     */
    public int dpPathSum(TreeNode root, int sum) {
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        //这个值的个数
        map.put(0, 1);
        return inOrder(root, 0, map,sum);
    }

    int inOrder(TreeNode node, int presum, Map<Integer, Integer> map,int target) {
        if (node == null) {
            return 0;
        }
        int res=0;
        int cur=presum+node.val;

        res+=map.getOrDefault(cur-target, 0);
        map.put(cur, map.getOrDefault(cur, 0) + 1);
        res+=inOrder(node.left, cur, map, target);
        res+=inOrder(node.right,cur,map,target);
        map.put(cur, map.get(cur)-1);
        return res;
    }

}
