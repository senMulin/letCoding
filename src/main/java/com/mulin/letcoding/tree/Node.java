package com.mulin.letcoding.tree;

import java.util.List;

/**
 * Created by mulin on 2018/8/17.
 */
public class Node {
    public int val;
    public List<Node> children;

    public Node() {}

    public Node(int _val, List<Node> _children) {
        val = _val;
        children = _children;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Node{");
        sb.append("val=").append(val);
        sb.append(", children=").append(children);
        sb.append('}');
        return sb.toString();
    }
}
