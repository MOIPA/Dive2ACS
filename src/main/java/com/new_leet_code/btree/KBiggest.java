package com.new_leet_code.btree;

import java.util.*;
public class KBiggest {
    public static void main(String[] args) {
        KBiggest kBiggest = new KBiggest();
        Solution solution = kBiggest.new Solution();
        System.out.println(solution.solve(new int[]{4,2,7,1,3,5,9},5));
    }
    class Solution{
        public Integer solve(int[] arr, int k){
            // 中序遍历
            List<Integer> list = new ArrayList<>();
            doPrint(arr,0,list);
            // list从小到大排列结果，第k个大的，要逆序
            return list.get(list.size()-k);
        }
        public void doPrint(int[] arr,int currentNode,List<Integer> res){
            if(currentNode > arr.length-1){return;}
            // 访问左节点
            doPrint(arr,currentNode*2+1,res);
            // 存储本节点
            res.add(arr[currentNode]);
            // 访问右节点
            doPrint(arr,currentNode*2+2,res);
        }
    }
}
