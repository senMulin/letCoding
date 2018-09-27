package com.mulin.letcoding.dynamicprograming.middle;

import com.mulin.letcoding.tree.TreeNode;

import java.util.*;

/**
 * Created by
 *
 * @author mulin
 * @date 2018/9/27
 * @description:
给定一个整数 n，生成所有由 1 ... n 为节点所组成的二叉搜索树。

示例:

输入: 3
输出:
[
[1,null,3,2],
[3,2,null,1],
[3,1,null,null,2],
[2,1,3],
[1,null,2,null,3]
]
解释:
以上的输出对应以下 5 种不同结构的二叉搜索树：

1         3     3      2      1
\       /     /      / \      \
3     2     1      1   3      2
/     /       \                 \
2     1         2                 3
 */
public class UniqueBinarySearchTreesII {

    Map<Integer, List<TreeNode>> map = new HashMap<>();

    public List<TreeNode> generateTrees(int n) {
        List<TreeNode> starts = new LinkedList();

        if (n == 0) {
            return starts;
        }


        TreeNode start = new TreeNode(1);
        starts.add(start);

        if (n == 1) {
            return starts;
        }

        map.put(1, starts);

        for (int i = 2; i <= n; i++) {
            List<TreeNode> ts = new LinkedList<>();

            for (int j = i; j >= 1; j--) {
                List<TreeNode> first = map.get(i - j);
                List<TreeNode> last = map.get(j - 1);

                if (null == first) {
                    for (TreeNode q : last) {
                        TreeNode t = new TreeNode(j);

                        t.left = q;

                        ts.add(t);
                    }
                }

                if (null == last) {
                    for (TreeNode p : first) {
                        TreeNode t = new TreeNode(j);

                        TreeNode a = build(p, j);
                        t.right = a;

                        ts.add(t);
                    }
                }

                if (null != first && null != last) {
                    for (TreeNode p : first) {
                        TreeNode a = build(p, j);

                        for (TreeNode q : last) {
                            TreeNode t = new TreeNode(j);

                            t.right = a;
                            t.left = q;

                            ts.add(t);
                        }
                    }
                }

            }

            map.put(i, ts);
        }

        return map.get(n);
    }

    public TreeNode build(TreeNode oldT, int n) {

        if (null == oldT) {
            return null;
        }

        TreeNode newT = new TreeNode(n + oldT.val);

        newT.left = build(oldT.left, n);

        newT.right = build(oldT.right, n);


        return newT;
    }

//    public List<TreeNode> generateTrees(int n) {
//        // write your code here
//
//        if(n < 0) {
//            return null;
//        }
//        return createTree(1, n);
//    }
//
//    public List<TreeNode> createTree(int start, int end){
//        List<TreeNode> res = new ArrayList<TreeNode>();
//        if(start > end){
//            res.add(null);
//            return res;
//        }
//        for(int i = start; i <=end; i++){
//            List<TreeNode> left = createTree(start, i-1);
//            List<TreeNode> right = createTree(i+1, end);
//            for(int j = 0; j < left.size(); j++){
//                for(int k = 0 ; k < right.size(); k++){
//                    TreeNode root = new TreeNode(i);
//                    root.left = left.get(j);
//                    root.right = right.get(k);
//                    res.add(root);
//                }
//            }
//        }
//        return res;
//    }

    public static void main(String[] args) {
        UniqueBinarySearchTreesII u = new UniqueBinarySearchTreesII();

        System.out.println(u.generateTrees(10));
    }
}
