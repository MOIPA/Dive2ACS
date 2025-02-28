package com.leetcode;

import java.util.*;

public class InsertIntervals57 {
    public static void main(String[] args) {
        int[][] insert = new Solution().insert2(new int[][]{{1, 3}, {6, 9}}, new int[]{2, 5});
        Arrays.stream(insert).forEach(x -> System.out.println(Arrays.toString(x)));
    }

    /**
     * Given a set of non-overlapping intervals, insert a new interval into the intervals (merge if necessary).
     * <p>
     * You may assume that the intervals were initially sorted according to their start times.
     * <p>
     * 思路：由于是排好序的，遍历新间断落入的情况，完全落入中间，未落入，左边落入中间-右边为落入，左边落入-右边落入
     */
    private static class Solution {
        public int[][] insert(int[][] intervals, int[] newInterval) {
            List<int[]> res = new LinkedList<>();
            if (intervals.length == 0) {
                res.add(newInterval);
                return res.toArray(new int[0][]);
            }
            // posi 为比新左端点大的地方
            int posi = 0;
            while (newInterval[0] >= intervals[posi][0]) posi++;
            // posi -2 和之前的先存入
            for (int i = 0; i <= posi - 2; i++) {
                res.add(intervals[i]);
            }
            // 左端点在左区间右端点之后且右端点在右区间左端点之前
            if (newInterval[0] > intervals[posi - 1][1] && newInterval[1] < intervals[posi][0]) {
                res.add(newInterval);
            }
            // 左右断点落入左区间
            else if (newInterval[1] <= intervals[posi - 1][1]) {
                return intervals;
            }
            // 右端点>=右区间左端点情况 左端点不明

            // 将posi和后面的装入
            for (int i = posi; i < intervals.length; i++) {
                res.add(intervals[i]);
            }
            return res.toArray(new int[0][]);
        }

        /**
         * 想到了个算法 如果用将集合表现在一条线上，那么只要start次数和end次数匹配就是一个区间 题目的都是闭区间如果start和end相等也要合并即
         * start = end 先算start次数+1
         *
         * @param intervals
         * @param newInterval
         * @return
         */
        public int[][] insert2(int[][] intervals, int[] newInterval) {
            List<int[]> res = new LinkedList<>();
            if (intervals.length == 0) {
                res.add(newInterval);
                return res.toArray(new int[0][]);
            }
            // 找到开始合并的数组
            int newS = newInterval[0];
            int newE = newInterval[1];
            int posi = 0;
            while (posi < intervals.length && newS >= intervals[posi][0]) posi++;
            posi--;
            for (int i = 0; i < posi; i++) res.add(intervals[i]);
            if (posi >= intervals.length) res.add(newInterval);
            else {
                if (posi >= 0) {
                    if (intervals[posi][1] < newS) {
                        posi++;
                        res.add(intervals[posi - 1]);
                    } else {
                        newS = Math.min(newS, intervals[posi][0]);
                    }
                } else posi++;
                // 确定右端点
                while (posi < intervals.length && intervals[posi][1] < newE) posi++;
                if (posi < intervals.length && intervals[posi][0] <= newE) newE = intervals[posi++][1];

                res.add(new int[]{newS, newE});
                for (int i = posi; i < intervals.length; i++) {
                    res.add(intervals[i]);
                }
            }
            return res.toArray(new int[0][]);
        }


        public int[][] insert3(int[][] intervals, int[] newInterval) {
            List<int[]> res = new LinkedList<>();
            Stack<Integer> stack = new Stack<>();
            if (intervals.length == 0) {
                res.add(newInterval);
                return res.toArray(new int[0][]);
            }
            Integer newS = newInterval[0];
            Integer newE = newInterval[1];
            for (int[] interval : intervals) {
                // 如果都在左边
                if (newS != null && newE != null && newE < interval[0]) {
                    res.add(newInterval);
                    newS = null;
                    newE = null;
                }
                // 如果都在右边
                if (newS != null && newE != null && newS > interval[1]) {
                    res.add(interval);
                    res.add(newInterval);
                    newS = null;
                    newE = null;
                }
                if (newS != null && interval[0] > newS) {
                    stack.push(newS.intValue());
                    newS = null;
                } else {
                    stack.push(interval[0]);
                    if (newS != null) {
                        stack.push(newS.intValue());
                        newS = null;
                    }
                }
            }
            return res.toArray(new int[0][]);
        }
    }
}
