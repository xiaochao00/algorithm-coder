package org.example.algo.coder.top100.comp.section1;

import java.util.Arrays;

/**
 * Item1310XorQueriesOfASubarray
 *
 * @author shichao
 * @since 1.0.0
 * 2021/5/23 19:12
 */

public class Item1310XorQueriesOfASubarray {
    public static void main(String[] args) {
        Solution solution = new Item1310XorQueriesOfASubarray().new Solution();
        int[] res = solution.xorQueries(new int[]{1, 3, 4, 8}, new int[][]{{0, 1}, {1, 2}, {0, 3}, {3, 3}});
        System.out.printf(Arrays.toString(res));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int[] xorQueries(int[] arr, int[][] queries) {
            int[] prefixRes = new int[arr.length + 1];
            int val = 0;
            for (int i = 0; i < arr.length; i++) {
                val = val ^ arr[i];
                prefixRes[i + 1] = val;
            }
            //
            int[] res = new int[queries.length];
            for (int i = 0; i < queries.length; i++) {
                int start = queries[i][0];
                int end = queries[i][1];
                res[i] = prefixRes[start] ^ prefixRes[end + 1];
            }
            return res;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)
}