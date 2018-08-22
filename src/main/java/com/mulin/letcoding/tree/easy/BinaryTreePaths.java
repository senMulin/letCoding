package com.mulin.letcoding.tree.easy;

import com.mulin.letcoding.tree.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by
 *
 * @author mulin
 * @date 2018/8/22
 * @description:

给定一个二叉树，返回所有从根节点到叶子节点的路径。

说明: 叶子节点是指没有子节点的节点。

示例:

输入:

    1
  /   \
 2     3
  \
   5

输出: ["1->2->5", "1->3"]

解释: 所有根节点到叶子节点的路径为: 1->2->5, 1->3
 */
public class BinaryTreePaths {
    public static List<String> binaryTreePaths(TreeNode root) {
        List<String> list = new ArrayList();
        path(root, "", list);

        return list;
    }

    public static void path(TreeNode node, String path, List<String> list) {
        if (null == node) {
            return;
        }

        path = path + "->" + node.val;

        if (node.left == null && node.right == null) {
            list.add(path.substring(2, path.length()));
            return;
        }

        path(node.left, path, list);
        path(node.right, path, list);
    }

    public static void main(String[] args) {
        TreeNode n5 = new TreeNode(5);

        TreeNode n2 = new TreeNode(2);
        n2.right = n5;

        TreeNode n3 = new TreeNode(3);

        TreeNode n1 = new TreeNode(1);
        n1.left = n2;
        n1.right = n3;

        binaryTreePaths(n1);
    }


}
