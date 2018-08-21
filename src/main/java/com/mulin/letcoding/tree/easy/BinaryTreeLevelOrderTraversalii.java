package com.mulin.letcoding.tree.easy;

import com.mulin.letcoding.tree.Node;
import com.mulin.letcoding.tree.TreeNode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by
 *
 * @author mulin
 * @date 2018/8/21
 * @description:
给定一个二叉树，返回其节点值自底向上的层次遍历。 （即按从叶子节点所在层到根节点所在的层，逐层从左向右遍历）

例如：
给定二叉树 [3,9,20,null,null,15,7],

    3
   / \
  9  20
    /  \
   15   7
返回其自底向上的层次遍历为：

[
[15,7],
[9,20],
[3]
]
 */
public class BinaryTreeLevelOrderTraversalii {

    public static List<List<Integer>> levelOrderBottom(TreeNode root) {

        List<List<Integer>> result = new ArrayList<List<Integer>>();
        lob(root, 0, result);

        for (int i = 0; i < result.size()/2; i++) {
            List<Integer> tmp = new ArrayList<Integer>(result.get(i));

            result.set(i, result.get(result.size() - 1 - i));
            result.set(result.size() - 1 - i, tmp);
        }

        return result;
    }

    public static void lob(final TreeNode node, int idx, List<List<Integer>> lists) {

        if (null == node) {
            return;
        }

        if (lists.size() <= idx) {
            lists.add(new ArrayList<Integer>(){{add(node.val);}});
        } else {
            List<Integer> list = lists.get(idx);
            list.add(node.val);
        }

        lob(node.left, idx + 1, lists);
        lob(node.right, idx + 1, lists);

    }


    /**
     *
       3
      / \
     9  20
      /  \
     15   7
      @param args
     */
    public static void main(String[] args) {
        TreeNode n7 = new TreeNode(7);

        TreeNode n15 = new TreeNode(15);

        TreeNode n20 = new TreeNode(20);
        n20.left = n15;
        n20.right = n7;

        TreeNode n9 = new TreeNode(9);

        TreeNode n3 = new TreeNode(3);
        n3.left = n9;
        n3.right = n20;


        levelOrderBottom(n3);
    }
}
