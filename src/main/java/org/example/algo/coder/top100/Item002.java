package org.example.algo.coder.top100;

/**
 * 002
 * https://leetcode-cn.com/problems/add-two-numbers/
 *
 * @author shichao
 * @since 1.0.0
 * 2021/4/30 19:49
 */
public class Item002 {
    public static void main(String[] args) {
        int[] n1 = new int[]{2, 4, 3};
        int[] n2 = new int[]{5, 6, 4};
        //
        ListNode list1 = arrayToListNode(n1);
        ListNode list2 = arrayToListNode(n2);
        System.out.println(list1);
        System.out.println(list2);
        //
        ListNode addNode = addTwoNumbers(list1, list2);
        System.out.println(addNode);
    }

    private static ListNode arrayToListNode(int[] arr) {
        assert arr != null;
        ListNode node = new ListNode(arr[0]);
        ListNode res = node;
        for (int i = 1; i < arr.length; i++) {
            ListNode next = new ListNode(arr[i]);
            node.next = next;
            node = next;
        }
        return res;
    }


    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }

        @Override
        public String toString() {
            StringBuilder str = new StringBuilder();
            str.append(val);
            ListNode n = this;
            if (n.next != null) {
                str.append(",").append(n.next.toString());
            }
            return str.toString();
        }
    }

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode n1 = l1;
        ListNode n2 = l2;
        ListNode nAdd = new ListNode(0);
        ListNode res = nAdd;
        ListNode pre = null;
        while (n1 != null && n2 != null) {
            int sum = nAdd.val + n1.val + n2.val;
            nAdd.val = sum % 10;

            n1 = n1.next;
            n2 = n2.next;
            ListNode nNext = new ListNode(sum / 10);
            pre = nAdd;
            nAdd.next = nNext;
            nAdd = nNext;
        }
        ListNode nLeft = (n1 == null ? n2 : n1);
        while (nLeft != null) {
            int sum = nAdd.val + nLeft.val;
            nAdd.val = sum % 10;

            pre = nAdd;

            ListNode nNext = new ListNode(sum / 10);
            nAdd.next = nNext;
            nAdd = nNext;
            nLeft = nLeft.next;
        }
        if (nAdd.val == 0 && pre != null) {
            pre.next = null;
        }
        return res;
    }

}
