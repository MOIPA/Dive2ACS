package com.new_leet_code.bit_operation;

/**
 * 寻找只出现一次的数字
 */
public class OneTimeNumber137 {
    public static void main(String[] args) {
        int res = solution.singleNumber(new int[]{0,1,0,1,0,1,99});
        System.out.println(res);
    }
    private static class solution{
        public static int singleNumber(int[] nums) {
            int res = 0;
           for (int i = 0; i < 32; i++) {
               int tmp =0;
               for(int number:nums){
                   tmp += (number>>i)&1;
               }
               if(tmp %3 !=0){
                   res |= 1<<i;
               }
           }
           return res;
        }
    }
}
