package com.mulin.letcoding.tree.easy;

import com.mulin.letcoding.tree.TreeNode;

/**
 * Created by
 *
 * @author mulin
 * @date 2018/9/2
 * @description:
给定一个非空特殊的二叉树，每个节点都是正数，并且每个节点的子节点数量只能为 2 或 0。如果一个节点有两个子节点的话，那么这个节点的值不大于它的子节点的值。

给出这样的一个二叉树，你需要输出所有节点中的第二小的值。如果第二小的值不存在的话，输出 -1 。

示例 1:

输入:
   2
  / \
 2   5
    / \
   5   7

输出: 5
说明: 最小的值是 2 ，第二小的值是 5 。
示例 2:

输入:
  2
 / \
2   2

输出: -1
说明: 最小的值是 2, 但是不存在第二小的值。

 */
public class SecondMinimumNodeInABinaryTree {

    int root;

    public int findSecondMinimumValue(TreeNode root) {
        if (null == root) {
            return -1;
        }

        this.root = root.val;

        int val = recursive(root);
        return val == this.root ? -1 : val;
    }

    public int recursive(TreeNode node) {
       Integer l = null;
       Integer r = null;

       if (null != node.left) {
           l = recursive(node.left);
       }

       if (null != node.right) {
           r = recursive(node.right);
       }

       if (l != null && r != null) {

           if (node.val != root) {
               return node.val;
           } else if (l != root && r == root) {
               return l;
           } else if (l == root && r != root) {
               return r;
           } else if (l >= r) {
               return r;
           } else if (r > l){
               return l;
           } else {
               return node.val;
           }
       } else if (l != null && r == null) {
           if (node.val != root) {
               return node.val;
           } else if (l != root) {
               return l;
           } else {
               return node.val;
           }
       } else if (r != null && l == null) {
           if (node.val != root) {
               return node.val;
           } else if (r != root) {
               return r;
           } else {
               return node.val;
           }
       } else {
           return node.val;
       }
    }

}
