package com.new_leet_code.array;

public class MaxSubArraySum {
    public static void main(String[] args) {
        MaxSubArraySum maxSubArraySum = new MaxSubArraySum();
        Solution solution = maxSubArraySum.new Solution();
        System.out.println(solution.maxSubArray(new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4}));
    }
    class Solution {
        public int maxSubArray(int[] nums) {
            int max = nums[0];
            int[][] res = new int[nums.length][];
            res[0] = nums;
            for (int i = 1; i < nums.length; i++) {
                res[i] = new int[nums.length-i];
                for (int j = 0; j < nums.length - i; j++) {
                    res[i][j] = res[i-1][j]+res[0][i+j];
                    max = Math.max(max,res[i][j]);
                }
            }
            for (int i = 0; i < nums.length; i++) {
                max = Math.max(max,nums[i]);
            }
            return max;
        }
    }
}
