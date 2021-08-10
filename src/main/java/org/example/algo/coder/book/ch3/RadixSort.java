package org.example.algo.coder.book.ch3;

import java.util.Arrays;

/**
 * 基数排序
 *
 * @author shichao
 * @since 1.0.0
 * 2021/8/11 1:01
 */
public class RadixSort {
    public static void main(String[] args) {
        RadixSort radixSort = new RadixSort();
        radixSort.sort(new int[]{50, 100, 30, 24, 44, 0, 10, 55, 67, 6, 5, 7});
    }

    private static final int DECIMAL = 10;

    public void sort(int[] nums) {
        if (nums == null || nums.length <= 0) {
            System.err.println("Get empty sorted list");
            return;
        }
        System.out.println("Before sort:" + Arrays.toString(nums));
        //
        int max = Integer.MIN_VALUE;
        for (int num : nums) {
            if (num > max) {
                max = num;
            }
        }
        // 判断最大值的位数
        int digitNum = 0;
        while (max != 0) {
            digitNum++;
            max = max / DECIMAL;
        }
        //
        int base = 1;
        for (int i = 0; i < digitNum; i++) {
            int[][] bucketNums = new int[DECIMAL][0];
            for (int num : nums) {
                int index = num / base % DECIMAL;
                //直接拼装，无需排序
                bucketNums[index] = insertMerge(bucketNums[index], num);
            }
            //组装排序后的数组
            int index = 0;
            for (int[] bucketNum : bucketNums) {
                for (int num : bucketNum) {
                    nums[index++] = num;
                }
            }
            //
            base *= DECIMAL;
        }
        System.out.println("After sort:" + Arrays.toString(nums));
    }

    private int[] insertMerge(int[] nums, int num) {
        int[] newNums = new int[nums.length + 1];
        System.arraycopy(nums, 0, newNums, 0, nums.length);
        newNums[nums.length] = num;
        return newNums;
    }
}
