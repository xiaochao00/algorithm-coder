package org.example.algo.coder.top100.comp.section1;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Item0872LeafSimilarTrees
 *
 * @author shichao
 * @since 1.0.0
 * 2021/5/23 19:13
 */
public class Item0872LeafSimilarTrees {
    public static void main(String[] args) {
        Solution solution = new Solution();

        Solution.TreeNode treeNode1 = buildTree(Arrays.asList(3, 5, 1, 6, 2, 9, 8, null, null, 7, 4));
        Solution.TreeNode treeNode2 = buildTree(Arrays.asList(3, 5, 1, 6, 7, 4, 2, null, null, null, null, null, null, 9, 8));
        boolean isSame = solution.leafSimilar(treeNode1, treeNode2);

        System.out.println(isSame);
    }
    //leetcode submit region begin(Prohibit modification and deletion)

    private static Solution.TreeNode buildTree(List<Integer> values) {
        List<Solution.TreeNode> nodes = new ArrayList<>(values.size());
        for (Integer val : values) {
            if (val == null) {
                nodes.add(null);
            } else {
                nodes.add(new Solution.TreeNode(val));
            }
        }
        //
        for (int i = 0; i < (values.size() / 2); i++) {
            Solution.TreeNode curNode = nodes.get(i);
            if (curNode != null) {
                Solution.TreeNode leftNode = nodes.get(2 * i + 1);
                Solution.TreeNode rightNode = nodes.get(2 * i + 2);
                curNode.left = leftNode;
                curNode.right = rightNode;
            }
        }
        return nodes.get(0);
    }

    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     * int val;
     * TreeNode left;
     * TreeNode right;
     * TreeNode() {}
     * TreeNode(int val) { this.val = val; }
     * TreeNode(int val, TreeNode left, TreeNode right) {
     * this.val = val;
     * this.left = left;
     * this.right = right;
     * }
     * }
     */
    static class Solution {
        public boolean leafSimilar(TreeNode root1, TreeNode root2) {
            List<Integer> leaft1 = getLeafs(root1);
            List<Integer> leaft2 = getLeafs(root2);
            return Arrays.equals(leaft1.toArray(), leaft2.toArray());
        }

        private List<Integer> getLeafs(TreeNode treeNode) {
            // 中序遍历树
            List<Integer> values = new ArrayList<>();
            LinkedList<TreeNode> queue = new LinkedList<>();

            TreeNode curNode = treeNode;

            while (curNode != null || !queue.isEmpty()) {
                // 每次扎到最左的节点
                while (curNode != null) {
                    queue.addFirst(curNode);
                    curNode = curNode.left;
                }
                //
                if (!queue.isEmpty()) {
                    curNode = queue.removeFirst();
                    if (curNode.right == null && curNode.left == null) {
                        values.add(curNode.val);
                    }
                    curNode = curNode.right;
                }
            }

            return values;
        }

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
    }
//leetcode submit region end(Prohibit modification and deletion)
}