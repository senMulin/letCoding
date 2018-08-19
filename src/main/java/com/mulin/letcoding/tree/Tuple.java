package com.mulin.letcoding.tree;

/**
 * @author mulin
 * @date 2018/8/19
 */
public class Tuple {
    public Node node;
    public Integer idx;

    public Tuple(Node node, Integer idx) {
        this.node = node;
        this.idx = idx;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Tuple{");
        sb.append("node=").append(node);
        sb.append(", idx=").append(idx);
        sb.append('}');
        return sb.toString();
    }
}
