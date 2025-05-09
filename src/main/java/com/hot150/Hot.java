package com.hot150;

import java.util.*;

/**
 * leetcode hot 150 面试题
 */
public class Hot {
    public static void main(String[] args) {
        Hot h = new Hot();
        Solution s = h.new Solution();

        // 1. 合并两个有序数组
        int[] nums1 = new int[] { 4, 0, 0, 0, 0 };
        s.merge(nums1, 1, new int[] { 1, 2, 5, 6 }, 4);
        System.out.println("code 1:");
        for (int num : nums1) {
            System.out.print(num + " ");
        }
        System.out.println();

        // 2. 移除元素
        System.out.println("code 2:");
        nums1 = new int[] { 0, 1, 2, 2, 3, 0, 4, 2 };
        System.out.println(s.removeElement(nums1, 2));
        for (int num : nums1) {
            System.out.print(num + " ");
        }
        System.out.println();

        // 3. 删除有序数组中的重复项
        System.out.println("code 3");
        nums1 = new int[] { 1, 1, 2 };
        System.out.println(s.removeDuplicates(nums1));
        for (int num : nums1) {
            System.out.print(num + " ");
        }
        System.out.println();
        // 4. 删除有序数组中的重复项 使得出现次数超过两次的元素只出现两次 ，返回删除后数组的新长度
        System.out.println("code 4");
        nums1 = new int[] { 1, 1, 2 };
        System.out.println(s.removeDuplicates2(nums1));
        for (int num : nums1) {
            System.out.print(num + " ");
        }
        System.out.println();
        // 5. 多数元素 给定一个大小为 n 的数组 nums ，返回其中的多数元素。多数元素是指在数组中出现次数 大于 ⌊ n/2 ⌋ 的元素。
        System.out.println("code 5");
        System.out.println(s.majorityElement(new int[] { 2, 2, 1, 1, 1, 2, 2, 4 }));

        // 6. 轮转数组 给定一个整数数组 nums，将数组中的元素向右轮转 k 个位置，其中 k 是非负数。
        System.out.println("code 6");
        nums1 = new int[] { 1, 2, 3, 4, 5, 6, 7 };
        s.rotate(nums1, 3);
        for (int num : nums1) {
            System.out.print(num + " ");
        }
        System.out.println();

        // 7. 买卖股票 给定一个数组 prices ，它的第 i 个元素 prices[i] 表示一支给定股票第 i 天的价格。
        // 你只能选择 某一天 买入这只股票，并选择在 未来的某一个不同的日子 卖出该股票。设计一个算法来计算你所能获取的最大利润。
        System.out.println("code 7");
        System.out.println(s.maxProfit(new int[] { 7, 1, 5, 3, 6, 4 }));
        // 8. 买卖股票2 给定一个数组 prices ，它的第 i 个元素 prices[i] 表示一支给定股票第 i 天的价格。 每天都可以买卖
        System.out.println("code 8");
        System.out.println(s.maxProfit2(new int[] { 7, 1, 5, 3, 6, 4 }));

        // 9. 跳跃游戏 给你一个非负整数数组 nums ，你最初位于数组的 第一个下标 。数组中的每个元素代表你在该位置可以跳跃的最大长度。
        // 判断你是否能够到达最后一个下标，如果可以，返回 true ；否则，返回 false 。
        System.out.println("code 9");
        System.out.println(s.canJump(new int[] { 2, 3, 1, 1, 4 }));

        // 10. 跳跃游戏2 这次要给出最小跳跃次数
        System.out.println("code 10");
        System.out.println(s.jump(new int[] { 2, 3, 1, 1, 4 }));

        // 11. H指数 h 代表“高引用次数” ，一名科研人员的 h 指数 是指他（她）至少发表了 h 篇论文，并且 至少 有 h 篇论文被引用次数大于等于 h
        // 。如果 h 有多种可能的值，h 指数 是其中最大的那个
        System.out.println("code 11");
        System.out.println(s.hIndex(new int[] { 1, 3, 1 }));

        // 12. 除自身以外数组的乘积 给你一个整数数组 nums，返回 数组 answer ，其中 answer[i] 等于 nums 中除 nums[i]
        // 之外其余各元素的乘积 。
        // 题目数据 保证 数组 nums之中任意元素的全部前缀元素和后缀的乘积都在 32 位 整数范围内。
        // 请 不要使用除法，且在 O(n) 时间复杂度内完成此题
        System.out.println("code 12");
        nums1 = s.productExceptSelf(new int[] { 1, 3, 1 });
        for (int num : nums1) {
            System.out.print(num + " ");
        }
        System.out.println();

        // 13. 加油站 在一条环路上有 n 个加油站，其中第 i 个加油站有汽油 gas[i] 升。
        // 你有一辆油箱容量无限的的汽车，从第 i 个加油站开往第 i+1 个加油站需要消耗汽油 cost[i] 升。你从其中的一个加油站出发，开始时油箱为空。
        // 给定两个整数数组 gas 和 cost ，如果你可以按顺序绕环路行驶一周，则返回出发时加油站的编号，否则返回 -1 。如果存在解，则 保证 它是 唯一
        // 的。
        System.out.println("code 13");
        System.out.println(s.canCompleteCircuit(new int[] { 5, 5, 1, 3, 4 }, new int[] { 8, 1, 7, 1, 1 }));

        // 14. 分发糖果n 个孩子站成一排。给你一个整数数组 ratings 表示每个孩子的评分。
        // 你需要按照以下要求，给这些孩子分发糖果：// 每个孩子至少分配到 1 个糖果。// 相邻两个孩子评分更高的孩子会获得更多的糖果。
        nums1 = new int[] { 1, 3, 2, 2, 1 };
        System.out.println("code 14");
        System.out.println(s.candy(nums1));

        // 15. 接雨水 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
        nums1 = new int[] { 4, 2, 0, 3, 2, 5 };
        System.out.println("code 15");
        System.out.println(s.trap(nums1));

        // 16. 整数转罗马数字
        System.out.println("code 16");
        System.out.println(s.intToRoman(3749));

        // 17. 最后一个单词的长度
        System.out.println("code 17");
        System.out.println(s.lengthOfLastWord("   fly me   to   the moon  "));

        // 18. 最长公共前缀
        System.out.println("code 18");
        System.out.println(s.longestCommonPrefix(new String[] { "flower", "flow", "flight" }));

        // 19. 反转字符串中的单词
        System.out.println("code 19");
        System.out.println(s.reverseWords("a good   example"));
        // 20. Z 字形变换
        System.out.println("code 20");
        System.out.println(s.convert("PAYPALISHIRING", 4));

        // 21. 找出字符串中第一个匹配项的下标 kmp算法
        System.out.println("code 21");
        System.out.println(s.strStr("aabaaabaaac", "aabaaac")); // 0,1,0,1,2,2(1),0

        // 22. 文本左右对齐
        System.out.println("code 22");
        System.out.println(
                s.fullJustify(new String[] { "Science","is","what","we","understand","well","enough","to","explain","to","a","computer.","Art","is","everything","else","we","do" }, 20));

    }

    class Solution {
        // 1. 合并两个有序数组
        // 输入：nums1 = [1,2,3,0,0,0], m = 3, nums2 = [2,5,6], n = 3
        // 输出：[1,2,2,3,5,6]
        // 解释：需要合并 [1,2,3] 和 [2,5,6] 。
        // 合并结果是 [1,2,2,3,5,6] ，其中斜体加粗标注的为 nums1 中的元素。
        public void merge(int[] nums1, int m, int[] nums2, int n) {
            int i = 0, j = 0;
            int[] nums3 = new int[m];
            for (int k = 0; k < m; k++)
                nums3[k] = nums1[k];
            int k = 0;
            while (i < m && j < n) {
                if (nums3[i] <= nums2[j])
                    nums1[k++] = nums3[i++];
                else
                    nums1[k++] = nums2[j++];
            }
            while (i < m) {
                nums1[k++] = nums3[i++];
            }
            while (j < n) {
                nums1[k++] = nums2[j++];
            }
        }

        // 2. 移除元素
        // 给你一个数组 nums 和一个值 val，你需要 原地 移除所有数值等于 val 的元素。元素的顺序可能发生改变。然后返回 nums 中与 val
        // 不同的元素的数量
        public int removeElement(int[] nums, int val) {
            int i = 0;
            int res = 0;
            int length = nums.length;
            while (i < length) {
                if (nums[i] == val) {
                    // 和最后一个交换
                    int tmp = nums[length - 1];
                    nums[length - 1] = nums[i];
                    nums[i] = tmp;
                    length--;
                } else {
                    res++;
                    i++;
                }
            }
            return res;
        }

        // 3. 删除有序数组中的重复项
        public int removeDuplicates(int[] nums) {
            if (nums.length == 1)
                return 1;
            // 存储指针
            int store = 0;
            // 遍历指针
            int see = 0;
            while (see + 1 < nums.length) {
                if (nums[see] == nums[see + 1]) {
                    while (see + 1 < nums.length && nums[see] == nums[see + 1])
                        see++;
                }
                nums[store++] = nums[see++];
            }
            // see 不一定走到最后
            if (see < nums.length) {
                nums[store++] = nums[see];
            }
            return store;
        }

        // 4. 删除有序数组中的重复项 使得出现次数超过两次的元素只出现两次 ，返回删除后数组的新长度
        public int removeDuplicates2(int[] nums) {
            if (nums.length <= 2)
                return nums.length;
            int store = 0;
            int see = 0;
            while (see < nums.length) {
                while (see < nums.length - 2 && nums[see] == nums[see + 1] && nums[see] == nums[see + 2])
                    see++;
                nums[store++] = nums[see++];
            }
            return store;
        }

        // 5. 多数元素 给定一个大小为 n 的数组 nums ，返回其中的多数元素。多数元素是指在数组中出现次数 大于 ⌊ n/2 ⌋ 的元素。
        public int majorityElement(int[] nums) {
            HashMap<Integer, Integer> map = new HashMap<>();
            for (int num : nums) {
                if (!map.containsKey(num)) {
                    map.put(num, 1);
                } else {
                    map.put(num, map.get(num) + 1);
                }
            }
            int maxKey = nums[0];
            int maxValue = 0;
            for (int num : map.keySet()) {
                if (map.get(num) > maxValue) {
                    maxKey = num;
                    maxValue = map.get(num);
                }
            }
            return maxKey;
        }

        // 6. 轮转数组 给定一个整数数组 nums，将数组中的元素向右轮转 k 个位置，其中 k 是非负数。
        public void rotate(int[] nums, int k) {
            // 第一种方法
            // if(k>nums.length){
            // k%=nums.length;
            // }
            // int[] cache = new int[nums.length];
            // int store = 0;
            // for(int i=nums.length-k;i<nums.length;i++){
            // cache[store++]=nums[i];
            // }
            // for(int i=0;i<nums.length-k;i++){
            // cache[store++]=nums[i];
            // }
            // for(int i=0;i<nums.length;i++){
            // nums[i]=cache[i];
            // }
            // 第二种方法
            if (k > nums.length) {
                k %= nums.length;
            }
            for (int i = nums.length - k - 1; i >= 0; i--) {
                // i 和 i+k交换
                int tmp = nums[i];
                nums[i] = nums[i + k];
                nums[i + k] = tmp;
            }
            // 前k个数 再移动 k-len%k 次
            // for(int i=0;i<k/2;i++){
            // int tmp = nums[i];
            // nums[i]=nums[k-i-1];
            // nums[k-i-1] = tmp;
            // }
        }

        // 7. 买卖股票 给定一个数组 prices ，它的第 i 个元素 prices[i] 表示一支给定股票第 i 天的价格。
        public int maxProfit(int[] prices) {
            if (prices.length < 2)
                return 0;
            int[] max = new int[prices.length];
            max[prices.length - 1] = prices[prices.length - 1];
            for (int i = prices.length - 2; i >= 0; i--) {
                if (prices[i] > max[i + 1]) {
                    max[i] = prices[i];
                } else {
                    max[i] = max[i + 1];
                }
            }
            for (int i = 0; i < max.length; i++) {
                max[i] = max[i] - prices[i];
            }
            int maxProfit = 0;
            for (int i = 0; i < max.length; i++) {
                if (max[i] > maxProfit)
                    maxProfit = max[i];
            }
            return maxProfit;
        }

        // 8. 买卖股票2 给定一个数组 prices ，它的第 i 个元素 prices[i] 表示一支给定股票第 i 天的价格。 每天都可以买卖
        public int maxProfit2(int[] prices) {
            int total = 0;
            int lastBuy = prices[0];
            for (int i = 1; i < prices.length; i++) {
                // 每天看看卖了多少钱，有利润就卖了然后买入今天的
                if (prices[i] - lastBuy > 0) {
                    total += prices[i] - lastBuy;
                }
                lastBuy = prices[i];
            }
            return total;
        }

        // 9. 跳跃游戏 给你一个非负整数数组 nums ，你最初位于数组的 第一个下标 。数组中的每个元素代表你在该位置可以跳跃的最大长度。
        // 判断你是否能够到达最后一个下标，如果可以，返回 true ；否则，返回 false 。
        public boolean canJump(int[] nums) {
            // 从右往左看每个点，哪个点能跳到这个点，找到能跳过来的最左边的点
            int endPoint = nums.length - 1;
            while (endPoint >= 0) {
                int needJump = 0; // 对于左边的点，越往左需要的跳数+1
                int jumpPoint = endPoint; // 需要得到能跳到endPoint的最左边的jumpPoint
                for (int i = jumpPoint; i >= 0; i--) {
                    if (nums[i] >= needJump) {
                        jumpPoint = i;
                    }
                    needJump++;
                }
                if (jumpPoint == 0)
                    return true;
                if (jumpPoint == endPoint)
                    return false; // 没有点可跳过来
                endPoint = jumpPoint; // 这个点能跳过来，那谁能跳到这个点
            }
            return true;
        }

        // 10. 跳跃游戏 这次要给出最小跳跃次数
        public int jump(int[] nums) {
            if (nums.length == 1) {
                return 1;
            }
            int jumpTimes = 0;
            int endPoint = nums.length - 1;
            while (endPoint >= 0) {
                int needJump = 0; // 对于左边的点，越往左需要的跳数+1
                int jumpPoint = endPoint; // 需要得到能跳到endPoint的最左边的jumpPoint
                for (int i = jumpPoint; i >= 0; i--) {
                    if (nums[i] >= needJump) {
                        jumpPoint = i;
                    }
                    needJump++;
                }
                jumpTimes++;
                if (jumpPoint == 0)
                    break;
                if (jumpPoint == endPoint)
                    return -1; // 没有点可跳过来
                endPoint = jumpPoint; // 这个点能跳过来，那谁能跳到这个点
            }
            return jumpTimes;
        }

        // 11. H指数 h 代表“高引用次数” ，一名科研人员的 h 指数 是指他（她）至少发表了 h 篇论文，并且 至少 有 h 篇论文被引用次数大于等于 h
        // 。如果 h 有多种可能的值，h 指数 是其中最大的那个
        public int hIndex(int[] citations) {
            // 先排序数组，获得另一个数组：大于当前引用次数的文章有多少
            Arrays.sort(citations);
            int maxH = 0;
            for (int i = 0; i < citations.length; i++) {
                // 因为排过序，大于i片文章引用量的结果为 length-i 包括i号文章本身
                int h = Math.min(Math.min(citations[i], citations.length - i), citations.length);
                maxH = Math.max(h, maxH);
            }
            return maxH;
        }

        // 12. 除自身以外数组的乘积 给你一个整数数组 nums，返回 数组 answer ，其中 answer[i] 等于 nums 中除 nums[i]
        // 之外其余各元素的乘积 。
        public int[] productExceptSelf(int[] nums) {
            // 找出所有数据的前缀，每个数的前缀=上一个数的前缀*上一个数
            int[] prefix = new int[nums.length];
            prefix[0] = 1;
            for (int i = 1; i < prefix.length; i++) {
                prefix[i] = nums[i - 1] * prefix[i - 1];
            }
            // 找出所有后缀，从最右边开始 每个数的后缀 = 右边的数*右边的数的后缀
            int[] aft = new int[nums.length];
            aft[nums.length - 1] = 1;
            for (int i = aft.length - 2; i >= 0; i--) {
                aft[i] = aft[i + 1] * nums[i + 1];
            }
            // 每个数的前缀*后缀 就是这个数除了自身的所有其他数乘积
            for (int i = 0; i < nums.length; i++) {
                nums[i] = prefix[i] * aft[i];
            }
            return nums;
        }

        // 13. 加油站 在一条环路上有 n 个加油站，其中第 i 个加油站有汽油 gas[i] 升。 暴力算法！！！！ 超时了！！！
        public int canCompleteCircuit(int[] gas, int[] cost) {
            // 贪心策略1，从最大的加油站开始，谁给的油最多从谁开始 不行！！！
            // int maxInd = 0;
            // int maxGas = 0;
            // for(int i=0;i<gas.length;i++){
            // if(gas[i]>maxGas){
            // maxGas = gas[i];
            // maxInd = i;
            // }
            // }
            // int i = maxInd; // 使用贪心改写

            // 贪心策略2 从消耗最小的加油站开始 不行
            // int ind = 0;
            // int minGas = cost[0];
            // for(int i=0;i<gas.length;i++){
            // if(cost[i]<minGas){
            // minGas = cost[i];
            // ind = i;
            // }
            // }
            // int i = ind; // 使用贪心改写

            // 贪心策略3 将所有开始位置，按照cost从小到大放入 还是 超时了！！！！ 官方题解是：从不能达到的点开始
            PriorityQueue<Node> startPoints = new PriorityQueue<>((o1, o2) -> o1.cost - o2.cost);
            for (int i = 0; i < cost.length; i++) {
                startPoints.add(new Node(cost[i], i));
            }

            // for (int i = 0; i < cost.length; i++) {
            // i指针代表起始位置 每一个起点都做尝试，如果成功返回i，否则返回-1
            // 用visited存储访问过的gas数量
            while (!startPoints.isEmpty()) {
                int i = startPoints.poll().ind;
                int visited = 1;
                int start = i; // 开始位置
                int currentGas = 0; // 初始油箱空的
                while (currentGas + gas[start] >= cost[start]) { // 付得起油费就往下走
                    currentGas = currentGas + gas[start] - cost[start]; // 走完更新油箱
                    start = (start + 1) % gas.length; // 到达下个地点
                    visited++;
                    // 判断是否走完了所有地点到达环路的第一个点
                    if (visited == gas.length + 1)
                        return i;
                }
            }
            // }
            return -1;
        }

        // 14. 分发糖果n 个孩子站成一排。给你一个整数数组 ratings 表示每个孩子的评分。
        public int candy(int[] ratings) {
            int[] candy = new int[ratings.length];
            Arrays.fill(candy, 1);
            doAllocate(ratings, candy, 0);
            int sum = 0;
            for (int num : candy)
                sum += num;
            return sum;
        }

        private void doAllocate(int[] ratings, int[] candy, int i) { // i 代表当前是哪个孩子
            if (i == ratings.length)
                return;
            if (i >= 1 && i <= ratings.length - 2) {
                if (ratings[i] <= ratings[i - 1] && ratings[i] <= ratings[i + 1]) {
                    candy[i] = 1;
                    candy[i + 1] = candy[i + 1] + 1;
                    doAllocate(ratings, candy, i + 1);
                    return;
                }
            }
            doAllocate(ratings, candy, i + 1);
            if (i <= ratings.length - 2 && ratings[i] >= ratings[i + 1])
                candy[i] = candy[i + 1] + 1;
            // else if(i<=ratings.length-2) candy[i] = candy[i+1]-1;
        }

        // 15. 接雨水 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
        public int trap(int[] height) {
            // 维护两个数组，分别保存这个点的左侧最大值和右侧最大值
            int[] leftMax = new int[height.length];
            int[] rightMax = new int[height.length];
            leftMax[0] = 0;
            rightMax[rightMax.length - 1] = 0;
            for (int i = 1; i < height.length; i++) {
                leftMax[i] = Math.max(leftMax[i - 1], height[i - 1]);
            }
            for (int i = rightMax.length - 2; i >= 0; i--) {
                rightMax[i] = Math.max(rightMax[i + 1], height[i + 1]);
            }
            int total = 0;
            for (int i = 0; i < height.length; i++) {
                int tmp = Math.min(leftMax[i], rightMax[i]) - height[i];
                if (tmp > 0)
                    total += tmp;
            }
            return total;
        }

        // 16. 整数转罗马数字
        public String intToRoman(int num) {
            String[] str = new String[] { "M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I" };
            int[] romanNums = new int[] { 1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1 };
            String res = "";
            while (num != 0) {
                for (int i = 0; i < str.length; i++) {
                    if (num >= romanNums[i]) {
                        num -= romanNums[i];
                        res += str[i];
                        break;
                    }
                }
            }
            return res;
        }

        // 17. 最后一个单词的长度
        public int lengthOfLastWord(String s) {
            int res = 0;
            for (int i = s.length() - 1; i >= 0; i--) {
                if (s.charAt(i) != ' ') {
                    while (i >= 0 && s.charAt(i) != ' ') {
                        res++;
                        i--;
                    }
                    return res;
                }
            }
            return res;
        }

        // 18. 最长公共前缀
        public String longestCommonPrefix(String[] strs) {
            // String prefix = "";
            // int ind = 0;
            // while (true) {
            // if(ind>=strs[0].length())return prefix;
            // char prefixChar = strs[0].charAt(ind);
            // for (String str : strs) {
            // if(ind>=str.length()||str.charAt(ind)!=prefixChar) return prefix;
            // }
            // prefix += prefixChar;
            // ind++;
            // }
            // 第二种方法：先排序，比较第一个和最后一个前缀
            Arrays.sort(strs);
            if (strs.length == 0 || strs[0].length() == 0)
                return "";
            char[] a = strs[0].toCharArray();
            char[] b = strs[strs.length - 1].toCharArray();
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < a.length && i < b.length; i++) {
                if (a[i] == b[i]) {
                    builder.append(a[i]);
                } else {
                    break;
                }
            }
            return builder.toString();
        }

        // 19. 反转字符串中的单词
        public String reverseWords(String s) {
            String[] strs = s.trim().split(" ");
            StringBuilder builder = new StringBuilder();
            for (int i = strs.length - 1; i >= 0; i--) {
                if (!strs[i].equals("")) {
                    builder.append(strs[i]);
                    if (i != 0)
                        builder.append(' ');
                }

            }
            return builder.toString();
        }

        // 20. Z 字形变换
        public String convert(String s, int numRows) {
            if (numRows == 1)
                return s;
            // 维护一个StringBUilder数组来分别收集对应行的字符
            StringBuilder[] builders = new StringBuilder[numRows];
            for (int i = 0; i < builders.length; i++) {
                builders[i] = new StringBuilder();
            }
            // numRows个数正序，numRows-2个数，逆序
            int i = 0; // 记录字符指针
            while (i < s.length()) {
                for (int j = 0; j < numRows && i < s.length(); j++) {
                    // rowNum[i++]=j;
                    builders[j].append(s.charAt(i++));
                }
                for (int j = numRows - 2; j >= 1 && i < s.length(); j--) {
                    // rowNum[i++]=j;
                    builders[j].append(s.charAt(i++));
                }
            }
            StringBuilder builder = new StringBuilder();
            for (StringBuilder b : builders) {
                builder.append(b);
            }
            return builder.toString();
        }

        // 21. 找出字符串中第一个匹配项的下标 kmp算法
        public int strStr(String haystack, String needle) {
            char[] haystackChs = haystack.toCharArray();
            char[] needleChs = needle.toCharArray();
            // 两个指针指向主串和子串
            int i = 0;
            int j = 0;
            int[] nexts = calcNexts(needle); // 计算next数组
            while (i < haystack.length() && j < needleChs.length) {
                if (haystackChs[i] == needleChs[j]) {
                    i++;
                    j++;
                } else {
                    if (j == 0)
                        i++;
                    // 一旦失配，主串指针不动，子串移动到失配的next位置继续比较
                    j = nexts[j];
                }
                // 判断是否匹配成功
                if (j == needleChs.length)
                    return i - j;
            }
            return -1;
        }

        private int[] calcNexts2(String str) {
            // next数组从1开始算
            char[] strChs = str.toCharArray();
            int[] next = new int[strChs.length + 1];
            Arrays.fill(next, 0);
            for (int i = 1; i < strChs.length; i++) {
                for (int j = 0; j < i; j++) {
                    // 前j个和后j个是否相同，相同
                    String pre = str.substring(0, j + 1);
                    String aft = str.substring(i - j, i + 1);
                    if (pre.equals(aft) && next[i + 1] < j + 1)
                        next[i + 1] = j + 1;
                }
            }
            return next;
        }

        // DP方式计算next数组
        private int[] calcNexts(String str) {
            // next数组从1开始算
            char[] strChs = str.toCharArray();
            int[] next = new int[strChs.length + 1];
            Arrays.fill(next, 0);
            for (int i = 1; i < strChs.length; i++) {
                int j = i;
                while (j > 0) {
                    if (strChs[i] == strChs[next[j]]) {
                        next[i + 1] = next[j] + 1;
                        break;
                    }
                    j = next[j]; // j 指向 next[j]和 strchs[i] 错配时的 next[j]的前一个字符 即 next[j]-1，
                }

            }
            return next;
        }

        // 22. 文本左右对齐
        public List<String> fullJustify(String[] words, int maxWidth) {
            Queue<String> wordQueue = new LinkedList<>();
            for (String str : words) {
                wordQueue.add(str);
            }
            List<String> res = new ArrayList<>();
            int leftWidth = maxWidth;
            StringBuilder curStr = new StringBuilder();
            while (!wordQueue.isEmpty()) {
                leftWidth = maxWidth;
                // 获取本次能放入的单词
                List<String> curWords = new LinkedList<>();
                String top = wordQueue.peek();
                if (leftWidth >= top.length()) {
                    curWords.add(wordQueue.poll());
                    leftWidth -= top.length();
                }
                while (!wordQueue.isEmpty()) {
                    top = wordQueue.peek();
                    if (leftWidth >= top.length() + 1) {
                        curWords.add(top);
                        wordQueue.poll();
                        leftWidth -= (top.length() + 1);
                    } else {
                        break;
                    }
                }
                // 分配本轮单词
                if (wordQueue.isEmpty()||curWords.size()==1) {
                    // 本轮是最后一轮或者只有一个单词
                    curStr.append(curWords.get(0));
                    for (int i = 1; i < curWords.size(); i++) {
                        curStr.append(' ');
                        curStr.append(curWords.get(i));
                    }
                    res.add(appendSpace(curStr.toString(), maxWidth));
                    if(wordQueue.isEmpty()){
                        return res;
                    } else{
                        curStr = new StringBuilder();
                        continue;
                    }
                } else {
                    // 不是最后一轮，分配空格
                    leftWidth = maxWidth;
                    for (String curW : curWords) {
                        leftWidth -= curW.length();
                    }
                    int spaceNums = 0;
                    int firstSpace = 0;
                    if (curWords.size() > 1) {
                        spaceNums = leftWidth / (curWords.size() - 1);
                        if (leftWidth % (curWords.size() - 1) == 0) {
                            firstSpace = spaceNums;
                        } else {
                            firstSpace = spaceNums+leftWidth % (curWords.size() - 1);
                        }
                    }
                    curStr.append(curWords.get(0));
                    for (int i = 0; i < firstSpace; i++) {
                        curStr.append(' ');
                    }
                    for (int i = 1; i < curWords.size() - 1; i++) {
                        curStr.append(curWords.get(i));
                        for (int j = 0; j < spaceNums; j++) {
                            curStr.append(' ');
                        }
                    }
                    curStr.append(curWords.get(curWords.size() - 1));
                    // 组装
                    res.add(curStr.toString());
                    curStr = new StringBuilder();
                }
            }
            return res;
        }

        private String appendSpace(String str, int maxLength) {
            StringBuilder builder = new StringBuilder();
            builder.append(str);
            for (int i = 0; i < maxLength - str.length(); i++) {
                builder.append(' ');
            }
            return builder.toString();
        }
    }

    class Node {
        int cost;
        int ind;

        public Node(int cost, int ind) {
            this.cost = cost;
            this.ind = ind;
        }
    }

    class RandomizedSet {

        private Set<Integer> set;

        public RandomizedSet() {
            this.set = new HashSet<>();
        }

        public boolean insert(int val) {
            if (set.contains(val))
                return false;
            else
                set.add(val);
            return true;
        }

        public boolean remove(int val) {
            if (!set.contains(val))
                return false;
            else
                set.remove(val);
            return true;
        }

        public int getRandom() {
            Object[] arr = this.set.toArray();
            return (int) arr[(int) (Math.random() * arr.length)];
        }
    }
}
