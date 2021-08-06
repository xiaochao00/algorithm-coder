//在有向图中，以某个节点为起始节点，从该点出发，每一步沿着图中的一条有向边行走。如果到达的节点是终点（即它没有连出的有向边），则停止。 
//
// 对于一个起始节点，如果从该节点出发，无论每一步选择沿哪条有向边行走，最后必然在有限步内到达终点，则将该起始节点称作是 安全 的。 
//
// 返回一个由图中所有安全的起始节点组成的数组作为答案。答案数组中的元素应当按 升序 排列。 
//
// 该有向图有 n 个节点，按 0 到 n - 1 编号，其中 n 是 graph 的节点数。图以下述形式给出：graph[i] 是编号 j 节点的一个列表，
//满足 (i, j) 是图的一条有向边。 
//
// 
//
// 
// 
// 示例 1： 
//
// 
//输入：graph = [[1,2],[2,3],[5],[0],[5],[],[]]
//输出：[2,4,5,6]
//解释：示意图如上。
// 
//
// 示例 2： 
//
// 
//输入：graph = [[1,2,3,4],[1,2],[3,4],[0,4],[]]
//输出：[4]
// 
//
// 
//
// 提示： 
//
// 
// n == graph.length 
// 1 <= n <= 104 
// 0 <= graph[i].length <= n 
// graph[i] 按严格递增顺序排列。 
// 图中可能包含自环。 
// 图中边的数目在范围 [1, 4 * 104] 内。 
// 
// 
// 
// Related Topics 深度优先搜索 广度优先搜索 图 拓扑排序 
// 👍 245 👎 0


package com.shuzijun.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class FindEventualSafeStates {
    public static void main(String[] args) {
        Solution solution = new Solution();
        List<Integer> res = solution.eventualSafeNodes2(new int[][]{{1, 2}, {2, 3}, {5}, {0}, {5}, {}, {}});
        System.out.println(res);

        res = solution.eventualSafeNodes2(new int[][]{{1}, {}});
        System.out.println(res);

        res = solution.eventualSafeNodes2(new int[][]{{1, 2, 3, 4}, {1, 2}, {3, 4}, {0, 4}, {}});
        System.out.println(res);
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    static class Solution {
        private static final int INIT = 0;
        /**
         * 当前节点是 遍历过/环 状态
         */
        private static final int RING = 1;
        /**
         * 当前节点是 结束 状态
         */
        private static final int END = 2;

        public List<Integer> eventualSafeNodes(int[][] graph) {
            // 官方解法1：深度优先遍历+三色标记
            int[] color = new int[graph.length];
            List<Integer> ends = new ArrayList<>();
            for (int i = 0; i < graph.length; i++) {
                if (isSafe(i, graph, color)) {
                    ends.add(i);
                }
            }
            return ends;
        }

        private boolean isSafe(int start, int[][] graph, int[] color) {
            if (color[start] > INIT) {
                return color[start] == END;
            }
            color[start] = RING;
            for (int n : graph[start]) {
                if (!isSafe(n, graph, color)) {
                    return false;
                }
            }
            color[start] = END;
            return true;
        }

        public List<Integer> eventualSafeNodes2(int[][] graph) {
            // 官方解法2：拓扑排序
            // 问题1：拓扑排序解决什么问题？通过拓扑排序，可以识别出区分出环的部分和非环的部分
            // 问题2：如何反转方向？通过二维数组/列表 重新存储其关系
            // 问题3：如何找到每次迭代过程中的安全点？ 通过一个数组存储每个节点的入度值
            // 问题4：如何找到安全点后，去掉它指向的边? 通过使入度值减1实现即可

            // rGraph[i][j] i到j有边(实际为j到i有边)
            List<List<Integer>> rGraph = new ArrayList<>(graph.length);
            for (int i = 0; i < graph.length; i++) {
                rGraph.add(new ArrayList<>());
            }
            // counts[i] i的入度
            int[] counts = new int[graph.length];
            //初始化遍历
            for (int i = 0; i < graph.length; i++) {
                for (int n : graph[i]) {
                    rGraph.get(n).add(i);
                }
                counts[i] = graph[i].length;
            }
            // 开始
            Queue<Integer> queue = new LinkedList<>();
            // 初始化队列
            for (int i = 0; i < counts.length; i++) {
                if (counts[i] == 0) {
                    queue.offer(i);
                }
            }
            while (!queue.isEmpty()) {
                Integer start = queue.poll();
                for (int n : rGraph.get(start)) {
                    counts[n]--;
                    if (counts[n] == 0) {
                        queue.offer(n);
                    }
                }
            }
            // 最后判断计数中小于等于0的序号的列表
            List<Integer> ends = new ArrayList<>();
            for (int i = 0; i < counts.length; i++) {
                if (counts[i] <= 0) {
                    ends.add(i);
                }
            }
            return ends;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)


}