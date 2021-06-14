package org.example.algo.coder.top100.comp.section2;

import java.util.Arrays;
import java.util.HashSet;
import java.util.PriorityQueue;

public class Item1738FindKthLargestXorCoordinateValue {
    public static void main(String[] args) {
        Solution solution = new Item1738FindKthLargestXorCoordinateValue().new Solution();
        int res = solution.kthLargestValue(new int[][]{{5, 2}, {1, 6}}, 1);
        assert res == 7;
        System.out.println(res);

        res = solution.kthLargestValue(new int[][]{{5, 2}, {1, 6}}, 2);
        assert res == 5;
        System.out.println(res);

        res = solution.kthLargestValue(new int[][]{{5, 2}, {1, 6}}, 3);
        assert res == 4;
        System.out.println(res);

        res = solution.kthLargestValue(new int[][]{{5, 2}, {1, 6}}, 4);
        assert res == 0;
        System.out.println(res);
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int kthLargestValue(int[][] matrix, int k) {
            int column = matrix.length;
            // 第一列的值列表，value0[i]=value[i-1]^matrix[i][0]
            Integer[] value0 = new Integer[column];
            // 第2列的值列表，value1[i]=value[i-1]^matrix[i][0]^matrix[i][1]
            Integer[] value1 = new Integer[column];

            // 初始化
            value0[0] = matrix[0][0];
            value1[0] = matrix[0][1] ^ value0[0];
            //
            for (int i = 1; i < column; i++) {
                value0[i] = value0[i - 1] ^ matrix[i][0];
            }
            //
            for (int i = 1; i < column; i++) {
                value1[i] = value1[i - 1] ^ matrix[i][0] ^ matrix[i][1];
            }
            // 利用优先级队列实现大顶堆
            PriorityQueue<Integer> maxHeap = new PriorityQueue<>((o1, o2) -> o2 - o1);
            //
            value0 = (new HashSet<>(Arrays.asList(value0))).toArray(new Integer[0]);
            value1 = (new HashSet<>(Arrays.asList(value1))).toArray(new Integer[0]);
            //
            for (int i = 0; i < column; i++) {
                maxHeap.add(value0[i]);
                maxHeap.add(value1[i]);
            }
            //
            for (int i = 1; i <= k; i++) {
                int value = maxHeap.poll();
                if (i == k) {
                    return value;
                }
            }

            return -1;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}