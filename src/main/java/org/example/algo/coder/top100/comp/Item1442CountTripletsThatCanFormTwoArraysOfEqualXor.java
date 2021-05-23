package org.example.algo.coder.top100.comp;

/**
 * Item1442CountTripletsThatCanFormTwoArraysOfEqualXor
 *
 * @author shichao
 * @since 1.0.0
 * 2021/5/23 19:10
 */
public class Item1442CountTripletsThatCanFormTwoArraysOfEqualXor {
    public static void main(String[] args) {
        Solution solution = new Item1442CountTripletsThatCanFormTwoArraysOfEqualXor().new Solution();
        int count = solution.countTriplets(new int[]{2, 3, 1, 6, 7});
        assert count == 4;
        System.out.println(count);
        count = solution.countTriplets(new int[]{1, 1, 1, 1, 1});
        assert count == 10;
        System.out.println(count);
        count = solution.countTriplets(new int[]{1, 3, 5, 7, 9});
        assert count == 3;
        System.out.println(count);
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int countTriplets(int[] arr) {
            // 找出 异或 arr[i-j]=0的所有i,j
            // 统计数目，每一组i-j可以的数目为，j-i个
            int sumCount = 0;
            for (int i = 0; i < arr.length; i++) {
                int val = arr[i];
                for (int j = i + 1; j < arr.length; j++) {
                    val = val ^ arr[j];
                    if (val == 0) {
                        sumCount += j - i;
                    }
                }
            }
            return sumCount;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}