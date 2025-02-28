package com.new_leet_code;

import java.util.*;

public class Swap {
    public static void main(String[] args) {
        Swap.swap();
    }
    private static void swap() {
//        这么写的前提是两个内存区域不一致，如果在同一个数组，i和j相同，会给这个地址的值抹成0
        int left = 1;
        int right = 2;
        left = left ^ right;
        right = left ^ right;
        left = left ^ right;
        System.out.println(left);
        System.out.println(right);

        Integer i1 = 10;
        Integer i2 = 10;
        System.out.println(i1==i2);
        i1 = 12;
        System.out.println(i2);

    }
}
