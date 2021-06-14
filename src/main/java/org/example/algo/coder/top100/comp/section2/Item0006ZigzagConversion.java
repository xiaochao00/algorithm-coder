package org.example.algo.coder.top100.comp.section2;

import java.util.ArrayList;
import java.util.List;

public class Item0006ZigzagConversion {
    public static void main(String[] args) {
        Solution solution = new Item0006ZigzagConversion().new Solution();
        String res = solution.convert("PAYPALISHIRING", 3);
        System.out.println(res);

        res = solution.convert2("PAYPALISHIRING", 3);
        assert "PAHNAPLSIIGYIR".equals(res);
        System.out.println(res);

        res = solution.convert2("PAYPALISHIRING", 4);
        assert "PINALSIGYAHRPI".equals(res);
        System.out.println(res);
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public String convert(String s, int numRows) {
            // 特殊情况
            if (numRows == 1) {
                return s;
            }
            // 初始化每行的builder
            List<StringBuilder> rows = new ArrayList<>();
            for (int i = 0; i < Math.min(numRows, s.length()); i++) {
                rows.add(new StringBuilder());
            }
            // 遍历字符串，给每行赋值
            int row = 0;
            boolean direction = false;
            for (int j = 0; j < s.length(); j++) {
                rows.get(row).append(s.charAt(j));
                if (row % (numRows - 1) == 0) {
                    direction = !direction;
                }
                if (direction) {
                    row += 1;
                } else {
                    row -= 1;
                }
            }
            // 合并所有行
            StringBuilder res = new StringBuilder();
            for (StringBuilder rowBuilder : rows) {
                res.append(rowBuilder);
            }
            return res.toString();
        }

        public String convert2(String s, int numRows) {
            // 特殊情况
            if (numRows == 1) {
                return s;
            }
            //
            int size = s.length();
            int groupSize = 2 * (numRows - 1);
            StringBuilder res = new StringBuilder();
            for (int i = 0; i < numRows; i++) {
                for (int j = 0; j + i < size; j += groupSize) {
                    res.append(s.charAt(j + i));
                    if (i != 0 && i != numRows - 1 && (j + groupSize - i) < size) {
                        res.append(s.charAt(j + groupSize - i));
                    }
                }
            }
            return res.toString();
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}