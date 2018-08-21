package com.mulin.letcoding.tree.easy;

import com.mulin.letcoding.tree.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by
 *
 * @author mulin
 * @date 2018/8/21
 * @description:

请考虑一颗二叉树上所有的叶子，这些叶子的值按从左到右的顺序排列形成一个 叶值序列 。
      3
   /     \
  5      1
 / \    / \
6  2   9  8
  / \
 7  4

举个例子，如上图所示，给定一颗叶值序列为 (6, 7, 4, 9, 8) 的树。

如果有两颗二叉树的叶值序列是相同，那么我们就认为它们是 叶相似 的。

如果给定的两个头结点分别为 root1 和 root2 的树是叶相似的，则返回 true；否则返回 false 。
 */

public class LeafSimilarTrees {

    public static boolean leafSimilar(TreeNode root1, TreeNode root2) {

        List<Integer> l1 = leaf(root1);
        List<Integer> l2 = leaf(root2);

        if (l1.size() != l2.size()) {
            return false;
        }

        for (int i = 0; i < l1.size(); i++) {
            if (!l1.get(i).equals(l2.get(i))) {
                return false;
            }
        }

        return true;
    }

    public static List<Integer> leaf(TreeNode node) {
        List<Integer> leafs = new ArrayList<Integer>();

        if (node == null) {
            return new ArrayList<Integer>();
        }

        leafs.addAll(leaf(node.left));
        leafs.addAll(leaf(node.right));

        if (node.left == null && node.right == null) {
            leafs.add(node.val);
        }

        return leafs;
    }

    public static void main(String[] args) {
        TreeNode n13 = new TreeNode(3);
        TreeNode n12 = new TreeNode(2);
        n12.left = n13;
        TreeNode n11 = new TreeNode(1);
        n11.left = n12;

        TreeNode n23 = new TreeNode(3);
        TreeNode n21 = new TreeNode(1);
        n21.left = n23;

        Boolean flag = leafSimilar(n11, n21);

        System.out.println(flag);
    }

}
