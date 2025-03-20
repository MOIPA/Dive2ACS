package com.new_leet_code.prefix_tree_and_greedy;
import java.util.*;

public class PrefixTree {
    public static void main(String[] args) {
        PrefixTree prefixTree = new PrefixTree();
        Solution s = prefixTree.new Solution();

        // 1-4. 测试前缀树功能
        Trie trie = prefixTree.new Trie();
        trie.insert("abc");
        trie.insert("abc");
        trie.insert("abd");
        System.out.println(trie.search("abc"));
        System.out.println(trie.prefixNumber("ab"));
        trie.delete("abd");
        System.out.println(trie.prefixNumber("ab"));

        // 贪心算法：技巧就是根据某个标准建立比较器，建立堆
        // 5. 会议安排问题
        System.out.println("greedy");
        Meeting[] meetings = new Meeting[]{prefixTree.new Meeting(6, 7),prefixTree.new Meeting(6, 8),prefixTree.new Meeting(7, 10),
            prefixTree.new Meeting(6, 9),prefixTree.new Meeting(9, 10)};
        List<Meeting> res = s.arrangeMeeting(meetings);
        res.forEach(x->System.out.println(x.startTime+" || "+x.endTime));

        //6. 字典序结合问题，现在给一些字符串数组["b","ba",....] 要求将这些字符串拼接成一个串，使得最后的结果字典序最小，比如 bba字典序明显大于 bab，所以结果是bab
        System.out.println(s.combine(new String[]{"b","ba"}));

        // 7. 分割金条问题，长度60的金条分割成两份需要花费60，现在有数组[10,20,30]代表金条总长度60，需要划分成这样的三份，怎么划分代价最低：如60划分成10，50，然后50分成20，30，总代价是110
        // 如果这样划分：60分成30,30 然后30划分成10,20 代价是90
        // 思路：1. 将数组的所有字符串排序，arrays.sort，然后顺序拼接   这种贪心策略是错误的！！！
        // 思路：2. 将排序数组的时候，自定义比较器，比如排序 b和ba时，比较bba和bab的字典序，谁小排前面
        System.out.println(s.splitGold2(new Integer[]{10,20,30}));

        // 查看两种解决方案的差异  实际结果表明还是思路2是对的
        System.out.println("diff: "+s.splitGoldDiff(10));


        // 8. 暴力递归 n皇后问题
        System.out.println(s.nQuenProblem(4));
    }

    class Solution {

        // 贪心算法

        // 5. 一个项目占用一个会议室，给你一个项目开始和结束时间，得到怎么样的安排使得会议室利用次数最多
        // 思路：哪一个会议结束时间早，先安排他就能得到最优解，执行：先找出结束时间最早的，删除和他冲突的，然后继续
        public List<Meeting> arrangeMeeting(Meeting[] meetings){
            PriorityQueue<Meeting> priorityQueue = new PriorityQueue<>((o1,o2)->o1.endTime-o2.endTime);
            for(Meeting meeting:meetings){
                priorityQueue.add(meeting);
            }
            int endTime =-1;
            List<Meeting> result = new ArrayList<>();
            while (!priorityQueue.isEmpty()) {
                Meeting meeting = priorityQueue.poll();
                // 判断是否和已有时间冲突
                if(meeting.startTime>=endTime) {
                    result.add(meeting);
                    endTime = meeting.endTime;
                }
            }
            return result;
        }
        //6. 字典序结合问题，现在给一些字符串数组["b","ba",....] 要求将这些字符串拼接成一个串，使得最后的结果字典序最小，比如 bba字典序明显大于 bab，所以结果是bab
        // 思路：1. 将数组的所有字符串排序，arrays.sort，然后顺序拼接   这种贪心策略是错误的！！！
        // 思路：2. 将排序数组的时候，自定义比较器，比如排序 b和ba时，比较bba和bab的字典序，谁小排前面
        public String combine(String[] strs){
            Arrays.sort(strs,(o1,o2)->(o1+o2).compareTo(o2+o1));
            StringBuilder builder = new StringBuilder();
            for(String s:strs){
                builder.append(s);
            }
            return builder.toString();
        }
        // 7. 分割金条问题，长度60的金条分割成两份需要花费60，现在有数组[10,20,30]代表金条总长度60，需要划分成这样的三份，怎么划分代价最低：如60划分成10，50，然后50分成20，30，总代价是110
        // 如果这样划分：60分成30,30 然后30划分成10,20 代价是90
        // 思路：从最大的开始划分 不一定对！  另一个思路：所有数据入堆，每次选择最小的两个数相加入堆 （本质就是哈夫曼树）
        // 两种思路不知道谁对，写一个对数器来比较
        public int splitGold(Integer[] arr){
            int res = 0;
            int total = 0;
            for(int n:arr){
                total += n;
            }
            Arrays.sort(arr,(o1,o2)->o2-o1);
            for(int i=0;i<arr.length-1;i++){
                total -= arr[i];
                res +=total+arr[i];
            }
            return res;
        }
        public int splitGold2(Integer[] arr){
            PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
            for(Integer n:arr){
                priorityQueue.add(n);
            }
            int res = 0;
            while(!priorityQueue.isEmpty()){
                int sum = 0;
                sum += priorityQueue.poll();
                if(priorityQueue.isEmpty())break;
                sum += priorityQueue.poll();
                res += sum;
                priorityQueue.add(sum);
            }
            return res;
        }

        // 随机生成长度1-101的数组
        public Integer[] generatoIntegers(){
            int n = (int)(Math.random()*100+1);
            Integer[] arr = new Integer[n];
            for(int i=0;i<arr.length;i++){
                arr[i] = (int)(Math.random()*100+1);
            }
            return arr;
        }

        // 两种算法计算结果差异次数
        public int splitGoldDiff(int times){
            int diff=0;
            for(int i=0;i<times;i++){
                Integer[] arr = generatoIntegers();
                if(splitGold(arr)!=splitGold2(arr))diff++;
            }
            return diff;
        }

        // 8. 暴力递归 n皇后问题  输入n表示n个皇后，每个皇后不共行不共列，不共斜线
        public int nQuenProblem(int n){
            int[] record = new int[n]; // record[0] 表示第0行的皇后的列
            return doNQuen(record, 0,n);
        }
        // 当前第i行位置的皇后能放多少种
        private int doNQuen(int[] record,int i,int n){
            if(i==n)return 1; // 所有皇后放满了，存在一种摆放方式
            int res = 0;
            // record[i] 怎么放 遍历所有列
            for(int j=0;j<n;j++){
                if(isValid(record,i,j)){ // 如果放在i，j位置合法 
                    record[i] = j;
                    res += doNQuen(record, i+1, n); // 看下一个皇后有多少种放法
                }
            }
            return res;
        }
        private boolean isValid(int[] record,int i,int j){
            // 放在 i，j位置的皇后是否和前面的共列或者共斜线，一行放一个皇后所以不共行
            for(int k=0;k<i;k++){  // 前一个皇后的坐标是 (k,record[k]) 当前的坐标是(i,j) 判断是否共斜线看斜率绝对值是否是1
                if(j==record[k]||(Math.abs(k-i) == Math.abs(record[k]-j)))return false;
            }
            return true;
        }

    }

    // 会议
    class Meeting{
        public int startTime;
        public int endTime;
        public Meeting(int start,int end){
            this.startTime = start;
            this.endTime = end; 
        }
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
