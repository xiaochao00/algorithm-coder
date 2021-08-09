package org.example.algo.coder.book.ch3;

import java.util.Arrays;

/**
 * 冒泡排序
 *
 * @author shichao
 * @since 1.0.0
 * 2021/8/9 22:49
 */
public class BubbleSort {
    public static void main(String[] args) {
        BubbleSort bubbleSort = new BubbleSort();
        bubbleSort.sort(new int[]{5, 4, 7, 2, 1, 0, 9, 6});
    }

    public void sort(int[] nums) {
        if (nums == null || nums.length <= 0) {
            System.err.println("Get empty sorted list");
            return;
        }
        System.out.println("Begin sort nums:" + Arrays.toString(nums));
        // 时间复杂度 n^2，空间复杂度 1
        for (int i = nums.length - 1; i >= 0; i--) {
            for (int j = 0; j < i; j++) {
                if (nums[j] > nums[j + 1]) {
                    // 位运算实现交换
                    nums[j] = nums[j] ^ nums[j + 1];
                    nums[j + 1] ^= nums[j];
                    nums[j] ^= nums[j + 1];
                }
            }
        }
        System.out.println("End sort to:" + Arrays.toString(nums));
    }
}
