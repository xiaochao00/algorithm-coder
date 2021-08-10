package org.example.algo.coder.book.ch3;

import java.util.Arrays;

/**
 * 堆排序
 *
 * @author shichao
 * @since 1.0.0
 * 2021/8/10 23:42
 */
public class HeapSort {
    public static void main(String[] args) {
        HeapSort heapSort = new HeapSort();
        heapSort.sort(new int[]{10, 20, 0, 4, 6, 1, 8, 3});
    }

    public void sort(int[] nums) {
        if (nums == null || nums.length <= 0) {
            System.err.println("Get empty sorted list");
            return;
        }
        System.out.println("Begin sort:" + Arrays.toString(nums));
        //自下向上调整堆
        adjustFromBottomToTop(nums, nums.length);
        //
        for (int i = nums.length - 1; i > 0; i--) {
            int temp = nums[0];
            nums[0] = nums[i];
            nums[i] = temp;
            //再重新调整
            adjustFromBottomToTop(nums, i);
        }
        System.out.println("Begin sort:" + Arrays.toString(nums));
    }

    /**
     * 从下网上调整
     *
     * @param nums 数组
     * @param end  结束调整的位置
     */
    private void adjustFromBottomToTop(int[] nums, int end) {
        int mid = end / 2;
        for (int i = mid; i >= 0; i--) {
            int left = 2 * i + 1;
            int right = 2 * i + 2;
            //
            int max = nums[i];
            if (left < end && nums[left] > max) {
                int temp = nums[left];
                nums[left] = max;
                max = temp;
            }
            if (right < end && nums[right] > max) {
                int temp = nums[right];
                nums[right] = max;
                max = temp;
            }
            if (max != nums[i]) {
                nums[i] = max;
            }
        }
    }
}
