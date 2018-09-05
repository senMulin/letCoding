package com.mulin.letcoding.tree.easy;

import com.mulin.letcoding.tree.TreeNode;

import java.util.*;

/**
 * Created by
 *
 * @author mulin
 * @date 2018/9/4
 * @description:

给定一个有相同值的二叉搜索树（BST），找出 BST 中的所有众数（出现频率最高的元素）。

假定 BST 有如下定义：

结点左子树中所含结点的值小于等于当前结点的值
结点右子树中所含结点的值大于等于当前结点的值
左子树和右子树都是二叉搜索树
例如：
给定 BST [1,null,2,2],

  1
   \
   2
  /
 2
返回[2].

提示：如果众数超过1个，不需考虑输出顺序

进阶：你可以不使用额外的空间吗？（假设由递归产生的隐式调用栈的开销不被计算在内）
 */
public class FindModeInBinarySearchTree {

    public static int[] findMode(TreeNode root) {

        Map<Integer, Integer> map = new HashMap<>();

        find(root, map);

        List<Integer> list = new LinkedList<>();
        int count = 0;

        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (list.size() == 0) {
                list.add(entry.getKey());
                count = entry.getValue();
            } else if (count < entry.getValue()) {
                list.clear();
                list.add(entry.getKey());
                count = entry.getValue();
            } else if (count == entry.getValue()) {
                list.add(entry.getKey());
            }
        }

        int[] result = new int[list.size()];

        for (int i = 0; i < list.size(); i++) {
            result[i] = list.get(i);
        }
        return result;
    }

    public static void find(TreeNode node, Map<Integer, Integer> map) {
        if (null == node) {
            return;
        }

        Integer count = map.getOrDefault(node.val, 0);
        map.put(node.val, ++count);

        find(node.left, map);
        find(node.right, map);
    }

    public static void main(String[] args) {
        TreeNode n1 = new TreeNode(1);

        TreeNode n21 = new TreeNode(2);

        TreeNode n22 = new TreeNode(2);

        n1.right = n21;
        n21.left = n22;

        findMode(n1);
    }
}
