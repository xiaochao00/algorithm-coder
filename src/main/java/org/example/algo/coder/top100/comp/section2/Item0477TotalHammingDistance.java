package org.example.algo.coder.top100.comp.section2;

public class Item0477TotalHammingDistance {
    public static void main(String[] args) {
        Solution solution = new Item0477TotalHammingDistance().new Solution();
        int res = solution.totalHammingDistance(new int[]{4, 14, 2});
        System.out.println(res);
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int totalHammingDistance(int[] nums) {
            //计算出，所有数据中，每一位的值为1的数目
            int[] bitSums = new int[32];
            for (int num : nums) {
                int index = 0;
                while (num != 0) {
                    bitSums[index++] += (num & 1);
                    num = num >> 1;
                }
            }
            //
            int res = 0;
            int size = nums.length;
            for (int num : nums) {
                int index = 0;
                while (index < 32) {
                    int cur = (num & 1);
                    res += (cur == 0 ? bitSums[index] : (size - bitSums[index]));
                    num = num >> 1;
                    index++;
                }
            }
            return res / 2;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}