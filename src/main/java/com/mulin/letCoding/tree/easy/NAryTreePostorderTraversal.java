package com.mulin.letCoding.tree.easy;

import com.mulin.letCoding.tree.Node;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mulin on 2018/8/17.
 * 给定一个N叉树，返回其节点值的后序遍历。

 例如，给定一个 3叉树 :

        1
     /  |  \
    3   2  4
   / \
  5   6

 返回其后序遍历: [5,6,3,2,4,1]
 */
public class NAryTreePostorderTraversal {

    public List<Integer> postorder(Node root) {
        List<Integer> list = new ArrayList<Integer>();

        recursive(root, list);

        return list;
    }

    /**
     * 递归解法
     * @param node
     * @param list
     */
    public static void recursive(Node node, List<Integer> list) {
        if (null == node) {
            return;
        }

        for (Node n : node.children) {
            recursive(n, list);
        }

        list.add(node.val);
    }

    public static List<Integer> iteration(Node node) {
        return new ArrayList<Integer>();
    }
}
