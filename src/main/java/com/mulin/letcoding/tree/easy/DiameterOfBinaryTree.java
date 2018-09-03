package com.mulin.letcoding.tree.easy;

import com.mulin.letcoding.tree.TreeNode;

/**
 * Created by
 *
 * @author mulin
 * @date 2018/9/3
 * @description:

给定一棵二叉树，你需要计算它的直径长度。一棵二叉树的直径长度是任意两个结点路径长度中的最大值。这条路径可能穿过根结点。

示例 :
给定二叉树

    1
   / \
  2   3
 / \
4   5
返回 3, 它的长度是路径 [4,2,1,3] 或者 [5,2,1,3]。

注意：两结点之间的路径长度是以它们之间边的数目表示。
 */
public class DiameterOfBinaryTree {

    int diameter = 0;

    public int diameterOfBinaryTree(TreeNode root) {
        maxPath(root);

        return diameter;
    }

    public int maxPath(TreeNode node) {
        if (null == node) {
            return 0;
        }

        int l = maxPath(node.left);
        int r = maxPath(node.right);

        diameter = diameter > (l + r) ? diameter : (l + r);

        return Math.max(l, r) + 1;
    }

}
