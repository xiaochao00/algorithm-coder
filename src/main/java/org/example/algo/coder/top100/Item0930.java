package org.example.algo.coder.top100;

import org.junit.Assert;

import java.util.HashMap;
import java.util.Map;

/**
 * 0930
 *
 * @author shichao
 * @since 1.0.0
 * 2021/7/8 0:57
 */
public class Item0930 {
    public static void main(String[] args) {
        int res = numSubarraysWithSum3(new int[]{0, 0, 0, 0, 0}, 0);
        System.out.println(res);
        Assert.assertEquals(res, 15);

        res = numSubarraysWithSum3(new int[]{1, 0, 1, 0, 1}, 2);
        System.out.println(res);
        Assert.assertEquals(res, 4);
    }

    public static int numSubarraysWithSum(int[] nums, int goal) {
        // 前缀和
        int[] sum = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            sum[i] = (i == 0 ? nums[i] : sum[i - 1] + nums[i]);
        }
        int count = 0;
        // dp[i][j]=sum[j]-sum[i-1]
        for (int i = 0; i < nums.length; i++) {
            for (int j = i; j < nums.length; j++) {
                int diff = (i == 0 ? sum[j] : sum[j] - sum[i - 1]);
                if (diff == goal) {
                    count++;
                }
            }
        }
        return count;
    }

    public static int numSubarraysWithSum2(int[] nums, int goal) {
        // 采用前缀数组方式+哈希方式
        int res = 0;
        int sum = 0;
        Map<Integer, Integer> countMap = new HashMap<>();
        for (int num : nums) {
            countMap.put(sum, countMap.getOrDefault(sum, 0) + 1);
            sum += num;
            res += countMap.getOrDefault(sum - goal, 0);
        }
        return res;
    }

    public static int numSubarraysWithSum3(int[] nums, int goal) {
        // 滑动窗口的方式

        return -1;
    }

}
