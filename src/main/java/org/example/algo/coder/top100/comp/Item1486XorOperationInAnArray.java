package org.example.algo.coder.top100.comp;

/**
 * Item1486XorOperationInAnArray
 *
 * @author shichao
 * @since 1.0.0
 * 2021/5/23 19:14
 */
public class Item1486XorOperationInAnArray {
    public static void main(String[] args) {
        Solution solution = new Item1486XorOperationInAnArray().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int xorOperation(int n, int start) {
            int res = 0;
            for (int i = 0; i < n; i++) {
                int v = start + 2 * i;
                res = res ^ v;
            }
            return res;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
