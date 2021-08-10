package org.example.algo.coder.book.ch3;

import java.util.Arrays;

/**
 * 计数排序
 *
 * @author shichao
 * @since 1.0.0
 * 2021/8/11 0:49
 */
public class CountingSort {
    public static void main(String[] args) {
        CountingSort countingSort = new CountingSort();
        countingSort.sort(new int[]{10, 34, 50, 22, 22, 10, 34, 50, 0});
    }

    public void sort(int[] nums) {
        if (nums == null || nums.length <= 0) {
            System.err.println("Get empty sorted list");
            return;
        }
        System.out.println("Before sort:" + Arrays.toString(nums));
        // 先得到最大最小值
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int num : nums) {
            if (num > max) {
                max = num;
            }
            if (num < min) {
                min = num;
            }
        }
        // counts[i]表示min+i的数出现的次数
        int[] counts = new int[max - min + 1];
        for (int num : nums) {
            int index = num - min;
            counts[index] = counts[index] + 1;
        }
        //
        int index = 0;
        for (int i = 0; i < counts.length; i++) {
            int num = i + min;
            for (int j = 0; j < counts[i]; j++) {
                nums[index++] = num;
            }
        }
        //
        System.out.println("After sort:" + Arrays.toString(nums));
    }
}
