package com.new_leet_code.force_recursive;

import java.util.*;

public class ForceRecursive {
    public static void main(String[] args) {
        ForceRecursive forceRecursive = new ForceRecursive();
        Solution s = forceRecursive.new Solution();
        // 1. 汉诺塔问题 3个柱子 n层汉诺塔：n个盘子从小到大放好，串在左边柱子，将n个盘子搬到最右边柱子的最优步骤是什么？
        s.hanoi(3);
        // 2. 打印一个字符串的所有子序列 所有子集合问题都是这个思路
        System.out.println("all sub:");
        s.printSubStr("abc");
        // 3. 字符串的全部排列
        System.out.println("全排列");
        s.arrange("abb"); // 不去重的全排列
        // 去重全排列，简单点可以用set，复杂的话，传递一个数组array[26]长度26，代表26个字母的使用情况，每次只能选择未使用的字母
        System.out.println("不重复全排列");
        s.arrangeWithOutRepeat("abb");
        // 5. A和B两个玩家，A先手，所有人每次只能取数组的最左或最右，谁得分高谁赢，返回得分最高的值，例子：【10，4，100，2】
        System.out.println(s.first(new int[] { 10, 4, 100, 2 }, 0, 3));
        // 6. 给一个栈，请你逆序这个栈，不能申请额外的数据结构，只能递归
        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.forEach(x -> System.out.print(x + "_"));
        System.out.println();
        s.reverseStack(stack);
        // System.out.println(s.findBottomNum(stack));
        stack.forEach(x -> System.out.print(x + "_"));
        System.out.println();
        // 7. 规定1和A对应、2和B对应、3和C对应… 那么一个数字字符串比如"111"，就可以转化为"AAA"、"KA"和"AK"。
        // 给定一个只有数字字符组成的字符串str，返回有多少种转化结果。
        System.out.println(s.convert("111") + " kinds");
        // 8. 背包，给你一个bag表示最大重量，给两个数组weight和value代表物品的重量和价值，得到最大价值
        System.out.println(s.maxValue(6, new int[] { 1, 2, 3, 4 }, new int[] { 4, 7, 5, 6 }) + " 价值");
    }

    class Solution {
        // 暴力递归

        // 1. 汉诺塔问题 3个柱子 n层汉诺塔：n个盘子从小到大放好，串在左边柱子，将n个盘子搬到最右边柱子的最优步骤是什么？
        public void hanoi(int n) {
            move(n, "左", "右", "中");
        }

        // from to other 三个柱子
        private void move(int i, String from, String to, String other) { // 移动从下往上第i个盘子的时候
            // base case是什么，i是1的时候，说明下面的盘子都移动走了，可以放到to 目的地了
            if (i == 1) {
                System.out.println("move 1 from " + from + " to " + to);
                return;
            }
            // 移动i上面的盘子到other去
            move(i - 1, from, other, to);
            // 然后把自己移动到to
            System.out.println("move " + i + " from " + from + " to " + to);
            // 再把上面的盘子移回来
            move(i - 1, other, to, from);
        }

        // 2. 打印一个字符串的所有子序列
        public void printSubStr(String str) {
            char[] chs = str.toCharArray();
            doPrintSubStr(chs, 0, new ArrayList<>());
        }

        private void doPrintSubStr(char[] chs, int i, List<Character> res) {
            if (i == chs.length) {
                res.forEach(x -> System.out.print(x));
                if (res.size() != 0)
                    System.out.println();
                return; // 没有字符了
            }
            List<Character> tmp = new ArrayList<>();
            res.forEach(x -> tmp.add(x));
            doPrintSubStr(chs, i + 1, tmp);

            tmp.add(chs[i]);
            doPrintSubStr(chs, i + 1, tmp);
        }

        // 3. 字符串的全部排列
        public void arrange(String str) {
            char[] chs = str.toCharArray();
            List<Character> list = new ArrayList<>();
            for (int i = 0; i < chs.length; i++) {
                list.add(chs[i]);
            }
            List<String> res = new ArrayList<>();
            doArrange(list, "", res);
            // 打印res
            for (String result : res) {
                System.out.println(result);
            }
        }

        private void doArrange(List<Character> list, String preStr, List<String> res) {
            // base case 如果list没有了就结束了 将当前的str组合加入到结果集合内
            if (list.isEmpty()) {
                res.add(preStr);
            }
            Set<Character> choosed = new HashSet<>(); // 去重修改
            // 遍历集合
            for (int i = 0; i < list.size(); i++) {
                Character choose = list.get(i);
                if (choosed.contains(choose))
                    continue;
                choosed.add(choose);
                List<Character> newList = new ArrayList<>(list);
                newList.remove(choose);
                doArrange(newList, preStr + choose, res);
            }
        }

        // 4. 字符串去重全排列
        public void arrangeWithOutRepeat(String str) {
            char[] chs = str.toCharArray();
            List<String> res = new ArrayList<>();
            doArraygeWithoutRepeat(chs, 0, res);
            res.forEach(x -> System.out.println(x));
        }

        private void doArraygeWithoutRepeat(char[] chs, int i, List<String> res) {
            if (i == chs.length)
                res.add(new String(chs));
            boolean[] visit = new boolean[26]; // 遍历字母是否已经被使用过
            for (int j = i; j < chs.length; j++) {
                if (!visit[chs[j] - 'a']) { // 从j开始的字母，如果有重复的就跳过
                    visit[chs[j] - 'a'] = true;
                    doSwap(chs, i, j); // j开始的数据选一个放在i上
                    doArraygeWithoutRepeat(chs, i + 1, res); // 剩下的从i+1开始排列
                    doSwap(chs, i, j); // 排完以后换回来
                                       // 不然无法确保chs[j]每次都是不一样的位置，即能全部遍历完数组，不然上一轮的chs的位置发生变动了，子递归结束开始j++正好是之前的某个值
                }
            }
        }

        private void doSwap(char[] chs, int i, int j) {
            char tmp = chs[i];
            chs[i] = chs[j];
            chs[j] = tmp;
        }

        // 5. A和B两个玩家，A先手，所有人每次只能取数组的最左或最右，谁得分高谁赢，返回得分最高的值，例子：【10，4，100，2】
        public int first(int[] arr, int l, int r) { // 在L到R的范围内取，先手
            if (l == r)
                return arr[l];
            // 要么取左，要么取右，取完以后后手取
            return Math.max(arr[l] + second(arr, l + 1, r), arr[r] + second(arr, l, r - 1));
        }

        private int second(int[] arr, int l, int r) { // 在L到R的范围上后手取
            if (l == r)
                return 0; // 只有一个的话被别人拿走了
            return Math.min(first(arr, l + 1, r), first(arr, l, r - 1)); // 后手，最大的被对方取走了
        }

        // 6. 给一个栈，请你逆序这个栈，不能申请额外的数据结构，只能递归
        public void reverseStack(Stack<Integer> stack) {
            if (stack.isEmpty()) {
                return;
            }
            Integer bottom = findBottomNum(stack);
            reverseStack(stack);
            stack.add(bottom);
        }

        public Integer findBottomNum(Stack<Integer> stack) { // 找到并且只弹出栈底数
            if (stack.size() == 1)
                return stack.pop();
            Integer num = stack.pop();
            Integer res = findBottomNum(stack);
            stack.push(num);
            return res;
        }

        // 7. 规定1和A对应、2和B对应、3和C对应… 那么一个数字字符串比如"111"，就可以转化为"AAA"、"KA"和"AK"。
        // 给定一个只有数字字符组成的字符串str，返回有多少种转化结果。
        public int convert(String str) {
            return calcPossibilities(str.toCharArray(), 0);
        }

        private int calcPossibilities(char[] chs, int i) {
            if (i == chs.length)
                return 1;
            // 给一个chs和i，获得对应的转换可能和新的i位置
            List<resultNode> transRes = translate(chs, i);
            int res = 0;
            for (resultNode node : transRes) {
                res += calcPossibilities(chs, node.i);
            }
            return res;
        }

        private List<resultNode> translate(char[] chs, int i) {
            List<resultNode> res = new ArrayList<>();
            String s;
            for (int j = i; j < chs.length; j++) {
                s = "";
                for (int k = i; k <= j; k++) {// i和j之间的所有字符组成串
                    s += chs[k];
                }
                // 串转为数字看是否存在对于的字符
                int c = Integer.parseInt(s);
                if (c > 'Z')
                    break; // 如果字符大于Z了循环结束
                res.add(new resultNode((char) ('A' + c - 1), j + 1));
            }
            return res;
        }

        // 8. 背包，给你一个bag表示最大重量，给两个数组weight和value代表物品的重量和价值
        public int maxValue(int bag, int[] weight, int[] value) {
            return doCountValue(bag, weight, value, 0);
        }

        private int doCountValue(int bag, int[] weight, int[] value, int i) { // i表示当前可放的物品
            int total = 0;
            for (int j = i; j < weight.length; j++) {
                if (bag >= weight[j]) {
                    doSwap(weight, i, j);
                    doSwap(value, i, j);
                    total = Math.max(value[i] + doCountValue(bag - weight[i], weight, value, i + 1),total);
                    doSwap(weight, i, j);
                    doSwap(value, i, j);
                }
            }
            return total;
        }

        private void doSwap(int[] arr, int i, int j) {
            int tmp = arr[i];
            arr[i] = arr[j];
            arr[j] = tmp;
        }
    }

    class resultNode {
        char c;
        int i;

        public resultNode(char c, int i) {
            this.c = c;
            this.i = i;
        }
    }
}
