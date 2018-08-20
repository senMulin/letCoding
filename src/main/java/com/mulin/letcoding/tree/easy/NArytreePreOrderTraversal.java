package com.mulin.letcoding.tree.easy;

import com.mulin.letcoding.tree.Node;
import com.mulin.letcoding.tree.Tuple;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Created by mulin on 2018/8/19.

 给定一个N叉树，返回其节点值的前序遍历。

 例如，给定一个 3叉树 :

       1
    /  |  \
   3   2  4
  / \
 5   6

 返回其前序遍历: [1,3,5,6,2,4]。

 */
public class NArytreePreOrderTraversal {

    public List<Integer> preorder(Node root) {

        List<Integer> list = new ArrayList<Integer>();

        recursive(root, list);

        return list;
    }


    public static void recursive(Node parent, List<Integer> list) {
        if (null == parent) {
            return;
        }

        list.add(parent.val);

        for (Node child : parent.children) {
            recursive(child, list);
        }
    }

    public static void iteration(Node parent, List<Integer> list) {
        if (null == parent) {
            return;
        }

        Stack<Tuple> stack = new Stack<Tuple>();

        Tuple cur = new Tuple(parent, 0);
        Node last = null;

        while (null != cur) {
            stack.push(cur);
            list.add(cur.node.val);

            if (cur.node.children.size() > 0) {
                cur = new Tuple(cur.node.children.get(0), 0);
            } else {
                cur = null;
            }
        }

        while (!stack.isEmpty()) {
            cur = stack.pop();

            int size = cur.node.children.size();
            if (size > cur.idx && last != cur.node.children.get(size - 1)) {

                cur.idx = cur.idx + 1;
                stack.push(cur);

                cur = new Tuple(cur.node.children.get(cur.idx), 0);

                while (cur != null) {
                    stack.push(cur);
                    list.add(cur.node.val);

                    if (cur.node.children.size() > 0) {
                        cur = new Tuple(cur.node.children.get(0), 0);
                    } else {
                        cur = null;
                    }
                }
            } else {
                last = cur.node;
            }
        }
    }

    public static void main(String[] args) {
        final Node n5 = new Node(5, new ArrayList<Node>());
        final Node n6 = new Node(6, new ArrayList<Node>());

        final Node n3 = new Node(3, new ArrayList<Node>(){{add(n5); add(n6);}});

        final Node n2 = new Node(2, new ArrayList<Node>());
        final Node n4 = new Node(4, new ArrayList<Node>());

        final Node n1 = new Node(1, new ArrayList<Node>(){{add(n3); add(n2); add(n4); }});

        List<Integer> list = new ArrayList<Integer>();

        iteration(n1, list);
    }

}
