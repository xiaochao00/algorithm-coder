//存在一个由 n 个节点组成的无向连通图，图中的节点按从 0 到 n - 1 编号。 
//
// 给你一个数组 graph 表示这个图。其中，graph[i] 是一个列表，由所有与节点 i 直接相连的节点组成。 
//
// 返回能够访问所有节点的最短路径的长度。你可以在任一节点开始和停止，也可以多次重访节点，并且可以重用边。 
//
// 
//
// 
// 
//
// 示例 1： 
//
// 
//输入：graph = [[1,2,3],[0],[0],[0]]
//输出：4
//解释：一种可能的路径为 [1,0,2,0,3] 
//
// 示例 2： 
//
// 
//
// 
//输入：graph = [[1],[0,2,4],[1,3,4],[2],[1,2]]
//输出：4
//解释：一种可能的路径为 [0,1,4,2,3]
// 
//
// 
//
// 提示： 
//
// 
// n == graph.length 
// 1 <= n <= 12 
// 0 <= graph[i].length < n 
// graph[i] 不包含 i 
// 如果 graph[a] 包含 b ，那么 graph[b] 也包含 a 
// 输入的图总是连通图 
// 
// Related Topics 位运算 广度优先搜索 图 动态规划 状态压缩 
// 👍 206 👎 0


package com.shuzijun.leetcode.editor.cn;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class Item0847ShortestPathVisitingAllNodes {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int res = solution.shortestPathLength(new int[][]{{1, 2, 3}, {0}, {0}, {0}});
        System.out.println(res);

        res = solution.shortestPathLength(new int[][]{{1}, {0, 2, 4}, {1, 3}, {2}, {1, 5}, {4}});
        System.out.println(res);
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    static class Solution {

        public int shortestPathLength(int[][] graph) {
            // 问题1：如何达到最短？广度优先遍历，看谁先遍历完所有节点；
            // 问题2：如何直到所有点在路径上？通过位运算存储路径信息；
            int[][] arr = new int[graph.length][3];
            Queue<int[]> queue = new LinkedList<>();
            // seeArr[i][j] 标记是否以同一路径j达到i
            boolean[][] seeArr = new boolean[graph.length][1 << graph.length];
            for (int i = 0; i < graph.length; i++) {
                arr[i] = new int[]{i, 1 << i, 0};
                queue.offer(arr[i]);
                seeArr[i][1 << i] = true;
            }
            //
            int step = 0;
            while (!queue.isEmpty()) {
                int[] curArr = queue.poll();
                int start = curArr[0], mask = curArr[1], dis = curArr[2];
                if (mask == ((1 << graph.length) - 1)) {
                    step = dis;
                    break;
                }
                //
                for (int next : graph[start]) {
                    int nextMask = mask | (1 << next);
                    if (!seeArr[start][nextMask]) {
                        seeArr[start][next] = true;
                        queue.offer(new int[]{next, nextMask, dis + 1});
                    }
                }
            }
            return step;
        }

        public int shortestPathLength2(int[][] graph) {
            int minCount = graph.length + 1;
            for (int[] ints : graph) {
                if (ints.length < minCount) {
                    minCount = ints.length;
                }
            }
            //
            int minStep = Integer.MAX_VALUE;
            for (int i = 0; i < graph.length; i++) {
                if (graph[i].length == minCount) {
                    int step = getPathNum(i, graph);
                    if (step < minStep) {
                        minStep = step;
                    }
                }
            }
            return minStep;
        }

        private int getPathNum(int start, int[][] graph) {
            int[] counts = new int[graph.length];
            for (int i = 0; i < graph.length; i++) {
                counts[i] = graph[i].length;
            }
            //
            int step = 0;
            Queue<Integer> queue = new LinkedList<>();
            Set<Integer> records = new HashSet<>();
            queue.offer(start);
            while (!queue.isEmpty()) {
                int n = queue.poll();
                records.add(n);
                counts[n] = counts[n] - 1;
                if (records.size() == graph.length) {
                    break;
                }
                int minCount = graph.length;
                int minNum = -1;
                for (int next : graph[n]) {
                    if (records.contains(next) || counts[next] <= 0) {
                        continue;
                    }
                    if (minCount > counts[next]) {
                        minCount = counts[next];
                        minNum = next;
                    }
                }
                //
                if (minNum == -1) {
                    for (int next : graph[n]) {
                        if (records.contains(next) && minCount > counts[next] && counts[next] > 0) {
                            minCount = counts[next];
                            minNum = next;
                        }
                    }
                }
                //
                queue.offer(minNum);
                step++;
            }
            return step;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}