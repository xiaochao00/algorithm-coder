package org.example.algo.coder.book.ch3;

import java.util.Arrays;

/**
 * 插入法排序
 *
 * @author shichao
 * @since 1.0.0
 * 2021/8/9 23:08
 */
public class InsertSort {
    public static void main(String[] args) {
        InsertSort insertSort = new InsertSort();
        insertSort.sort(new int[]{10, 4, 9, 0, 3, 8, 2});
    }

    public void sort(int[] nums) {
        if (nums == null || nums.length <= 0) {
            System.err.println("Get empty sorted list");
            return;
        }
        System.out.println("Begin sort nums:" + Arrays.toString(nums));
        for (int i = 1; i < nums.length; i++) {
            // nums[i] 插入到 nums[0~i-1]中
            int temp = nums[i];
            int j = i;
            while (j > 0 && nums[j - 1] > temp) {
                nums[j] = nums[j - 1];
                j--;
            }
            nums[j] = temp;
        }
        System.out.println("End sort to:" + Arrays.toString(nums));
    }

}
