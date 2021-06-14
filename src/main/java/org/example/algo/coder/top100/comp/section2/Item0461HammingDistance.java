package org.example.algo.coder.top100.comp.section2;

public class Item0461HammingDistance {
    public static void main(String[] args) {
        Solution solution = new Item0461HammingDistance().new Solution();
        int res = solution.hammingDistance(1, 4);
        System.out.println(res);
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int hammingDistance(int x, int y) {
            int val = x ^ y;
            int count = 0;
            while (val != 0) {
                count += (val & 1);
                val = val >> 1;
            }
            return count;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}