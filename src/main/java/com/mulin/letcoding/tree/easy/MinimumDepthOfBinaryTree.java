package com.mulin.letcoding.tree.easy;

import com.mulin.letcoding.tree.TreeNode;

/**
 * Created by
 *
 * @author mulin
 * @date 2018/9/4
 * @description:

给定一个二叉树，找出其最小深度。

最小深度是从根节点到最近叶子节点的最短路径上的节点数量。

说明: 叶子节点是指没有子节点的节点。

示例:

给定二叉树 [3,9,20,null,null,15,7],

    3
   / \
  9  20
    /  \
   15   7
返回它的最小深度  2.
 */
public class MinimumDepthOfBinaryTree {
    public int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int l = minDepth(root.left);
        int r = minDepth(root.right);

        if (l > 0 && r > 0) {
            return l > r ? r + 1 : l + 1;
        } else if (l == 0 && r > 0) {
            return r + 1;
        } else if (r == 0 && l > 0) {
            return l + 1;
        } else {
            return 1;
        }
    }
}
