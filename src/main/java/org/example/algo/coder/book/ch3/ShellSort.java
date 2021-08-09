package org.example.algo.coder.book.ch3;

import java.util.Arrays;

/**
 * 希尔排序
 *
 * @author shichao
 * @since 1.0.0
 * 2021/8/9 23:24
 */
public class ShellSort {
    public static void main(String[] args) {
        ShellSort shellSort = new ShellSort();
        shellSort.sort(new int[]{10, 4, 9, 0, 3, 8, 2});
    }

    public void sort(int[] nums) {
        if (nums == null || nums.length <= 0) {
            System.err.println("Get empty sorted list");
            return;
        }
        //
        System.out.println("Begin sort nums:" + Arrays.toString(nums));
        int offset = nums.length / 2;
        while (offset >= 1) {
            //
            for (int i = offset; i < nums.length; i++) {
                int temp = nums[i];
                //
                int j = i;
                while (j >= offset && nums[j - offset] > temp) {
                    nums[j] = nums[j - offset];
                    j = j - offset;
                }
                //
                nums[j] = temp;
            }
            offset = offset / 2;
        }
        System.out.println("After sort nms:" + Arrays.toString(nums));
    }
}
