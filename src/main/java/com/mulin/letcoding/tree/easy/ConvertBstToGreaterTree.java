package com.mulin.letcoding.tree.easy;

import com.mulin.letcoding.tree.TreeNode;

/**
 * Created by
 *
 * @author mulin
 * @date 2018/8/26
 * @description:

给定一个二叉搜索树（Binary Search Tree），把它转换成为累加树（Greater Tree)，使得每个节点的值是原来的节点值加上所有大于它的节点值之和。

例如：

输入: 二叉搜索树:
   5
 /   \
2     13

输出: 转换为累加树:
   18
  /   \
20     13
 */
public class ConvertBstToGreaterTree {

    static int sum = 0;

    public static TreeNode convertBST(TreeNode root) {
        covert(root);
        return root;
    }


    public static void covert(TreeNode node) {
        if (null == node) {
            return;
        }

        covert(node.right);
        node.val += sum;
        sum = node.val;
        covert(node.left);
    }

    public static void main(String[] args) {
        TreeNode n2 = new TreeNode(2);

        TreeNode n1 = new TreeNode(1);

        TreeNode n3 = new TreeNode(3);
        n2.left = n1;
        n2.right = n3;

        convertBST(n2);
    }
}
