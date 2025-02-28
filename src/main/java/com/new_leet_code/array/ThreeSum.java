package com.new_leet_code.array;

import java.sql.Array;
import java.util.*;
public class ThreeSum {
    public static void main(String[] args) {
        ThreeSum solution = new ThreeSum();
        Solution sol=solution.new Solution();
        int[] nums = {-1, 0, 1, 2, -1, -4};
        List<List<Integer>> res = sol.threeSum(nums);
        System.out.println(res.toString());
    }
    class Solution {
        public List<List<Integer>> threeSum(int[] nums) {
            Arrays.sort(nums);
            List<List<Integer>> ret = new ArrayList<>();
            Set<List<Integer>> set = new HashSet<>();
            // 暴力
            for (int i = 0; i < nums.length; i++) {
                for (int j = i+1; j < nums.length; j++) {
                    for (int k = j+1; k < nums.length; k++) {
                        // 第三层的时候，剩下了的数去重
                        if(nums[i]+nums[j]+nums[k]==0){
                            set.add(Arrays.asList(nums[i],nums[j],nums[k]));
                        }
                    }
                }
            }
            ret = new ArrayList<>(set);
            return ret;
        }
    }
}
