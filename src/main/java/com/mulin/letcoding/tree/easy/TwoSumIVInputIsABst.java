package com.mulin.letcoding.tree.easy;

import com.mulin.letcoding.tree.TreeNode;

/**
 * Created by
 *
 * @author mulin
 * @date 2018/8/27
 * @description:
给定一个二叉搜索树和一个目标结果，如果 BST 中存在两个元素且它们的和等于给定的目标结果，则返回 true。

案例 1:

输入:
    5
   / \
  3   6
 / \   \
2   4   7

Target = 9

输出: True


案例 2:

输入:
    5
   / \
  3   6
 / \   \
2   4   7

Target = 28

输出: False

 */
public class TwoSumIVInputIsABst {

    static boolean flag = false;

    public static boolean findTarget(TreeNode root, int k) {
        find(root, root, k);
        return flag;
    }

    public static void find(TreeNode node, TreeNode root, int k) {
        if (null == node || flag) {
            return;
        }

        int target = k - node.val;

        TreeNode tmp = root;

        while (tmp != null) {
            if (node == tmp) {
                break;
            }

            if (target < tmp.val) {
                tmp = tmp.left;
            } else if (target > tmp.val) {
                tmp = tmp.right;
            } else {
                flag = true;
                break;
            }
        }

        find(node.left, root, k);
        find(node.right, root, k);
    }

    public static void main(String[] args) {
        TreeNode n1 = new TreeNode(1);

        TreeNode n3 = new TreeNode(3);


        TreeNode n2 = new TreeNode(2);
        n2.left = n1;
        n2.right = n3;

        findTarget(n2, 4);
    }
}
