package org.example.algo.coder.top100;

/**
 * 143
 *
 * @author shichao
 * @since 1.0.0
 * 2021/5/16 23:12
 */
public class Item0421 {
    public static void main(String[] args) {
        int max = findMaximumXOR2(new int[]{3, 10, 5, 25, 2, 8});
        assert max == 28;
        System.out.println(max);
        max = findMaximumXOR2(new int[]{0});
        assert max == 0;
        System.out.println(max);
        max = findMaximumXOR2(new int[]{2, 4});
        assert max == 6;
        System.out.println(max);
        max = findMaximumXOR2(new int[]{8, 10, 2});
        assert max == 10;
        System.out.println(max);
        max = findMaximumXOR2(new int[]{14, 70, 53, 83, 49, 91, 36, 80, 92, 51, 66, 70});
        assert max == 127;
        System.out.println(max);
        max = findMaximumXOR2(new int[]{1});
        assert max == 0;
        System.out.println(max);
        max = findMaximumXOR2(new int[]{7, 4, 6});
        assert max == 3;
        System.out.println(max);
    }

    private static int findMaximumXOR2(int[] nums) {
        // 得到每一个数的二进制表达
        int[][] arr = new int[nums.length][32];
        for (int i = 0; i < nums.length; i++) {
            arr[i] = new int[32];
            int num = nums[i];
            int shift = 0;
            while (num > 0) {
                arr[i][shift++] = (num & 1);
                num = (num >> 1);
            }
        }
        // 判断arr数组每一列，如果不是全0就
        int max = 0;
        for (int j = 0; j < 32; j++) {
            int val = 0;
            for (int i = 0; i < nums.length; i++) {
                val = (val | arr[i][j]);
            }
            max += (val << j);
        }
        //
        return max;
    }

    private static int findMaximumXOR(int[] nums) {
        if (nums.length == 1) {
            return 0;
        }

        int max = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            int iMax = 0;
            for (int j = i + 1; j < nums.length; j++) {
                int val = nums[i] ^ nums[j];
                if (val > iMax) {
                    iMax = val;
                }
            }
            if (iMax > max) {
                max = iMax;
            }
        }
        return max;
    }
}
