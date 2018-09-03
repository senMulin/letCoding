package com.mulin.letcoding.tree.easy;

import com.mulin.letcoding.tree.TreeNode;

/**
 * Created by
 *
 * @author mulin
 * @date 2018/8/30
 * @description:

给定一个二叉树，判断它是否是高度平衡的二叉树。

本题中，一棵高度平衡二叉树定义为：

一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过1。

示例 1:

给定二叉树 [3,9,20,null,null,15,7]

      3
     / \
    9  20
  /  \
15   7
返回 true 。

示例 2:

给定二叉树 [1,2,2,3,3,null,null,4,4]

       1
      / \
     2   2
    / \
   3   3
  / \
4   4
返回 false 。
 */
public class BalancedBinaryTree {

    public boolean isBalanced(TreeNode root) {

        heightDiff(root);

        return heightDiff > 1 ? false : true;
    }

    int heightDiff = 0;

    public int heightDiff(TreeNode node) {
        if (null == node) {
            return 0;
        }

        int lh = heightDiff(node.left);
        int rh = heightDiff(node.right);

        heightDiff = heightDiff > Math.abs(lh - rh) ? heightDiff : Math.abs(lh - rh);

        return Math.max(lh, rh) + 1;
    }
}
