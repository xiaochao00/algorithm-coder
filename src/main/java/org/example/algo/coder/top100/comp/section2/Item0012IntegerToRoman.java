package org.example.algo.coder.top100.comp.section2;

import org.junit.Assert;

public class Item0012IntegerToRoman {
    public static void main(String[] args) {
        Solution solution = new Item0012IntegerToRoman().new Solution();
        String roman = solution.intToRoman(3);
        Assert.assertEquals("III", roman);
        System.out.println(roman);

        roman = solution.intToRoman(4);
        Assert.assertEquals("IV", roman);
        System.out.println(roman);

        roman = solution.intToRoman(1994);
        Assert.assertEquals("MCMXCIV", roman);
        System.out.println(roman);
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public String intToRoman(int num) {
            StringBuilder sb = new StringBuilder("");
            while (num > 0) {
                RomanNum largestRomanNum = RomanNum.getLargestRomanNum(num);
                // 是否是0
                if (RomanNum.ZERO.equals(largestRomanNum)) {
                    break;
                }
                // 是否是特殊罗马字符
                if (largestRomanNum.isSpecial) {
                    sb.append(largestRomanNum.toString());
                } else {
                    // 正常罗马符号
                    int count = num / largestRomanNum.val;
                    for (int i = 0; i < count; i++) {
                        sb.append(largestRomanNum.toString());
                    }
                }
                num = num % largestRomanNum.val;
            }
            return sb.toString();
        }

    }

    enum RomanNum {
        M(1000, false), CM(900, true), D(500, false), CD(400, true),
        C(100, false), XC(90, true), L(50, false), XL(40, true),
        X(10, false), IX(9, true), V(5, false), IV(4, true),
        I(1, false), ZERO(0, false);

        private int val;
        private boolean isSpecial;

        RomanNum(int val, boolean isSpecial) {
            this.val = val;
            this.isSpecial = isSpecial;
        }

        static RomanNum getLargestRomanNum(int val) {
            for (RomanNum romanNum : RomanNum.values()) {
                if (val >= romanNum.val) {
                    return romanNum;
                }
            }
            return RomanNum.ZERO;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}