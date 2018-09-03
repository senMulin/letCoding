package com.mulin.letcoding.tree.easy;

import com.mulin.letcoding.tree.TreeNode;

/**
 * Created by
 *
 * @author mulin
 * @date 2018/8/30
 * @description:
 * 计算给定二叉树的所有左叶子之和。

  示例：

      3
     / \
    9  20
  /  \
15   7

在这个二叉树中，有两个左叶子，分别是 9 和 15，所以返回 24

 */
public class SumOfLeftLeaves {
    int sum = 0;

    public int sumOfLeftLeaves(TreeNode root) {
        sum(root, null);

        return sum;
    }

    public void sum(TreeNode node, TreeNode parent) {
        if (null == node) {
            return;
        }

        if (node.left == null &&
                node.right == null &&
                null != parent && parent.left == node) {
            sum += node.val;
        }

        sum(node.left, node);
        sum(node.right, node);
    }

}
