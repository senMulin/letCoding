package com.mulin.letcoding.tree.easy;

import com.mulin.letcoding.tree.TreeNode;

import java.util.LinkedList;

/**
 * Created by
 *
 * @author mulin
 * @date 2018/9/4
 * @description:
给定一个二叉树，找到最长的路径，这个路径中的每个节点具有相同值。 这条路径可以经过也可以不经过根节点。

注意：两个节点之间的路径长度由它们之间的边数表示。

示例 1:

输入:

     5
    / \
   4   5
  / \   \
 1   1   5
输出:

2
示例 2:

输入:

     1
    / \
   4   5
  / \   \
 4   4   5
输出:

2
注意: 给定的二叉树不超过10000个结点。 树的高度不超过1000。
 */
public class LongestUnivaluePath {

    int max = 0;

    public int longestUnivaluePath(TreeNode root) {
        if (root == null) {
            return 0;
        }

        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            TreeNode tmp = queue.poll();

            if (tmp.left != null) {
                queue.offer(tmp.left);
            }

            if (tmp.right != null) {
                queue.offer(tmp.right);
            }

           int count = count(tmp);
            max = count > max ? count : max;
        }

        return max;
    }

    public int count(TreeNode node) {
        if (node == null) {
            return 0 ;
        }

        int l = countChild(node.left, node);
        int r = countChild(node.right, node);

        return l+r;
    }

    public int countChild(TreeNode node, TreeNode parent) {
        if (node == null) {
            return 0;
        }

        if (parent.val == node.val) {
            return Math.max(countChild(node.left, node), countChild(node.right, node)) + 1;
        } else {
            return 0;
        }
    }
}
