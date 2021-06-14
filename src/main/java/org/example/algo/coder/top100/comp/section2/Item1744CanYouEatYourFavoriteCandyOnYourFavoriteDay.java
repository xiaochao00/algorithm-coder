package org.example.algo.coder.top100.comp.section2;

import java.util.Arrays;

public class Item1744CanYouEatYourFavoriteCandyOnYourFavoriteDay {
    public static void main(String[] args) {
        Solution solution = new Item1744CanYouEatYourFavoriteCandyOnYourFavoriteDay().new Solution();
        boolean[] res = solution.canEat(new int[]{7, 4, 5, 3, 8}, new int[][]{{0, 2, 2}});
        System.out.println(Arrays.toString(res));

        res = solution.canEat(new int[]{5, 2, 6, 4, 1}, new int[][]{{3, 1, 2}, {4, 10, 3}, {3, 10, 100}, {4, 100, 30}
                , {1, 3, 1}});
        System.out.println(Arrays.toString(res));

        res = solution.canEat(new int[]{5215, 14414, 67303, 93431, 44959, 34974, 22935, 64205, 28863, 3436, 45640, 34940, 38519, 5705, 14594, 30510, 4418, 87954, 8423, 65872, 79062, 83736, 47851, 64523, 15639, 19173, 88996, 97578, 1106, 17767, 63298, 8620, 67281, 76666, 50386, 97303, 26476, 95239, 21967, 31606, 3943, 33752, 29634, 35981, 42216, 88584, 2774, 3839, 81067, 59193, 225, 8289, 9295, 9268, 4762, 2276, 7641, 3542, 3415, 1372, 5538, 878, 5051, 7631, 1394, 5372, 2384, 2050, 6766, 3616, 7181, 7605, 3718, 8498, 7065, 1369, 1967, 2781, 7598, 6562, 7150, 8132, 1276, 6656, 1868, 8584, 9442, 8762, 6210, 6963, 4068, 1605, 2780, 556, 6825, 4961, 4041, 4923, 8660, 4114}, new int[][]{{91, 244597, 840227137}});
        System.out.println(Arrays.toString(res));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public boolean[] canEat(int[] candiesCount, int[][] queries) {
            // 前缀和
            long[] sums = new long[candiesCount.length];
            long sum = 0;
            for (int i = 0; i < candiesCount.length; i++) {
                sum += candiesCount[i];
                sums[i] = sum;
            }
            //
            boolean[] res = new boolean[queries.length];
            for (int i = 0; i < queries.length; i++) {
                int[] query = queries[i];
                int favType = query[0];
                int favDay = query[1];
                int dayCap = query[2];
                // 第favDay天吃糖的数目的范围
                long x1 = favDay + 1;
                long y1 = (long) (favDay + 1) * dayCap;
                // 第favType类糖，吃第一颗时候的总数目，和吃完的时候的总数目
                long x2 = (favType == 0) ? 1 : sums[favType - 1] + 1;
                long y2 = sums[favType];
                // 结果为两者是否有交集
                res[i] = !(x1 > y2 || x2 > y1);
            }
            return res;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}