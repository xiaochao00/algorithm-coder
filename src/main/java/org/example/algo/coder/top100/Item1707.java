package org.example.algo.coder.top100;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 1707
 * https://leetcode-cn.com/problems/maximum-xor-with-an-element-from-array/
 *
 * @author shichao
 * @since 1.0.0
 * 2021/5/23 11:44
 */
public class Item1707 {
    public static void main(String[] args) {
        Item1707 item1707 = new Item1707();
        int[] res = item1707.maximizeXor(new int[]{0, 1, 2, 3, 4}, new int[][]{{3, 1}, {1, 3}, {5, 6}});
        System.out.println(Arrays.toString(res));

        res = item1707.maximizeXor2(new int[]{0, 1, 2, 3, 4}, new int[][]{{3, 1}, {1, 3}, {5, 6}});
        System.out.println(Arrays.toString(res));

        res = item1707.maximizeXor2(new int[]{5, 2, 4, 6, 6, 3}, new int[][]{{12, 4}, {8, 1}, {6, 3}});
        System.out.println(Arrays.toString(res));

        res = item1707.maximizeXor2(new int[]{0, 1, 2, 3, 4}, new int[][]{{3, 1}, {1, 3}, {5, 6}});
        System.out.println(Arrays.toString(res));
    }

    public int[] maximizeXor(int[] nums, int[][] queries) {
        int[] res = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int[] que = queries[i];
            int x = que[0];
            int m = que[1];
            int max = Integer.MIN_VALUE;
            for (int num : nums) {
                if (num <= m) {
                    int val = x ^ num;
                    if (val > max) {
                        max = val;
                    }
                }
            }
            res[i] = max;
        }
        return res;
    }

    public int[] maximizeXor2(int[] nums, int[][] queries) {
        // 排序
        Arrays.sort(nums);
        //
        int[][] newQueries = new int[queries.length][3];
        for (int i = 0; i < queries.length; i++) {
            newQueries[i][0] = queries[i][0];
            newQueries[i][1] = queries[i][1];
            newQueries[i][2] = i;
        }
        Arrays.sort(newQueries, Comparator.comparingInt(o -> o[1]));
        //
        int level = 4;
        // 全局保持一棵树，减少计算量；因此要求，queries是按顺序的，按照m的顺序
        PrefixTree tree = new PrefixTree(level);
        int index = 0;
        int[] answers = new int[newQueries.length];
        for (int[] newQuery : newQueries) {
            int x = newQuery[0];
            int m = newQuery[1];
            int order = newQuery[2];
            for (int j = index; j < nums.length; j++) {
                int num = nums[index];
                if (num <= m) {
                    tree.insert(num);
                    index = index + 1;
                } else {
                    break;
                }
            }
            if (index == 0) {
                answers[order] = -1;
            } else {
                answers[order] = tree.getMaxXor(x);
            }
        }
        //
        return answers;
    }

    class PrefixTree {
        private int level;
        TreeNode root;

        PrefixTree(int level) {
            this.level = level;
            this.root = new TreeNode();
        }

        void insert(int val) {
            TreeNode node = this.root;
            for (int i = level - 1; i > 0; i--) {
                // 判断val在当前位数的最后一位的值，0或1
                int r = ((val >> (i - 1)) & 1);
                if (r == 0) {
                    if (node.left == null) {
                        node.left = new TreeNode();
                    }
                    node = node.left;
                } else {
                    if (node.right == null) {
                        node.right = new TreeNode();
                    }
                    node = node.right;
                }
            }
        }

        /**
         * 获取当前前缀树中的叶子节点，和x值，求异或最大的叶子，并返回最大的异或值
         *
         * @param x 指定值
         * @return 最大的异或值
         */
        int getMaxXor(int x) {
            int res = 0;
            // 从根节点开始，不断往下找
            TreeNode node = root;
            for (int i = level - 1; i > 0; i--) {
                assert node != null : "node is empty in level:" + i;
                // 计算指定值，在当前位数的最后一位值
                int r = (x >> (i - 1)) & 1;
                if (r == 1) {
                    // 当前位数为1，对应查找前缀树的左节点
                    if (node.left != null) {
                        // 左子树存在
                        node = node.left;
                        res += (1 << (i - 1));
                    } else {
                        node = node.right;
                    }
                } else {
                    // 当前位数为0，对应查找前缀树的右节点
                    if (node.right != null) {
                        node = node.right;
                        res += (1 << (i - 1));
                    } else {
                        node = node.left;
                    }
                }
            }
            return res;
        }

    }

    class TreeNode {
        TreeNode left;
        TreeNode right;
    }
}
