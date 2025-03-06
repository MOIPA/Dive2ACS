package com.new_leet_code.eor;

import java.util.*;

/**
* 异或问题
*/
public class Eor {
    public static void main(String[] args) {
	    Eor e = new Eor();
	    Solution s = e.new Solution();

        // 1. swap测试
        int[] test1 = new int[]{1,2};
	    s.swap(test1,0,1);
        System.out.println(test1[0]+" | "+test1[1]);

        // 2. 一组数据中只有一种数只出现了一次，其他都是偶数次
        System.out.println(s.findOne(new int[]{1,1,2,2,3,3,4}));
        // 3. 一组数据中，有两种数出现了奇数次，其他都是偶数次
        int[] res = s.findTwo(new int[]{1,1,2,2,3,3,4,5});
        System.out.println(res[0]+" | "+res[1]);
    }
    
    public class Solution{

        // 1. 异或的特殊swap
        public void swap(int[] array,int i,int j) {
            if(i==j)return;
		    array[i] =  array[i] ^ array[j];
		    array[j] =  array[i] ^ array[j];
		    array[i] =  array[i] ^ array[j];
    	}

        // 2. 一组数据中只有一种数只出现了一次，其他都是偶数次
        public int findOne(int[] array){
            int eor = 0;
            for(int num:array){
                eor ^=num;
            }
            return eor;
        }

        // 3. 一组数据中，有两种数出现了奇数次，其他都是偶数次
        public int[] findTwo(int[] array){
            int eor = 0;
            int[] res = new int[2];
            for(int num:array){
                eor ^=num;
            }
            int other = 0;
            // eor = a*b
            // 找到eor的最后末尾不为0的位，说明a和b在这位上一个是0，一个是1，最后将所有这个位上位1的数据异或，结果不是a就是b
            int lastBit = eor & (~eor+1); // 与自己的补码+1
            for(int num:array){
                if((lastBit&num) == lastBit){ // 所有位为1的数据
                    other ^= num;
                }
            }
            res[0]=other;
            res[1]=eor^other;
            return res;
        }
    
    }

}

