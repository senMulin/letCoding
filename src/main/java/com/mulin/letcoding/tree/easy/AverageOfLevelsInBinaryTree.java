package com.mulin.letcoding.tree.easy;

import com.mulin.letcoding.tree.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by
 *
 * @author mulin
 * @date 2018/8/20
 * @description:
给定一个非空二叉树, 返回一个由每层节点平均值组成的数组.

示例 1:

输入:
     3
    / \
   9  20
  /  \
15   7
输出: [3, 14.5, 11]
解释:
第0层的平均值是 3,  第1层是 14.5, 第2层是 11. 因此返回 [3, 14.5, 11].
注意：

节点值的范围在32位有符号整数范围内。
 */
public class AverageOfLevelsInBinaryTree {

    /**
     * 解法，给每层节点添加一个idx 游标，
     * 采用数组加链表的方式存储，数组的idx 即为数的深度
     * 最后遍历数组求每个元素的平均值
     * @param root
     * @return
     */
    public List<Double> averageOfLevels(TreeNode root) {
        List<Double> doubles = new ArrayList<Double>();

        if (null == root) {
            return doubles;
        }

        List<LinkedList<Integer>> lists = new ArrayList<LinkedList<Integer>>();

        avgLevel(root, 0, lists);

        for (LinkedList<Integer> link : lists) {
            double sum = 0;
            long size = link.size();

            for (Integer i : link) {
                sum += i;
            }

            doubles.add(sum/size);
        }

        return doubles;
    }


    public static void avgLevel(TreeNode node, int idx, List<LinkedList<Integer>> lists) {
        if (null == node) {
            return;
        }

        LinkedList<Integer> link = new LinkedList<Integer>();
        if (lists.size() <= idx) {
            link.add(node.val);
            lists.add(link);
        } else {
            link = lists.get(idx);
            link.add(node.val);
        }

        avgLevel(node.left, idx + 1, lists);
        avgLevel(node.right, idx + 1, lists);
    }
}
