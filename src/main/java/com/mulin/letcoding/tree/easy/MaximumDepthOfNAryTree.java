package com.mulin.letcoding.tree.easy;

import com.mulin.letcoding.tree.Node;

/**
 * Created by mulin on 2018/8/19.
 *
 给定一个N叉树，找到其最大深度。

 最大深度是指从根节点到最远叶子节点的最长路径上的节点总数。

 例如，给定一个 3叉树 :


       1
    /  |  \
   3   2  4
  / \
 5   6

 */
public class MaximumDepthOfNAryTree {

    public int maxDepth(Node root) {
        if (null == root) {
            return 0;
        }

        if (root.children.size() == 0) {
            return 1;
        }

        int deep = 1;

        for (Node node : root.children) {
            int tmp = maxDepth(node);
            deep = tmp > deep ? tmp : deep;
        }

        return deep+1;
    }


}
