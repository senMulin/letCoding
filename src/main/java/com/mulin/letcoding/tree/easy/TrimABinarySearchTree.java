package com.mulin.letcoding.tree.easy;

import com.mulin.letcoding.tree.TreeNode;

/**
 * Created by
 *
 * @author mulin
 * @date 2018/8/20
 * @description:
给定一个二叉搜索树，同时给定最小边界L 和最大边界 R。通过修剪二叉搜索树，使得所有节点的值在[L, R]中 (R>=L) 。
你可能需要改变树的根节点，所以结果应当返回修剪好的二叉搜索树的新的根节点。

示例 1:

输入:
  1
 / \
0   2

L = 1
R = 2

输出:
  1
   \
    2
示例 2:

输入:
     3
    / \
   0   4
    \
    2
   /
  1

L = 1
R = 3

输出:
      3
     /
    2
   /
  1
 */
public class TrimABinarySearchTree {

    public static TreeNode trimBST(TreeNode root, int L, int R) {
        if (null == root) {
            return root;
        }

        return trim(root, null, L, R);
    }

    public static TreeNode trim(TreeNode node, TreeNode parent, int L, int R) {
        if (node == null) {
            return  null;
        }

        if (node.val < L || node.val > R) {
            node = delete(node, parent);

            if (node == null) {
                return null;
            } else {
               node = trim(node, parent, L, R);
            }
        } else {
            trim(node.left, node, L, R);
            trim(node.right, node, L, R);
        }

        return node;
    }

    public static TreeNode delete(TreeNode node, TreeNode parent) {
        if (node == null) {
            return null;
        }

        TreeNode tmp = node, left = node.left, right = node.right;

        if (left == null && right == null) {
            tmp = null;
        } else if (left == null && right != null) {
            tmp = right;
        } else if (left != null && right == null) {
            tmp = left;
        } else {
            /** 将左子节点 拼到 右子节点的最左子节点上**/
            tmp = right;

            while (right.left != null) {
                right = right.left;
            }

            right.left = left;
        }

        if (null == parent) {
            return tmp;
        }

        if (node.val < parent.val) {
            parent.left = tmp;
        } else {
            parent.right = tmp;
        }

        return tmp;
    }

//   [3,1,4,null,2]
//1
//2

    public static void main(String[] args) {
        TreeNode n2 = new TreeNode(2);
        TreeNode n1 = new TreeNode(1);

        n1.left = null;
        n1.right = n2;

        TreeNode n4 = new TreeNode(4);
        TreeNode n3 = new TreeNode(3);

        n3.left = n1;
        n3.right = n4;

        int L = 3;
        int R = 4;

       TreeNode node = trimBST(n3, L, R);

        System.out.println("");
    }
}
