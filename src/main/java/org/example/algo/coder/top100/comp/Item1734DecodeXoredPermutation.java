package org.example.algo.coder.top100.comp;


import java.util.Arrays;

/**
 * Item1734DecodeXoredPermutation
 *
 * @author shichao
 * @since 1.0.0
 * 2021/5/23 19:13
 */
public class Item1734DecodeXoredPermutation {
    public static void main(String[] args) {
        Solution solution = new Item1734DecodeXoredPermutation().new Solution();
        int[] arr = solution.decode(new int[]{3, 1});
        System.out.println(Arrays.toString(arr));
        arr = solution.decode(new int[]{6, 5, 4, 6});
        System.out.println(Arrays.toString(arr));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int[] decode(int[] encoded) {
            // 得到n个数的亦或结果
            int total = 0;
            int n = encoded.length + 1;
            for (int i = 1; i <= n; i++) {
                total = total ^ i;
            }
            // 得到出第一个元素外的其余元素的亦或结果
            int other = 0;
            for (int i = 1; i < n - 1; i += 2) {
                other = other ^ encoded[i];
            }
            // 得到第一个元素的值
            int first = total ^ other;
            int[] arr = new int[n];
            arr[0] = first;
            for (int i = 1; i < n; i++) {
                arr[i] = arr[i - 1] ^ encoded[i - 1];
            }
            return arr;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}