//给你一个整数 n ，找出从 1 到 n 各个整数的 Fizz Buzz 表示，并用字符串数组 answer（下标从 1 开始）返回结果，其中： 
//
// 
// answer[i] == "FizzBuzz" 如果 i 同时是 3 和 5 的倍数。 
// answer[i] == "Fizz" 如果 i 是 3 的倍数。 
// answer[i] == "Buzz" 如果 i 是 5 的倍数。 
// answer[i] == i 如果上述条件全不满足。 
// 
//
// 
//
// 示例 1： 
//
// 
//输入：n = 3
//输出：["1","2","Fizz"]
// 
//
// 示例 2： 
//
// 
//输入：n = 5
//输出：["1","2","Fizz","4","Buzz"]
// 
//
// 示例 3： 
//
// 
//输入：n = 15
//输出：["1","2","Fizz","4","Buzz","Fizz","7","8","Fizz","Buzz","11","Fizz","13","1
//4","FizzBuzz"] 
//
// 
//
// 提示： 
//
// 
// 1 <= n <= 104 
// 
// Related Topics 数学 字符串 模拟 
// 👍 150 👎 0


package com.shuzijun.leetcode.editor.cn;

import org.junit.Assert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Item0412FizzBuzz {
    public static void main(String[] args) {
        Solution solution = new Solution();

        List<String> res = solution.fizzBuzz(3);
        System.out.println(res);
        Assert.assertEquals(Arrays.asList("1", "2", "Fizz"), res);

        res = solution.fizzBuzz(5);
        System.out.println(res);
        Assert.assertEquals(Arrays.asList("1", "2", "Fizz", "4", "Buzz"), res);

        res = solution.fizzBuzz(15);
        System.out.println(res);
        Assert.assertEquals(Arrays.asList("1", "2", "Fizz", "4", "Buzz", "Fizz", "7", "8", "Fizz", "Buzz", "11", "Fizz", "13", "14", "FizzBuzz"), res);
    }

    static //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public List<String> fizzBuzz(int n) {
            List<String> list = new ArrayList<>(n);

            for (int i = 0; i < n; i++) {
                StringBuilder res = getFizz(i + 1);
                list.add(res.toString());
            }

            return list;
        }

        private StringBuilder getFizz(int n) {
            if (n % 15 == 0) {
                return new StringBuilder("FizzBuzz");
            }
            if (n % 3 == 0) {
                return new StringBuilder("Fizz");
            }
            if (n % 5 == 0) {
                return new StringBuilder("Buzz");
            }
            return new StringBuilder(String.valueOf(n));
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}