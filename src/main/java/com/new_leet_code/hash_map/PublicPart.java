package com.new_leet_code.hash_map;

import java.util.*;
public class PublicPart {
    public static void main(String[] args){
        int[] nums1 ={1,2,3,4,5};
        int[] nums2 ={1,2,3,4,5};
        PublicPart publicPart = new PublicPart();
        Solution solution = publicPart.new Solution();
        int[] res = solution.solve(nums1, nums2);
        System.out.println(Arrays.toString(res));

    }
    class Solution{

        public int[] solve(int[] nums1,int[] nums2){
            int[] res = new int[]{0,0};
            HashSet<Integer> set1 = new HashSet<>();
            HashSet<Integer> set2 = new HashSet<>();
            for(int i=0;i<nums1.length;i++){
                set1.add(nums1[i]);
            }
            for (int i = 0; i < nums2.length; i++) {
                set2.add(nums2[i]);
            }
            // a在set2出现的次数
            for (int i = 0; i < nums1.length; i++) {
                if(set2.contains(nums1[i]))res[0]++;
            }
            for (int i = 0; i < nums2.length; i++) {
                if(set1.contains(nums2[i]))res[1]++;
            }
            return res;
        }

    }
}
