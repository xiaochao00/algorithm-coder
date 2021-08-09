package org.example.algo.coder.book.ch3;

import java.util.Arrays;

/**
 * 选择法排序
 *
 * @author shichao
 * @since 1.0.0
 * 2021/8/9 22:59
 */
public class SelectionSort {
    public static void main(String[] args) {
        SelectionSort selectionSort = new SelectionSort();
        selectionSort.sort(new int[]{9, 10, 3, 4, 6, 11, 0});
    }

    public void sort(int[] nums) {
        if (nums == null || nums.length <= 0) {
            System.err.println("Get empty sorted list");
            return;
        }
        //
        System.out.println("Begin sort nums:" + Arrays.toString(nums));
        for (int i = nums.length - 1; i > 0; i--) {
            int maxIndex = 0;
            for (int j = 1; j <= i; j++) {
                if (nums[j] > nums[maxIndex]) {
                    maxIndex = j;
                }
            }
            // 把maxIndex和i位置上的元素互换
            if (maxIndex != i) {
                // 位运算实现交换
                nums[maxIndex] = nums[maxIndex] ^ nums[i];
                nums[i] ^= nums[maxIndex];
                nums[maxIndex] ^= nums[i];
            }
        }
        System.out.println("After sort nums:" + Arrays.toString(nums));
    }
}
