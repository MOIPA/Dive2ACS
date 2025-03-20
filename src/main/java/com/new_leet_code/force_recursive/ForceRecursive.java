package com.new_leet_code.force_recursive;
import java.util.*;

public class ForceRecursive {
    public static void main(String[] args) {
        ForceRecursive forceRecursive = new ForceRecursive();
        Solution s = forceRecursive.new Solution();
        // 1. 汉诺塔问题 3个柱子 n层汉诺塔：n个盘子从小到大放好，串在左边柱子，将n个盘子搬到最右边柱子的最优步骤是什么？
        s.hanoi(3);
        // 2. 打印一个字符串的所有子序列   所有子集合问题都是这个思路
        System.out.println("all sub:");
        s.printSubStr("abc");
        // 3. 字符串的全部排列
        System.out.println("全排列");
        s.arrange("abb"); // 不去重的全排列
    }
    class Solution{
        // 暴力递归

        // 1. 汉诺塔问题 3个柱子 n层汉诺塔：n个盘子从小到大放好，串在左边柱子，将n个盘子搬到最右边柱子的最优步骤是什么？
        public void hanoi(int n){
            move(n, "左", "右", "中");
        }
        // from to other 三个柱子
        private void move(int i,String from,String to,String other){ // 移动从下往上第i个盘子的时候 
            // base case是什么，i是1的时候，说明下面的盘子都移动走了，可以放到to 目的地了
            if(i==1){
                System.out.println("move 1 from "+from+" to "+to);
                return;
            }
            // 移动i上面的盘子到other去
            move(i-1,from,other,to);
            // 然后把自己移动到to
            System.out.println("move "+i+" from "+from+" to "+to);
            // 再把上面的盘子移回来
            move(i-1,other,to,from);
        }
    
        // 2. 打印一个字符串的所有子序列
        public void printSubStr(String str){
            char[] chs = str.toCharArray();
            doPrintSubStr(chs, 0,new ArrayList<>());
        }
        private void doPrintSubStr(char[] chs,int i,List<Character> res){
            if(i==chs.length){
                res.forEach(x->System.out.print(x));
                if(res.size()!=0)System.out.println();
                return; // 没有字符了
            }
            List<Character> tmp = new ArrayList<>();
            res.forEach(x->tmp.add(x));
            doPrintSubStr(chs, i+1, tmp);

            tmp.add(chs[i]);
            doPrintSubStr(chs, i+1, tmp);
        }
    
        // 3. 字符串的全部排列
        public void arrange(String str){
            char[] chs = str.toCharArray();
            List<Character> list = new ArrayList<>();
            for(int i=0;i<chs.length;i++){
                list.add(chs[i]);
            }
            List<String> res = new ArrayList<>();
            doArrange(list, "", res);
            // 打印res
            for (String result : res) {
                System.out.println(result);
            }
        }
        private void doArrange(List<Character> list,String preStr,List<String> res){
            // base case  如果list没有了就结束了 将当前的str组合加入到结果集合内
            if(list.isEmpty()){
                res.add(preStr);
            }
            // 遍历集合
            for(int i=0;i<list.size();i++){
                Character choose = list.get(i);
                List<Character> newList = new ArrayList<>(list);
                newList.remove(choose);
                doArrange(newList, preStr+choose, res);
            }
        }
    }
}
