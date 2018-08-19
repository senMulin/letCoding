package com.mulin.letcoding.tree.easy;

import com.mulin.letcoding.tree.TreeNode;


/**
 * Created by mulin on 2018/8/19.
 *
 将一个按照升序排列的有序数组，转换为一棵高度平衡二叉搜索树。

 本题中，一个高度平衡二叉树是指一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1。

 示例:

 给定有序数组: [-10,-3,0,5,9],

 一个可能的答案是：[0,-3,9,-10,null,5]，它可以表示下面这个高度平衡二叉搜索树：

      0
     / \
   -3   9
   /   /
 -10  5

 */
public class ConvertSortedArrayToBinarySearchTree {

    /**
     * 思路迭代查找中间的节点
     * @param nums
     * @return
     */
    public TreeNode sortedArrayToBST(int[] nums) {

        if (nums.length == 0) {
            return null;
        }

        if (nums.length == 1) {
            return new TreeNode(nums[0]);
        }

        return toBST(nums, 0, nums.length-1);
    }

    public static TreeNode toBST(int[] nums, int start, int end) {
        if (start > end) {
            return null;
        }

        int mid = partition(start, end);

        TreeNode node = new TreeNode(nums[mid]);

        node.left = toBST(nums, start, mid - 1);
        node.right = toBST(nums, mid + 1, end);

        return node;
    }

    public static int partition(int start, int end) {
        return start + (end - start) /2;
    }
}
