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
给定一个二叉搜索树, 找到该树中两个指定节点的最近公共祖先。

百度百科中最近公共祖先的定义为：“对于有根树 T 的两个结点 p、q，最近公共祖先表示为一个结点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。”

例如，给定如下二叉搜索树:  root = [6,2,8,0,4,7,9,null,null,3,5]

       _______6______
      /              \
  ___2__          ___8__
 /      \        /      \
0      _4       7       9
      /  \
     3   5

示例 1:

输入: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 8
输出: 6
解释: 节点 2 和节点 8 的最近公共祖先是 6。
示例 2:

输入: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 4
输出: 2
解释: 节点 2 和节点 4 的最近公共祖先是 2, 因为根据定义最近公共祖先节点可以为节点本身。
说明:

所有节点的值都是唯一的。
p、q 为不同节点且均存在于给定的二叉搜索树中。
 */
public class LowestCommonAncestorOfABinarySearchTree {
    public static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (null == root) {
            return null;
        }

        List<TreeNode> pl = path(root, p.val);
        List<TreeNode> ql = path(root, q.val);

        TreeNode result = root;
        int i = 0;
        while (i < pl.size() && i < ql.size()) {
            if (pl.get(i).val == ql.get(i).val) {
                result = pl.get(i);
                i++;

            } else {
                break;
            }
        }

        return result;
    }

    public static List<TreeNode> path(TreeNode root, int val) {
        List<TreeNode> list = new ArrayList();
        TreeNode node = root;

        while (null != node) {
            list.add(node);

            if (node.val < val) {
                node = node.right;
            } else if (node.val > val) {
                node = node.left;
            } else {
                break;
            }
        }

        return list;
    }



    public static void main(String[] args) {
        TreeNode n3 = new TreeNode(3);
        TreeNode n5 = new TreeNode(5);
        TreeNode n4 = new TreeNode(4);
        n4.left = n3;
        n4.right = n5;

        TreeNode n0 = new TreeNode(0);
        TreeNode n2 = new TreeNode(2);
        n2.left = n0;
        n2.right = n4;

        TreeNode n7 = new TreeNode(7);
        TreeNode n9 = new TreeNode(9);

        TreeNode n8 = new TreeNode(8);
        n8.left = n7;
        n8.right = n9;

        TreeNode n6 = new TreeNode(6);
        n6.left = n2;
        n6.right = n8;

        lowestCommonAncestor(n6, n2, n8);
    }
}
