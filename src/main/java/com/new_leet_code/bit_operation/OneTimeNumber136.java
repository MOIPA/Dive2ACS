package com.new_leet_code.bit_operation;

/**
 * 寻找只出现一次的数字
 */
public class OneTimeNumber136 {
    public static void main(String[] args) {
        int res = solution.singleNumber(new int[]{2,2,4});
        System.out.println(res);

    }
    private static class solution{
        public static int singleNumber(int[] nums) {
            int eor = 0;
            for (int i = 0; i < nums.length; i++) {
                eor ^= nums[i];
            }
            return eor;
        }
    }
}
