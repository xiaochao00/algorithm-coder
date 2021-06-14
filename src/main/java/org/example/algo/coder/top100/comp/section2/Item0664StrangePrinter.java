package org.example.algo.coder.top100.comp.section2;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Item0664StrangePrinter {
    public static void main(String[] args) {
        Solution solution = new Item0664StrangePrinter().new Solution();
        int res = solution.strangePrinter("abdabdb");
        System.out.println(res);

        res = solution.strangePrinter("baacdddaaddaaaaccbddbcabdaabdbbcdcbbbacbddcabcaaa");
        System.out.println(res);

        res = solution.strangePrinter("bacdadacbdbcabdabdbcdcbacbdcabca");
        System.out.println(res);

        res = solution.strangePrinter2("baacdddaaddaaaaccbddbcabdaabdbbcdcbbbacbddcabcaaa");
        System.out.println(res);
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int strangePrinter(String s) {
            int size = s.length();
            // dp[i][j] 表示，从i到j需要打印多少次，i<=j
            int[][] dp = new int[size][size];
            // 初始化
            for (int i = 0; i < size; i++) {
                dp[i][i] = 1;
            }
            // 推导：dp[i][j]=
            // 1.s[j]==s[j-1], =dp[i][j-1]
            // 2.s[j]==s[i], =dp[i][j-1]
            // 3.s[j]!=s[i] and s[j]==s[j-1], =dp[i][k-1]+dp[k][j];k为字符s[j]在i~j范围内第一次出现的位置
            // 1，2 的情况中，当前列的值，依赖前列的值，因此需要从左到右计算；
            // 3 的情况
            for (int column = 1; column < size; column++) {
                for (int row = column - 1; row >= 0; row--) {
                    if (s.charAt(column) == s.charAt(column - 1) || s.charAt(column) == s.charAt(row)) {
                        dp[row][column] = dp[row][column - 1];
                    } else {
                        int min = Integer.MAX_VALUE;
                        for (int k = row; k < column; k++) {
                            int val = dp[row][k] + dp[k + 1][column];
                            if (val < min) {
                                min = val;
                            }
                        }
                        dp[row][column] = min;
                    }
                }
            }
            // 求的是 dp[0][size-1]
            return dp[0][size - 1];
        }

        public int strangePrinter2(String s) {
            // 通过构建树
            TreeNode tree = buildTree(s);
            // 求所有叶子节点的字符串
            List<String> leafList = inorderTraverseTree(tree);
            System.out.println(leafList);
            // 如果结束字符串的开始等于，整个字符串的开始，需要减1
            int count = leafList.stream().mapToInt(String::length).sum();
            if (leafList.get(leafList.size() - 1).startsWith(s.substring(0, 1))) {
                count = count - 1;
            }
            return count;
        }

        public TreeNode buildTree(String s) {
            // 压缩字符串
            TreeNode root = new TreeNode();
            int size = s.length();
            if ("bac".equals(s)) {
                System.out.println("123");
            }
            if (size <= 1 || stringHaveRedundancy(s)) {
                root.s = s;
                return root;
            }
            String cS = compress(s);
            //
            char lastChar = cS.charAt(cS.length() - 1);
            int beginIndex = cS.indexOf(lastChar);
            root.left = buildTree(cS.substring(0, beginIndex));
            root.right = buildTree(cS.substring(beginIndex));
            //
            return root;
        }

        public List<String> inorderTraverseTree(TreeNode root) {
            // 中序遍历
            List<String> arr = new ArrayList<>();
            Stack<TreeNode> stack = new Stack<>();
            TreeNode node = root;
            while (node != null || !stack.isEmpty()) {
                // 一直遍历到最左边，入栈
                while (node != null) {
                    stack.add(node);
                    node = node.left;
                }
                if (!stack.isEmpty()) {
                    node = stack.pop();
                    if (node.right == null) {
                        arr.add(node.s);
                    }
                    node = node.right;
                }
            }
            return arr;
        }

        public String compress(String s) {
            char pre = s.charAt(0);
            StringBuilder builder = new StringBuilder("" + pre);
            for (int i = 1; i < s.length(); i++) {
                char cur = s.charAt(i);
                if (cur != pre) {
                    // 如果当前字符不等于上一个
                    builder.append(cur);
                }
                pre = cur;
            }
            //去掉尾部和首部一样的尾部
            String newS = builder.toString();
            if (newS.endsWith(s.substring(0, 1))) {
                return newS.substring(0, newS.length() - 1);
            }
            return newS;
        }

        public boolean stringHaveRedundancy(String s) {
            for (int i = 0; i < s.length() - 1; i++) {
                if (s.substring(i + 1).indexOf(s.charAt(i)) != -1) {
                    return false;
                }
            }
            return true;
        }

        class TreeNode {
            String s;
            TreeNode left;
            TreeNode right;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}