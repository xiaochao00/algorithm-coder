package org.example.algo.coder.top100;

/**
 * 4
 * https://leetcode-cn.com/problems/median-of-two-sorted-arrays/
 *
 * @author shichao
 * @since 1.0.0
 * 2021/5/5 12:53
 */
public class Item0004 {
    public static void main(String[] args) {
        double res = findMedianSortedArrays(new int[]{1, 3}, new int[]{2});
        assert res == 2.0 : "error:" + res;
        res = findMedianSortedArrays(new int[]{1, 2}, new int[]{3, 4});
        assert res == 2.5 : "error:" + res;
        res = findMedianSortedArrays(new int[]{0, 0}, new int[]{0, 0});
        assert res == 0 : "error:" + res;
        res = findMedianSortedArrays(new int[]{1, 3}, new int[]{2, 7});
        assert res == 2.5 : "error:" + res;
        res = findMedianSortedArrays(new int[]{1, 3}, new int[]{2});
        assert res == 2.0 : "error:" + res;
        res = findMedianSortedArrays(new int[]{}, new int[]{2, 3});
        assert res == 2.5 : "error:" + res;
        res = findMedianSortedArrays(new int[]{2, 2, 2}, new int[]{2, 2, 2, 2});
        assert res == 2.0 : "error:" + res;
    }

    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        // 判断有空数组的情况1:两个都是空
        if (m == 0 && n == 0) {
            return 0;
        }
        // 判断有空数组情况2:只有一个为空
        if (m == 0 || n == 0) {
            int[] resNums = (m != 0) ? nums1 : nums2;
            if (resNums.length % 2 != 0) {
                return resNums[resNums.length / 2];
            }
            return (resNums[resNums.length / 2] + resNums[resNums.length / 2 - 1]) / 2.0;
        }
        // 两个数组都不为空的情况
        int resLen = (m + n) / 2;
        if ((m + n) % 2 == 0) {
            resLen--;
        }
        int res = 0;
        int mIndex = 0;
        int nIndex = 0;
        int count = 0;
        while (count <= resLen) {
            count++;
            int curM = Integer.MAX_VALUE;
            int curN = Integer.MAX_VALUE;
            if (mIndex < m) {
                curM = nums1[mIndex];
            }
            if (nIndex < n) {
                curN = nums2[nIndex];
            }
            if (curM <= curN) {
                mIndex++;
                res = curM;
            } else {
                nIndex++;
                res = curN;
            }
        }

        if ((m + n) % 2 != 0) {
            return res;
        }
        if (mIndex < m && nIndex < n) {
            res += Math.min(nums2[nIndex], nums1[mIndex]);
            return res / 2.0;
        }
        if (mIndex == m) {
            res += nums2[nIndex];
            return res / 2.0;
        }
        return (res + nums1[mIndex]) / 2.0;
    }
}
