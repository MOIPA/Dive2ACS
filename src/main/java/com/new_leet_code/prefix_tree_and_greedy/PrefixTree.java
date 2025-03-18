package com.new_leet_code.prefix_tree_and_greedy;

import java.util.Arrays;

public class PrefixTree {
    public static void main(String[] args) {
        PrefixTree prefixTree = new PrefixTree();
        Solution s = prefixTree.new Solution();
        Trie trie = prefixTree.new Trie();
        trie.insert("abc");
        trie.insert("abc");
        trie.insert("abd");
        System.out.println(trie.search("abc"));
        System.out.println(trie.prefixNumber("ab"));

    }

    class Solution {

    }

    // 前缀树的节点定义 可以用于词频统计等功能
    class TrieNode {
        int pass = 0; // 每个节点都有被经过的次数
        int end = 0; // 节点作为字符结束节点次数，如abc，c的节点的end就要+1
        TrieNode[] nexts; // 前缀树可以有任意个子节点 如果是字符串前缀树，一般大小是26，表示有26个字母

        public TrieNode() {
            this.nexts = new TrieNode[26];
        }
    }

    // 定义前缀树的数据结构
    class Trie {
        private TrieNode root;

        public Trie() {
            this.root = new TrieNode();
        }

        // 提供操作
        // 1. 加入字符串操作
        public void insert(String word) {
            if (null == word)
                return;
            TrieNode node = this.root;
            node.pass++;
            char[] chs = word.toCharArray();
            for (int i = 0; i < chs.length; i++) {
                int indx = chs[i] - 'a'; // 字母a的编号是97
                if (node.nexts[indx] == null) {
                    node.nexts[indx] = new TrieNode();
                }
                node.nexts[indx].pass++;
                node = node.nexts[indx];
            }
            node.end++;
        }

        // 2. 查询单词加入过几次
        public int search(String word){
            if(word==null)return 0;
            TrieNode node = this.root;
            char[] chs = word.toCharArray();
            for (int i = 0; i < chs.length; i++) {
                int indx = chs[i] - 'a';
                if(node.nexts[indx]==null)return 0;
                node = node.nexts[indx];
            }
            return node.end;
        }

        // 3. 所有加入的字符串中，以这个prifeix开头的字符串有多少
        public int prefixNumber(String prefix){
            if(prefix==null) return 0;
            TrieNode node = this.root;
            char[] chs = prefix.toCharArray();
            for (int i = 0; i < chs.length; i++) {
                int index = chs[i]-'a';
                if(node.nexts[index]==null)return 0;
                node = node.nexts[index];
            }
            return node.pass;
        }

        // 4. 字符串的删除
        public void delete(String word){
            if(search(word)==0)return;
            if(word==null)return;
            char[] chs = word.toCharArray();
            TrieNode node = this.root;
            node.pass--;
            for (int i = 0; i < chs.length; i++) {
                int index = chs[i]-'a';
                node.pass--;
                if(node.pass==0) {node.nexts[index]=null;return;}
                node = node.nexts[index];
            }
            node.end--;
        }
    }
}
