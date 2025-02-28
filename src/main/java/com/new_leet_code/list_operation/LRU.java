package com.new_leet_code.list_operation;

import java.util.*;
public class LRU {

    public static void main(String[] args) {
        LRU lru = new LRU();
        LRUCache lRUCache = lru.new LRUCache(2);
        lRUCache.put(2, 1); // 缓存是 {1=1}
        lRUCache.put(1, 1);
        lRUCache.put(2, 3);
        lRUCache.put(4,1);    // 返回 4
        System.out.println(lRUCache.get(1));
        System.out.println(lRUCache.get(2));
    }

    class Node{
        int key;
        int value;
        public Node(int key,int value){
            this.value = value;
            this.key = key;
        }
        // 前后指针
        Node pre;
        Node next;
    }

    class LRUCache {
        private int capacity = 0;
        private int currentSize = 0;
        // 初始化头尾节点
        private Node tail = new Node(-1,-1);
        private Node head = new Node(-1,-1);
        // 初始化一个hashmap用于快速定位节点位置
        private Map<Integer,Node> map = new HashMap<>();

        public LRUCache(int capacity) {
            // 维护cache大小，超过cache要删除尾节点上一个点
            this.capacity = capacity;
            // 头尾节点初始化
            this.head.next = tail;
            this.tail.pre = head;
        }

        public int get(int key) {
            // 快速从hashmap定位到node，并且将节点移到头节点下一个位置
            Node node = map.get(key);
            // 不存在该点
            if(node == null)return -1;
            // 存在点，移动到头节点位置
            // 当前只有一个点不用动
            if(this.currentSize==1) return node.value;
            // 处理节点前后关系
            node.pre.next = node.next;
            node.next.pre = node.pre;
            // 头插法入头节点下一个
            addToHead(node);
            return node.value;
        }
        public void addToHead(Node node){
            this.head.next.pre = node;
            node.next = this.head.next;
            node.pre = this.head;
            this.head.next = node;
        }

        public void put(int key, int value) {
            // 先判断是否已经存在于内存，如果存在更新value
            if(this.map.containsKey(key)){
                Node t = this.map.get(key);
                // 只有一个数据的时候不能移动
                t.value = value;
                t.pre.next = t.next;
                t.next.pre = t.pre;
                addToHead(t);
                return;
            }
            // put操作要判断是否超出范围
            // 没有超出范围
            if(this.currentSize < this.capacity){
                this.currentSize++;
            }else{
                // 删除在map的node
                map.remove(tail.pre.key);
                // 超出或等于范围都需要将最后一个节点删除后移动到头
                // 删除尾节点
                this.tail.pre.pre.next = tail;
                this.tail.pre = this.tail.pre.pre;
            }
            // 生成新的节点，加入hashmap和链表
            Node node = new Node(key,value);
            this.map.put(key,node);
            addToHead(node);
        }

        private void printList(){
            Node crr = this.head.next;
            while(crr!=this.tail){
                System.out.print(crr.key+"***"+crr.value+" || ");
                crr = crr.next;
            }
            System.out.println();
        }
    }

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
}
