package com.mulin.letcoding.tree.easy;

import com.mulin.letcoding.tree.TreeNode;

/**
 * Created by mulin on 2018/8/15.
 *
 翻转一棵二叉树。

 示例：

 输入：

      4
    /   \
   2     7
  / \   / \
 1   3 6   9
 输出：

      4
    /   \
   7     2
  / \   / \
 9   6 3   1
 */
public class InvertBinaryTree {
    public TreeNode invertTree(TreeNode root) {
        if (null == root) {
            return root;
        }
        invert(root);
        return root;
    }

    /**
     * 递归调用左子节点与有子节点交换
     * @param root
     */
    public void invert(TreeNode root) {
        if (null == root.left && null == root.right) {
            return;
        }

        TreeNode tmp = null;

        tmp = root.left;
        root.left = root.right;
        root.right = tmp;

        if (null == root.left && null != root.right) {
            invertTree(root.right);
        } else if (null != root.left && null == root.right) {
            invertTree(root.left);
        } else {
            invertTree(root.left);
            invertTree(root.right);
        }
    }
}
