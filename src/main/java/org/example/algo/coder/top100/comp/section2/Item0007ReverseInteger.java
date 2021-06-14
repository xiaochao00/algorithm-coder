package org.example.algo.coder.top100.comp.section2;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Item0007ReverseInteger {
    public static void main(String[] args) {
        Solution solution = new Item0007ReverseInteger().new Solution();
        int res = solution.reverse(-1432156780);
        System.out.println(res);

        res = solution.reverse2(1432156789);
        System.out.println(res);
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int reverse2(int x) {
            int res = 0;
            while (x != 0) {
                if (res < Integer.MIN_VALUE / 10 || res > Integer.MAX_VALUE / 10) {
                    return 0;
                }
                res = res * 10 + (x % 10);
                x = x / 10;
            }
            return res;
        }

        public int reverse(int x) {
            try {
                String s = Integer.valueOf(x).toString();
                List<String> list = Arrays.asList(s.split(""));
                Collections.reverse(list);
                String val = String.join("", list);
                if ("-".equals(list.get(list.size() - 1))) {
                    val = "-" + val.substring(0, s.length() - 1);
                }
                return Integer.parseInt(val);
            } catch (NumberFormatException e) {
                System.out.println(e.getMessage());
                return 0;
            }
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}