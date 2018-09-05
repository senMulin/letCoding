package com.mulin.letcoding.tree.easy;

import com.mulin.letcoding.tree.TreeNode;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by
 *
 * @author mulin
 * @date 2018/9/4
 * @description:
给定两个非空二叉树 s 和 t，检验 s 中是否包含和 t 具有相同结构和节点值的子树。
s 的一个子树包括 s 的一个节点和这个节点的所有子孙。s 也可以看做它自身的一棵子树。

示例 1:
给定的树 s:

     3
    / \
   4   5
  / \
 1   2
给定的树 t：

   4
  / \
 1   2
返回 true，因为 t 与 s 的一个子树拥有相同的结构和节点值。

示例 2:
给定的树 s：

       3
      / \
     4   5
    / \
   1   2
  /
 0
给定的树 t：

   4
  / \
 1   2
返回 false。
 */

public class SubtreeOfAnotherTree {
    List<List<Integer>> lists = new LinkedList();
    List<TreeNode> treeList = new LinkedList();

    public boolean isSubtree(TreeNode s, TreeNode t) {
        if (null == s || null == t) {
            return false;
        }
        recursives(s, t.val);
        tree2List();

        List<Integer> tList = new LinkedList();
        inOrderTraversal(t, tList);

        for (List<Integer> list : lists) {
            if (list.size() != tList.size()) {
                continue;
            }
            boolean flag = true;

            for (int i = 0; i < tList.size(); i++) {
                if (!list.get(i).equals(tList.get(i))) {
                    flag = false;
                }
            }

            if (flag) {
                return true;
            }
        }

        return false;
    }

    public void recursives(TreeNode node, int val) {
        if (null == node) {
            return;
        }

        if (node.val == val) {
            treeList.add(node);
        }
        recursives(node.left, val);
        recursives(node.right, val);
    }

    public void tree2List() {
        for (TreeNode node : treeList) {
            List<Integer> list = new LinkedList();
            inOrderTraversal(node, list);
            lists.add(list);
        }
    }

    public void inOrderTraversal(TreeNode node, List<Integer> list) {
        if (null == node) {
            return;
        }

        inOrderTraversal(node.left, list);
        list.add(node.val);
        inOrderTraversal(node.right, list);
    }


    /**
     * 另一种解法
     * @param s
     * @param t
     * @return
     */
    public boolean isSubtree2(TreeNode s, TreeNode t) {
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.offer(s);

        while (!queue.isEmpty()) {
            TreeNode tmp = queue.poll();

            if (null != tmp.left) {
                queue.offer(tmp.left);
            }
            if (null != tmp.right) {
                queue.offer(tmp.right);
            }

            if (match(tmp, t)) {
                return true;
            }
        }

        return false;
    }

    public boolean match(TreeNode s, TreeNode t) {
        if (null == s && null == t) {
            return true;
        } else if (null == s || null == t) {
            return false;
        }

        if (s.val != t.val) {
            return false;
        }

        return match(s.left, t.right) && match(s.right, t.right);
    }
}
