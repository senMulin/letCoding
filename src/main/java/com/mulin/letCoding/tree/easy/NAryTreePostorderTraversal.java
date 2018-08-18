package com.mulin.letCoding.tree.easy;

import com.mulin.letCoding.tree.Node;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

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

    public static List<Integer> iteration(Node node, List<Integer> list) {

        Stack<Tuple> stack = new Stack<Tuple>();

        Tuple cur = new Tuple(node, 0);

        Node last = null;

        while (cur != null) {
            stack.push(cur);

            if (cur.node.children.size() > 0) {
                cur = new Tuple(cur.node.children.get(0), 0);
            } else {
                cur = null;
            }
        }

        while (!stack.isEmpty()) {

            cur = stack.pop();
            int childSize = cur.node.children.size();

            if (childSize > cur.idx &&
                    cur.node.children.get(childSize - 1) != last) {

                cur.idx = cur.idx + 1;

                stack.push(cur);

                cur = new Tuple(cur.node.children.get(cur.idx), 0);
                while (cur != null) {
                    stack.push(cur);

                    if (cur.node.children.size() > 0) {
                        cur = new Tuple(cur.node.children.get(0), 0);
                    } else {
                        cur = null;
                    }
                }

            } else {
                list.add(cur.node.val);
                last = cur.node;
            }
        }

        return list;
    }

    static class Tuple {
        public Node node;
        public Integer idx;

        public Tuple(Node node, Integer idx) {
            this.node = node;
            this.idx = idx;
        }
    }
}
