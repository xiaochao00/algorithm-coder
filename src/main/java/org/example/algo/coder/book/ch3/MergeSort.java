package org.example.algo.coder.book.ch3;

import java.util.Arrays;

/**
 * 归并排序
 *
 * @author shichao
 * @since 1.0.0
 * 2021/8/10 22:41
 */
public class MergeSort {
    public static void main(String[] args) {
        int[] nums1 = new int[]{10, 30, 4, 0, 9, 8, 2, 5};
        int[] nums2 = new int[]{10, 30, 4, 0, 9, 8, 2, 5, 7};
        MergeSort mergeSort = new MergeSort();
        mergeSort.sort(nums1);
        mergeSort.sort(nums2);
        //
        System.out.println("Begin sort:" + Arrays.toString(nums1));
        int[] newNums = mergeSort.sort2(nums1);
        System.out.println("Begin sort:" + Arrays.toString(newNums));
    }

    public int[] sort2(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return nums;
        }
        int mid = nums.length / 2;
        int[] left = Arrays.copyOfRange(nums, 0, mid);
        int[] right = Arrays.copyOfRange(nums, mid, nums.length);
        return merge(sort2(left), sort2(right));
    }

    /**
     * 合并两个数组为新的数组
     *
     * @param left  左半数组
     * @param right 右半数组
     * @return 合并后的数组
     */
    private int[] merge(int[] left, int[] right) {
        int size = left.length + right.length;
        int[] nums = new int[size];
        //
        int i = 0, j = 0, index = 0;
        while (i < left.length && j < right.length) {
            if (left[i] <= right[j]) {
                nums[index++] = left[i++];
            } else {
                nums[index++] = right[j++];
            }
        }
        //
        if (i == left.length) {
            for (; j < right.length; j++) {
                nums[index++] = right[j];
            }
        } else {
            for (; i < left.length; i++) {
                nums[index++] = left[i];
            }
        }
        //
        return nums;
    }

    public void sort(int[] nums) {
        if (nums == null || nums.length <= 0) {
            System.err.println("Get empty sorted list");
            return;
        }
        System.out.println("Begin sort:" + Arrays.toString(nums));
        mergeSort(nums, 0, nums.length - 1);
        System.out.println("After sort:" + Arrays.toString(nums));
    }

    private void mergeSort(int[] nums, int left, int right) {
        if (right <= left) {
            return;
        }
        if (right - left == 1) {
            if (nums[left] > nums[right]) {
                // 位运算实现交换
                nums[left] ^= nums[right];
                nums[right] ^= nums[left];
                nums[left] ^= nums[right];
            }
            return;
        }
        //
        int mid = (left + right) / 2;
        mergeSort(nums, left, mid);
        mergeSort(nums, mid + 1, right);
        //插入排序法，
        for (int i = mid + 1; i <= right; i++) {
            int temp = nums[i];
            int j = i;
            while (j > 0 && temp < nums[j - 1]) {
                nums[j] = nums[j - 1];
                j--;
            }
            nums[j] = temp;
        }
    }
}
