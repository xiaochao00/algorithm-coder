package org.example.algo.coder.top100.comp.section2;

public class Item0011ContainerWithMostWater {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int res = solution.maxArea(new int[]{1, 8, 6, 2, 5, 4, 8, 3, 7});
        System.out.println(res);
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    static class Solution {
        public int maxArea(int[] height) {
            // 双指针解法
            int p = 0;
            int q = height.length - 1;
            int maxArea = 0;
            //
            while (p < q) {
                int area = Math.min(height[p], height[q]) * (q - p);
                if (area > maxArea) {
                    maxArea = area;
                }
                // 开始移动
                if (height[p] >= height[q]) {
                    q--;
                } else {
                    p++;
                }
            }
            return maxArea;
        }

        public int maxArea2(int[] height) {
            int maxArea = 0;
            for (int i = 0; i < height.length; i++) {
                int maxDiff = -1;
                for (int j = 0; j < height.length; j++) {
                    if (j != i && height[j] >= height[i]) {
                        int diff = Math.abs(j - i);
                        if (diff > maxDiff) {
                            int area = diff * height[i];
                            if (area > maxArea) {
                                maxArea = area;
                            }
                            maxDiff = diff;
                        }
                    }
                }
            }
            return maxArea;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}