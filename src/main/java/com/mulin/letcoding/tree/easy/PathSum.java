package com.mulin.letcoding.tree.easy;

import com.mulin.letcoding.tree.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by
 *
 * @author mulin
 * @date 2018/9/3
 * @description:
给定一个二叉树和一个目标和，判断该树中是否存在根节点到叶子节点的路径，这条路径上所有节点值相加等于目标和。

说明: 叶子节点是指没有子节点的节点。

示例:
给定如下二叉树，以及目标和 sum = 22，

       5
      / \
     4   8
    /   / \
   11  13  4
  /  \      \
 7    2      1

返回 true, 因为存在目标和为 22 的根节点到叶子节点的路径 5->4->11->2。
 */
public class PathSum {

    boolean flag = false;

    List<Integer> list = new ArrayList<>();

    public boolean hasPathSum(TreeNode root, int sum) {

        pathSum(root, sum);

        return flag;
    }

    public void pathSum(TreeNode node, int sum) {
        if (null == node) {
            return;
        }

        list.add(node.val);
        if (null == node.left && null == node.right) {
            int val  = 0;

            for (Integer i : list) {
                val += i;
            }

            if (val == sum) {
                flag = true;
            }
        }

        pathSum(node.left, sum);
        pathSum(node.right, sum);

        list.remove(list.size() - 1);
    }

}
