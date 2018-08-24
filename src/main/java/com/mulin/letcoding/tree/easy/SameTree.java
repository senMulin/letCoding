package com.mulin.letcoding.tree.easy;

import com.mulin.letcoding.tree.TreeNode;

import java.util.Stack;

/**
 * Created by
 *
 * @author mulin
 * @date 2018/8/23
 * @description:
给定两个二叉树，编写一个函数来检验它们是否相同。

如果两个树在结构上相同，并且节点具有相同的值，则认为它们是相同的。

示例 1:

输入:       1         1
          / \       / \
         2   3     2   3

[1,2,3],   [1,2,3]

输出: true
示例 2:

输入:      1          1
          /           \
         2             2

[1,2],     [1,null,2]

输出: false
 */
public class SameTree {
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if ((null == p && q != null) || (null != p && q == null)) {
            return false;
        } else if (p == null && q == null) {
            return true;
        }

        Stack<TreeNode> pStack = new Stack();
        Stack<TreeNode> qStack = new Stack();
        TreeNode cp = p, cq = q, pLast = null, qLast = null;


        while (cp != null && cq != null) {
            if (cp.val != cq.val) {
                return false;
            }

            pStack.push(cp);
            qStack.push(cq);

            cp = cp.left;
            cq = cq.left;
        }

        if (!(cp == null && cq == null)) {
            return false;
        }

        while (!pStack.isEmpty() && !qStack.isEmpty()) {
            cp = pStack.pop();
            cq = qStack.pop();

            if (cp.right != null && cq.right != null && pLast != cp.right && qLast != cq.right) {
                pStack.push(cp);
                qStack.push(cq);

                cp = cp.right;
                cq = cq.right;

                while (cp != null && cq != null) {
                    if (cp.val != cq.val) {
                        return false;
                    }

                    pStack.push(cp);
                    qStack.push(cq);

                    cp = cp.left;
                    cq = cq.left;
                }
            } else if ((cp.right == null && cq.right != null) ||
                    (cp.right != null && cq.right == null)){
                return false;
            } else {
                pLast = cp;
                qLast = cq;
            }
        }

        if (pStack.isEmpty() && qStack.isEmpty()) {
            return true;
        }else {
            return false;
        }
    }

}
