package org.example.algo.coder.top100;

/**
 * 1723
 * https://leetcode-cn.com/problems/find-minimum-time-to-finish-all-jobs/
 *
 * @author shichao
 * @since 1.0.0
 * 2021/5/8 22:12
 */
public class Item1723 {
    public static void main(String[] args) {
        int minTime = minimumTimeRequired2(new int[]{12, 13, 14, 17, 25}, 3);
        System.out.println(minTime);
    }

    public static int minimumTimeRequired2(int[] jobs, int k) {
        //
        int n = jobs.length;
        // 计算任意工作组合的和
        int[] sum = new int[1 << n];
        for (int i = 1; i < (1 << n); i++) {
            int x = Integer.numberOfTrailingZeros(i);
            int y = i - (1 << x);
            sum[i] = sum[y] + jobs[x];
        }
        // 压缩初始化dp矩阵，并未很大提升
        int[] preCompressDp = new int[1 << n];
        int[] curPressDp = new int[1 << n];
        if ((1 << n) - 1 >= 0) System.arraycopy(sum, 1, preCompressDp, 1, (1 << n) - 1);
        //
        for (int i = 1; i < k; i++) {
            for (int j = 0; j < (1 << n); j++) {
                // dp[i][j]
                int minTime = Integer.MAX_VALUE;
                for (int s = j; s != 0; s = (s - 1) & j) {
                    minTime = Math.min(minTime, Math.max(sum[s], preCompressDp[j - s]));
                }
                curPressDp[j] = minTime;
            }
            System.arraycopy(curPressDp, 0, preCompressDp, 0, curPressDp.length);
        }
        return preCompressDp[(1 << n) - 1];
    }

    public static int minimumTimeRequired(int[] jobs, int k) {
        //
        int n = jobs.length;
        // 计算任意工作组合的和
        int[] sum = new int[1 << n];
        for (int i = 1; i < (1 << n); i++) {
            int x = Integer.numberOfTrailingZeros(i);
            int y = i - (1 << x);
            sum[i] = sum[y] + jobs[x];
        }
        // 初始化dp矩阵
        int[][] dp = new int[k][1 << n];
        if ((1 << n) - 1 >= 0) System.arraycopy(sum, 1, dp[0], 1, (1 << n) - 1);
        //
        for (int i = 1; i < k; i++) {
            for (int j = 0; j < (1 << n); j++) {
                // dp[i][j]
                int minTime = Integer.MAX_VALUE;
                for (int s = j; s != 0; s = (s - 1) & j) {
                    minTime = Math.min(minTime, Math.max(sum[s], dp[i - 1][j - s]));
                }
                dp[i][j] = minTime;
            }
        }
        return dp[k - 1][(1 << n) - 1];
    }
}
