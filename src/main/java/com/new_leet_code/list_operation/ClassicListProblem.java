package com.new_leet_code.list_operation;


public class ClassicListProblem {
    public static void main(String[] args) {

        // 1. 两个可能有环路的单链表是否相交问题
        Node h1 = new Node(0);
        Node h1_2 = new Node(3);
        Node h1_3 = new Node(4);
        Node h1_4 = new Node(5);
        Node h1_5 = new Node(6);
        Node h2 = new Node(0);
        Node h2_1 = new Node(20);
        Node h2_2 = new Node(30);
        Node h2_3 = new Node(40);
        Node h2_4 = new Node(50);
        Node h2_5 = new Node(60);
        h1.next = h1_2;
        h1_2.next = h1_3;
        h1_3.next = h1_4;
        h1_4.next = h1_5;
        h1_5.next = null;
        h1_5.next = h1_3; // 生成环路

        h2.next = h2_1;
        h2_1.next = h2_2;
        h2_2.next = h1_5; // 相交点h1_2

        Node res = Solution.ReturnFirstCrossNode(h1,h2);
        if(res != null)System.out.println(res.value);
        else System.out.println("no such node");
    }

    private static class Node{
        public int value;
        public Node next;
        public Node(int value) {
            this.value = value;
        }
    }
    private static class Solution {
        // 两个有可能有环路的链表相交问题
        public static Node ReturnFirstCrossNode(Node head1,Node head2){
            // 1. 先判断两个单链表是否有环。使用快慢指针的方法
            Node cyrcleNode1 = getCyrcleNode(head1);
            Node cyrcleNode2 = getCyrcleNode(head2);
            // 1.1 一个有环一个无环不可能相交
            if ((cyrcleNode2==null&&cyrcleNode1!=null) || (cyrcleNode1==null&&cyrcleNode2!=null)){
                return null;
            }
            // 1.2 如果两个链表环一样
            Node end = null;
            if(cyrcleNode1==cyrcleNode2){
                // 终止节点就是入环节点 其他可以复用2.0无环方案
                end = cyrcleNode1;
            }
            // 1.3 如果两个环不一样 有可能不相交，也有可能相交，loop1转一圈回到自己过程中和loop2一样才相交
            if(cyrcleNode1!=cyrcleNode2){
                Node tmp = cyrcleNode1.next;
                while(tmp!=cyrcleNode1){
                    if(tmp==cyrcleNode2){
                        return cyrcleNode1;
                    }
                    tmp = tmp.next;
                }
                return null;
            }
            // 2. 如果两个链表都没环
            Node end1 = head1;
            Node end2 = head2;
            int len1 = 1;
            int len2 = 1;
            while(end1.next!=end){
                len1++;
                end1 = end1.next;
            }
            while(end2.next!=end){
                len2++;
                end2 = end2.next;
            }
            if(end1!=end2) return null; // 不相交
            // 相交
            Node sycHead1 = head1;
            Node sycHead2 = head2;
            if(len1>=len2){
                for(int i=0;i<len1-len2;i++){
                    sycHead1 = sycHead1.next;
                }
            }else{
                for(int i=0;i<len2-len1;i++){
                    sycHead2 = sycHead2.next;
                }
            }
            // 寻找相交点
            while(sycHead1!=end){
                if(sycHead1==sycHead2){
                    return sycHead1;
                }
                sycHead1 = sycHead1.next;
                sycHead2 = sycHead2.next;
            }
            return sycHead1;
        }

        public static Node getCyrcleNode(Node head){
            Node fast = head.next.next;
            Node slow = head.next;
            while(fast!=slow){
                if(fast==null || slow==null || fast.next == null){
                    return null;
                }
                slow = slow.next;
                fast = fast.next.next;
            }
            // 第一次相遇 设置快指针到开始点，步伐1
            fast = head;
            while(fast!=slow){
                fast = fast.next;
                slow = slow.next;
            }
            return fast;
        }
    }
}
