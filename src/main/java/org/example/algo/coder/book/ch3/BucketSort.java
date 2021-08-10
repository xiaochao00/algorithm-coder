package org.example.algo.coder.book.ch3;

import java.util.Arrays;

/**
 * 桶排序
 *
 * @author shichao
 * @since 1.0.0
 * 2021/8/11 0:20
 */
public class BucketSort {
    public static void main(String[] args) {
        BucketSort bucketSort = new BucketSort();
        bucketSort.sort(new int[]{25, 10, 9, 8, 0, 16, 15, 20, 23, 22});
    }

    public void sort(int[] nums) {
        if (nums == null || nums.length <= 0) {
            System.err.println("Get empty sorted list");
            return;
        }
        System.out.println("Before sort:" + Arrays.toString(nums));
        int bucketSize = 5;
        int minNum = Integer.MAX_VALUE;
        int maxNum = Integer.MIN_VALUE;
        for (int num : nums) {
            if (num > maxNum) {
                maxNum = num;
            }
            if (num < minNum) {
                minNum = num;
            }
        }
        // 总共有bucketNum个桶，bucketIndex的数据范围是:[min+bucketIndex*bucketSize,(min+bucketIndex+1)*bucketSize-1]
        int bucketNum = (maxNum - minNum) / bucketSize + 1;
        //
        int[][] bucketNums = new int[bucketNum][0];
        //
        for (int num : nums) {
            int bucketIndex = (num - minNum) / bucketSize;
            bucketNums[bucketIndex] = insertBucket(bucketNums[bucketIndex], num);
        }
        //
        int index = 0;
        for (int i = 0; i < bucketNum; i++) {
            for (int num : bucketNums[i]) {
                nums[index++] = num;
            }
        }
        System.out.println("After sort:" + Arrays.toString(nums));
    }

    private int[] insertBucket(int[] nums, int num) {
        if (nums == null || nums.length == 0) {
            return new int[]{num};
        }
        //
        int[] newNums = new int[nums.length + 1];
        System.arraycopy(nums, 0, newNums, 0, nums.length);
        int i = newNums.length - 1;
        while (i > 0 && newNums[i - 1] > num) {
            newNums[i] = newNums[i - 1];
            i--;
        }
        newNums[i] = num;
        return newNums;
    }


}
