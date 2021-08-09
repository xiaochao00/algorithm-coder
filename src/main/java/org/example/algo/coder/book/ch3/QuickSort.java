package org.example.algo.coder.book.ch3;

import java.util.Arrays;

/**
 * 快速排序
 *
 * @author shichao
 * @since 1.0.0
 * 2021/8/9 23:58
 */
public class QuickSort {
    public static void main(String[] args) {
        QuickSort quickSort = new QuickSort();
        quickSort.sort(new int[]{10, 8, 4, 3, 2, 9, 8, 5, 1, 2});
    }

    public void sort(int[] nums) {
        if (nums == null || nums.length <= 0) {
            System.err.println("Get empty sorted list");
            return;
        }
        System.out.println("Begin sort nums:" + Arrays.toString(nums));
        //
        quickSort(0, nums.length - 1, nums);
        System.out.println("After sort nums:" + Arrays.toString(nums));
    }

    private void quickSort(int baseIndex, int endIndex, int[] nums) {
        if (baseIndex < 0 || endIndex > nums.length || baseIndex > endIndex) {
            return;
        }
        int pivot = nums[baseIndex];
        //
        int left = baseIndex;
        int right = endIndex;
        while (left < right) {
            while (right > left && nums[right] >= pivot) {
                right--;
            }
            if (left < right) {
                // 互换
                nums[left++] = nums[right];
            }
            while (nums[left] < pivot && left < right) {
                left++;
            }
            if (left < right) {
                // 互换
                nums[right--] = nums[left];
            }
        }
        //
        if (left == right) {
            nums[left] = pivot;
        }
        quickSort(baseIndex , left - 1, nums);
        quickSort(left + 1, endIndex, nums);
    }
}
