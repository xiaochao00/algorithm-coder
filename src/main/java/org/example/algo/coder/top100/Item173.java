package org.example.algo.coder.top100;

/**
 * 173
 * https://leetcode-cn.com/problems/single-number-ii/
 *
 * @author shichao
 * @since 1.0.0
 * 2021/4/30 0:05
 */
public class Item173 {
    public static void main(String[] args) {
        int a = singleNumber2(new int[]{2, 2, 3, 2});
        System.out.println(a);
        a = singleNumber2(new int[]{0, 1, 1, 1, 0, 99, 0});
        System.out.println(a);
        a = singleNumber2(new int[]{0, 1, 0, 1, 0, 1, 99});
        System.out.println(a);
        a = singleNumber2(new int[]{-2, -2, 1, 1, 4, 1, 4, 4, -4, -2});
        System.out.println(a);
        a = singleNumber2(new int[]{2, 2, 2, -1, -1, -1, 8, -7, 0, -7, 0, -7, 0});
        System.out.println(a);
    }

    public static int singleNumber2(int[] nums) {
        int res = 0;
        for (int i = 0; i < 32; i++) {
            int sum = 0;
            // 准确的求出每一个数对应二进制位的和
            for (int num : nums) {
                // 准确的求当前位的最后一位的值，0或1
                sum += (num >> i) & 1;
            }
            //
            if (sum % 3 == 1) {
                res |= (1 << i);
            }
        }
        return res;
    }

    public static int singleNumber(int[] nums) {
        // 最高位符号位
        int[] sums3 = new int[33];
        for (int num : nums) {
            plusBy3(sums3, num);
        }
        // 转回十进制数
        return back3To10(sums3);
    }

    /**
     * 3进制数组无进位加
     *
     * @param sum3 和的3进制数组表示
     * @param num  当前数
     */
    private static void plusBy3(int[] sum3, int num) {
        int[] newNums = changeTo3(num);
        for (int i = 0; i < newNums.length; i++) {
            int sum = (sum3[i] + Math.abs(newNums[i])) % 3;
            sum3[i] = sum;
        }
    }

    /**
     * 把num转换为3进制数
     *
     * @param num num值
     * @return num的3进制表示数组
     */
    private static int[] changeTo3(int num) {
        int[] newNums = new int[33];
        if (num < 0) {
            newNums[32] = 1;
            num = -num;
        }
        int index = 0;
        while (num != 0) {
            newNums[index++] = (num % 3);
            num = num / 3;
        }
        return newNums;
    }

    /**
     * 3进制数组表示的数转回10进制数
     *
     * @param nums3 某十进制数的3进制表示
     * @return 原十进制数
     */
    private static int back3To10(int[] nums3) {
        int sum = 0;
        for (int i = nums3.length - 2; i >= 0; i--) {
            sum = sum * 3 + nums3[i];
        }
        if (nums3[nums3.length - 1] == 1) {
            sum = -sum;
        }
        return sum;
    }
}
