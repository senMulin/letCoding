package com.mulin.letcoding.tree.easy;

import com.mulin.letcoding.tree.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by
 * @author mulin
 * @date 2018/8/30
 * @description:
给定一个二叉树，检查它是否是镜像对称的。

例如，二叉树 [1,2,2,3,4,4,3] 是对称的。

    1
   / \
  2   2
 / \ / \
3  4 4  3

但是下面这个 [1,2,2,null,3,null,3] 则不是镜像对称的:

    1
   / \
  2   2
   \   \
    3    3
 */
public class SymmetricTree {

    List<Integer> leaves = new ArrayList<>();

    public boolean isSymmetric(TreeNode root) {
        if (null != root && root.left != null && root.right != null && root.left.val != root.right.val) {
            return false;
        }

        recusive(root);
        for (int i = 0; i < leaves.size(); i++) {
            if (!leaves.get(i).equals(leaves.get(leaves.size()-1-i))) {
                return false;
            }
        }
        return true;
    }

    public void recusive(TreeNode node) {
        if (null == node) {
            return ;
        }

        recusive(node.left);

        leaves.add(node.val);

        recusive(node.right);
    }
}

