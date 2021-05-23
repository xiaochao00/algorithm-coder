package org.example.algo.coder.top100.comp;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.Collections.reverse;

/**
 * Item0692TopKFrequentWords
 *
 * @author shichao
 * @since 1.0.0
 * 2021/5/23 19:10
 */
public class Item0692TopKFrequentWords {
    public static void main(String[] args) {
        Solution solution = new Item0692TopKFrequentWords().new Solution();
        List<String> res = solution.topKFrequent(new String[]{"the", "day", "is", "sunny", "the", "the", "the",
                "sunny", "is", "is"}, 4);
        System.out.println(res);

    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public List<String> topKFrequent(String[] words, int k) {
            Map<String, WordEntry> map = new HashMap<>();
            for (String word : words) {
                if (map.containsKey(word)) {
                    WordEntry entry = map.get(word);
                    entry.setCount(entry.getCount() + 1);
                } else {
                    map.put(word, new WordEntry(word, 1));
                }
            }
            //
            SmallHeap<WordEntry> smallHeap = new SmallHeap<>(k, WordEntry.class);
            for (Map.Entry<String, WordEntry> entry : map.entrySet()) {
                smallHeap.offer(entry.getValue());
            }
            //
            List<WordEntry> res = new ArrayList<>();
            WordEntry e;
            while ((e = smallHeap.poll()) != null) {
                res.add(e);
            }
            reverse(res);
            return res.stream().map(WordEntry::getValue).collect(Collectors.toList());
        }

    }

    static class SmallHeap<T extends Comparable> {
        private T[] entries;
        private int count;

        SmallHeap(int size, Class<T> clazz) {
            entries = (T[]) Array.newInstance(clazz, size + 1);
        }

        public void offer(T wordEntry) {
            entries[count] = wordEntry;
            count += 1;
            upAdjustHeep(count - 1);
            if (count >= entries.length) {
                // 如果超过数目size，就出来一个
                poll();
            }
        }

        public T poll() {
            if (count <= 0) {
                return null;
            }
            T min = entries[0];
            entries[0] = entries[--count];
            downAdjustHeap(0);
            return min;
        }

        private void upAdjustHeep(int index) {
            // 从下向上调整堆
            int parent = (index - 1) / 2;
            while (parent >= 0 && parent != index) {
                if (entries[parent].compareTo(entries[index]) <= 0) {
                    break;
                }
                T max = entries[parent];
                entries[parent] = entries[index];
                entries[index] = max;
                //
                index = parent;
                parent = (index - 1) / 2;
            }
        }

        private void downAdjustHeap(int index) {
            //从上往下调整
            for (int i = index; (2 * i + 1) < count; i++) {
                int son1Index = 2 * i + 1;
                int son2Index = 2 * i + 2;
                T cur = entries[i];
                T son1 = entries[son1Index];
                //
                int minIndex = i;
                T min = cur;
                if (cur.compareTo(son1) > 0) {
                    minIndex = son1Index;
                    min = son1;
                }
                if (son2Index < count) {
                    T son2 = entries[son2Index];
                    if (min.compareTo(son2) > 0) {
                        minIndex = son2Index;
                        min = son2;
                    }
                }
                //
                if (i != minIndex) {
                    entries[i] = min;
                    entries[minIndex] = cur;
                }
            }
        }
    }

    class WordEntry implements Comparable {
        private String value;
        private int count;

        WordEntry(String value, int count) {
            this.value = value;
            this.count = count;
        }

        public String getValue() {
            return value;
        }

        public int getCount() {
            return count;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public void setCount(int count) {
            this.count = count;
        }

        @Override
        public int compareTo(Object o) {
            int diff = (this).getCount() - ((WordEntry) o).getCount();
            if (diff == 0) {
                return ((WordEntry) o).getValue().compareTo(this.getValue());
            }
            return diff;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)
}
