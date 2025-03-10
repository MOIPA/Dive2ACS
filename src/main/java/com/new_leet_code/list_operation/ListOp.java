package com.new_leet_code.list_operation;

import java.util.*;

public class ListOp {
    public static Node createSample() {
        ListOp op = new ListOp();
        Node head = op.new Node();
        Node p = head;
        p.next = op.new Node(1);
        p = p.next;
        p.next = op.new Node(2);
        p = p.next;
        p.next = op.new Node(3);
        p = p.next;
        p.next = op.new Node(3);
        p = p.next;
        p.next = op.new Node(2);
        p = p.next;
        p.next = op.new Node(1);
        p = p.next;
        return head;
    }

    public static Node[] createSample2() {
        Node[] heads = new Node[2];
        ListOp op = new ListOp();
        Node head = op.new Node();
        Node p = head;
        p.next = op.new Node(1);
        p = p.next;
        p.next = op.new Node(2);
        p = p.next;
        p.next = op.new Node(3);
        p = p.next;
        p.next = op.new Node(4);
        p = p.next;
        p.next = op.new Node(5);
        p = p.next;
        p.next = op.new Node(6);
        p = p.next;
        Node head2 = op.new Node();
        Node p2 = head2;
        p2.next = op.new Node(1);
        p2 = p2.next;
        p2.next = op.new Node(2);
        p2 = p2.next;
        p2.next = head.next.next.next; // 节点相交
        p.next = head.next.next.next.next; // 节点环，6-4
        heads[0] = head;
        heads[1] = head2;
        return heads;
    }

    public static void main(String[] args) {
        ListOp op = new ListOp();
        Solution s = op.new Solution();

        // 1. 单链表反转
        Node head = createSample();
        printList(head);
        head = s.reverseSingleList(head);
        printList(head);

        // 2. 双向链表反转 一样思路

        // 3. 打印两个链表的公共部分 归并思想，两个指针分别指向两个链表，谁小谁移动

        // 4. 判断链表是否回文 栈，或者快慢指针把后半部分进栈
        System.out.println(s.isPalindrome(head));
        // 5. 判断链表是否回文的高级方法：将后半段链表逆序
        System.out.println(s.isPalindromePrimer(head));
        // 6. 将单链表按某个值，划分为：左边小，中间相等，右边大形式 简单：用数组，Node[] array;排序完了再串起来
        head = createSample();
        s.sortNode(head);
        printList(head);
        // 7. 将单链表按某个值，划分为：左边小，中间相等，右边大形式 高级方法：分成三个list，小于部分的list，等于，大于的，然后串起来
        head = createSample();
        s.sortNode(head, 2);
        printList(head);

        // 8. 节点Node除了有next还有一个rand，随机指向其他节点，形成一个无环的图。要求拷贝这个图，随机关系也拷贝
        // 简单方法：第一次遍历，使用 HashMap<原来的Node，新创建的Node>，第二次遍历HashMap拷贝关系
        // 高级方法：生成的所有节点，挂在原来节点的下一个，1-2-3，变成
        // 1-1’-2-2’-3-3‘，第二次遍历若存在1-3的随机指向，可以改成1的下一个随机指向3的下一个，最后分离新节点

        // 9. 两个单链表相交的一系列问题
        // 9.1 判断是否有环路
        Node[] heads = createSample2();
        Node head1 = heads[0];
        Node head2 = heads[1];
        System.out.println(s.isCircle(head1).val);
        // 9.2 判断是否有环路：简单版本-用hashset判断下一个节点是否已经在set中
        System.out.println(s.isCircle2(head1).val);
        // 9.3 判断两个链表是否相交
        System.out.println(s.isCross(head1, head2).val);
    }

    public static void printList(Node root) {
        root = root.next;
        while (root != null) {
            System.out.print(root.val + " | ");
            root = root.next;
        }
        System.out.println();
    }

    public class Node {
        Node pre = null;
        Node next = null;
        Integer val = null;

        public Node() {
        }

        public Node(int val) {
            this.val = val;
        }

        public Node(int val, Node next) {
            this.next = next;
        }

        public Node(int val, Node next, Node pre) {
            this.next = next;
            this.pre = pre;
        }
    }

    public class Solution {

        // 1. 单链表反转
        public Node reverseSingleList(Node head) {
            Node newHead = new Node();
            // 头插法加入新链表
            Node p = head.next;
            while (p != null) {
                Node next = p.next;
                p.next = newHead.next;
                newHead.next = p;
                p = next;
            }
            return newHead;
        }

        // 5. 判断栈是否回文
        public boolean isPalindrome(Node head) {
            // 带head的快慢指针，慢指针指向中间靠左的节点
            Node fast = head;
            Node slow = head;
            while (fast != null && fast.next != null) {
                fast = fast.next.next;
                slow = slow.next;
            }
            // 中间节点的下一个节点开始入栈
            Stack<Node> s = new Stack<>();
            while (slow.next != null) {
                s.add(slow.next);
                slow = slow.next;
            }
            // 对比盏内所有数据
            Node p = head.next;
            while (!s.isEmpty()) {
                if (p.val != s.pop().val)
                    return false;
                p = p.next;
            }
            return true;
        }

        // 5. 判断链表是否回文的高级方法：将后半段链表逆序
        public boolean isPalindromePrimer(Node head) {
            // 快慢指针
            Node fast = head;
            Node slow = head;
            while (fast != null && fast.next != null) {
                slow = slow.next;
                // slow先动，fast动之前判断是否走到结尾
                if (fast.next.next == null) {
                    // 到结尾了 fast指向尾节点
                    fast = fast.next;
                    break;
                }
                fast = fast.next.next;
            }
            Node mid = slow;
            // 逆序后半段链表
            reverseHalfList(mid);
            // 判断回文
            head = head.next;
            while (head != null) {
                if (head.val != fast.val)
                    return false;
                head = head.next;
                fast = fast.next;
            }
            return true;
        }

        // 将后半段链表逆序
        public void reverseHalfList(Node mid) {
            if (mid == null || mid.next == null)
                return;
            // 逆转后半段链表 mid是中间节点，偶数时靠左
            Node p1 = mid.next;
            mid.next = null;
            Node p2 = p1.next;
            while (p1 != null) {
                p1.next = mid;
                mid = p1;
                p1 = p2;
                if (p2 != null)
                    p2 = p2.next;
            }
        }

        // 6. 排序链表简单方法
        public void sortNode(Node head) {
            int size = 0;
            Node p = head.next;
            while (p != null) {
                size++;
                p = p.next;
            }
            Node[] arrayNodes = new Node[size];
            p = head.next;
            for (int i = 0; i < size; i++) {
                arrayNodes[i] = p;
                p = p.next;
            }
            // 排序
            Arrays.sort(arrayNodes, (o1, o2) -> o1.val - o2.val);
            p = head;
            for (int i = 0; i < size; i++) {
                p.next = arrayNodes[i];
                p = arrayNodes[i];
            }
            p.next = null;
        }

        // 7. 链表版荷兰国旗，高级方法
        public void sortNode(Node head, int num) {
            // 三个链表，存放小于，大于，等于 需要小于的头尾，等于的头尾，大于的头
            Node equalNodeH = null;
            Node equalNodeT = null;
            Node biggerNodeH = null;
            Node smallerNodeH = null;
            Node smallerNodeT = null;
            Node p = head.next;
            while (p != null) {
                Node next = p.next;
                p.next = null;
                if (p.val > num) {
                    // 头插法
                    if (biggerNodeH == null) {
                        biggerNodeH = p;
                    } else {
                        p.next = biggerNodeH.next;
                        biggerNodeH.next = p;
                    }
                } else if (p.val == num) {
                    // 尾插法就行，要记录尾节点
                    if (equalNodeH == null) {
                        equalNodeH = p;
                        equalNodeT = p;
                    } else {
                        equalNodeT.next = p;
                        equalNodeT = p;
                    }
                } else if (p.val < num) {
                    if (smallerNodeH == null) {
                        smallerNodeH = p;
                        smallerNodeT = p;
                    } else {
                        smallerNodeT.next = p;
                        smallerNodeT = p;
                    }
                }
                p = next;
            }
            // 三个链表串起来 串起来要考虑到边界问题，万一全都小于num，或者大于，或者等于
            if (smallerNodeH == null && equalNodeH == null) {
                head.next = biggerNodeH.next;
                return;
            } else if (biggerNodeH == null && equalNodeH == null) {
                head.next = smallerNodeH.next;
                return;
            } else if (biggerNodeH == null && smallerNodeH == null) {
                head.next = equalNodeH.next;
                return;
            } else if (smallerNodeH == null) {
                head.next = equalNodeH;
                equalNodeT.next = biggerNodeH; // 等于和大于链接
            } else if (equalNodeH == null) {
                head.next = smallerNodeH;
                smallerNodeT.next = biggerNodeH; // 小于和大于链接
            } else if (biggerNodeH == null) {
                head.next = smallerNodeH;
                smallerNodeT.next = equalNodeH; // 小于和等于链接
            } else {
                head.next = smallerNodeH;
                smallerNodeT.next = equalNodeH; // 小于和等于链接
                equalNodeT.next = biggerNodeH; // 等于和大于链接
            }
        }

        // 9.1 判断环路
        public Node isCircle(Node head) {
            // 快慢指针
            Node slow = head.next;
            Node fast = head.next.next;
            while (slow != fast && fast != null && fast.next != null) {
                slow = slow.next;
                fast = fast.next.next;
            }
            if (fast == null || fast.next == null)
                return null; // 无环
            // 有环
            slow = head;
            while (slow != fast) {
                slow = slow.next;
                fast = fast.next;
            }
            return slow;
        }

        // 9.2 判断是否有环路：简单版本-用hashset判断下一个节点是否已经在set中
        public Node isCircle2(Node head) {
            Set<Node> set = new HashSet<>();
            Node p = head.next;
            while (!set.contains(p)) {
                set.add(p);
                p = p.next;
                if (p == null)
                    return null;
            }
            return p;
        }

        // 9.3 判断两个链表是否相交
        public Node isCross(Node head1, Node head2) {
            // 环路判断
            Node circle1 = isCircle(head1);
            Node circle2 = isCircle(head2);
            // 1. 一个有环，一个无环肯定不相交
            if (circle1 == null && circle2 != null)
                return null;
            if (circle2 == null && circle1 != null)
                return null;
            // 2. 两个都无环，找到最后一个节点
            if (circle1 == null && circle2 == null) {
               return noLoopFind(head1, head2);
            }
            // 3.1 两个都有环，但是入环节点相同，可以复用双无环代码，将环路截断
            if(circle1==circle2){
                circle1.next = null;
                return noLoopFind(head1, head2);
            }
            // 3.2 两个都有环，但是入环节点不同，判断loop1能否遇到loop2，能说明两个链表在一个环路，只是入环点不一样，返回随机入环点
            Node p = circle1.next;
            while(p!=circle1){
                if(p==circle2)return circle1;
                p = p.next;
            }
            // 有环但是不在一个环
            return null;
        }

        public Node noLoopFind(Node head1, Node head2) {
            int len1 = 0, len2 = 0;
            Node p1 = head1;
            Node p2 = head2;
            while (p1.next != null) {
                p1 = p1.next;
                len1++;
            }
            while (p2.next != null) {
                p2 = p2.next;
                len2++;
            }
            if (p1 != p2)
                return null; // 不相交
            // 相交，根据两个链表长度找到相交点
            for (int i = 0; i < len1 - len2; i++)
                head1 = head1.next;
            for (int i = 0; i < len2 - len1; i++)
                head2 = head2.next;
            while (head1 != head2) {
                head1 = head1.next;
                head2 = head2.next;
            }
            return head1;
        }
    }
}
