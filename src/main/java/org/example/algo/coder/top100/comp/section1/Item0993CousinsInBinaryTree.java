package org.example.algo.coder.top100.comp.section1;


import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Item0993CousinsInBinaryTree
 *
 * @author shichao
 * @since 1.0.0
 * 2021/5/23 19:11
 */
public class Item0993CousinsInBinaryTree {
    public static void main(String[] args) {
        Solution solution = new Item0993CousinsInBinaryTree().new Solution();
        TreeNode tree1 = buildTree(Arrays.asList(1, 2, null, null, 3, null, 4));
        boolean isCousin = solution.isCousins(tree1, 4, 3);
        System.out.println("" + isCousin);
    }
    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * Definition for a binary tree node.
     */
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    private static TreeNode buildTree(List<Integer> values) {
        List<TreeNode> nodes = values.stream()
                .map(v -> (v == null ? null : new TreeNode(v)))
                .collect(Collectors.toList());
        // 调整空节点
        int size = nodes.size();
        for (int i = 0; i <= size / 2; i++) {
            if (nodes.get(i) == null) {
                nodes.add(2 * i + 1, null);
                nodes.add(2 * i + 2, null);
            }
        }
        // 构造树
        size = nodes.size();
        for (int i = 0; i < (size / 2); i++) {
            TreeNode curNode = nodes.get(i);
            if (curNode != null) {
                if (2 * i + 1 < size) {
                    curNode.left = nodes.get(2 * i + 1);
                }
                if (2 * i + 2 < size) {
                    curNode.right = nodes.get(2 * i + 2);
                }
            }
        }
        return nodes.get(0);
    }


    class Solution {

        public boolean isCousins(TreeNode root, int x, int y) {
            // 查找节点的位置
            int[] xInfo = findNodeByDeep(root, x, 0, null);
            int[] yInfo = findNodeByDeep(root, y, 0, null);
            return xInfo[0] != yInfo[0] && xInfo[1] == yInfo[1];
        }

        /**
         * 查找指定节点的父节点，和其高度值
         *
         * @param root   开始节点
         * @param val    查找值
         * @param high   开始查找的高度
         * @param parent 父节点
         * @return [父节点值, 高度值]
         */
        public int[] findNodeByDeep(TreeNode root, int val, int high, TreeNode parent) {
            if (root == null) {
                return null;
            }
            // 通过栈实现深度优先遍历树
            if (root.val == val) {
                if (parent != null) {
                    return new int[]{parent.val, high};
                }
                return new int[]{Integer.MIN_VALUE, high};
            }

            int[] leftInfo = findNodeByDeep(root.left, val, high + 1, root);
            if (leftInfo != null) {
                return leftInfo;
            }
            return findNodeByDeep(root.right, val, high + 1, root);
        }
    }
//leetcode submit region end(Prohibit modification and deletion)
}
