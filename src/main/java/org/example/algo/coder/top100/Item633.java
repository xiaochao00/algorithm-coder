package org.example.algo.coder.top100;

/**
 * 633
 * https://leetcode-cn.com/problems/sum-of-square-numbers/
 *
 * @author shichao
 * @since 1.0.0
 * 2021/4/28 23:37
 */
public class Item633 {
    public static void main(String[] args) {
        System.out.println(judgeSquareNum(5));
        System.out.println(judgeSquareNum(3));
        System.out.println(judgeSquareNum(4));
        System.out.println(judgeSquareNum(2));
        System.out.println(judgeSquareNum(1));
        System.out.println(judgeSquareNum(2147482647));
        System.out.println(judgeSquareNum2(2147482647));
    }

    public static boolean judgeSquareNum(int c) {
        for (int i = 0; i <= (int) Math.sqrt(c); i++) {
            int a2 = i * i;
            double d = Math.sqrt(c - a2);
            if (Math.abs(d - (int) d) <= 0.00001) {
                return true;
            }
        }
        return false;
    }

    public static boolean judgeSquareNum2(int c) {
        int i = 0, j = (int) Math.sqrt(c);
        while (i <= j) {
            int sum = i * i + j * j;
            if (sum == c) {
                return true;
            } else if (sum > c) {
                j = j - 1;
            } else {
                i = i + 1;
            }
        }
        return false;
    }
}
