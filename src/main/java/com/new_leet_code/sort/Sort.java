package com.new_leet_code.sort;

import java.util.*;

/**
 * 所有排序问题
 */
public class Sort {
    public static void main(String[] args) {
        Sort sort = new Sort();
        Solution s = sort.new Solution();
        int[] array = new int[]{5,3,7,5,6,2,4};
        // 1. 插入排序
        s.insertSort(array);
        printArray(array);
        // 2. 选择排序
        array = new int[]{5,3,7,5,6,2,4};
        s.chooseSort(array);
        printArray(array);
        // 3. 冒泡排序
        array = new int[]{5,3,7,5,6,2,4,4,4,4,3,3,3,3};
        s.bubbleSort(array);
        printArray(array);
        // 4. 有序数组二分查找问题 找到n的下标
        System.out.println("target Index:"+s.biFind(array,3));
        // 5. 有序数组二分查找，找到>=n的最左侧下标
        System.out.println("target Index:"+s.biFindMostLeft(array,3));
        // 6. 归并排序
        array = new int[]{5,3,7,5,6,2,4};
        s.mergerSort(array);
        printArray(array);
        // 7. 小和问题
        array = new int[]{1,3,4,2,5};
        System.out.println("small sum:"+s.smallSum(array));
        // 8. 寻找逆序对问题
        array = new int[]{1,3,4,2,5};
        System.out.println("reverse pairs:");
        s.findReversePairs(array).stream().forEach(x->System.out.print(x[0]+" | "+x[1]+"\n"));
        // 9. 荷兰国旗问题
        array = new int[]{5,3,7,5,6,2,4};
        s.flagProblemOne(array);
        printArray(array);
        // 10. 荷兰国旗问题
        array = new int[]{5,3,7,5,6,2,4,5};
        s.flagProblemTwo(array);
        printArray(array);
        // 10. 荷兰国旗问题 while 且有返回值版本
        array = new int[]{5,3,7,5,6,2,4,5};
        int[] re = s.flagProblemTwoRewrite(array);
        System.out.println("l:"+re[0]+" r:"+re[1]);
        printArray(array);
        // 11. 快排
        array = new int[]{5,3,7,5,6,2,4,5};
        s.quickSort(array);
        printArray(array);
        // 12. 堆排
        array = new int[]{5,3,7,5,6,2,4,5};
        s.heapSort(array);
        printArray(array);
    }

    class Solution{

        // 1. 插入排序
        public void insertSort(int[] array){
            for(int i=0;i<array.length-1;i++){
                for(int j=i+1;j>0;j--){
                    // 和前面的比较看是否要交换
                    if(array[j]<array[j-1])swap(array, j, j-1);
                }
            }
        }
        // 2. 选择排序
        public void chooseSort(int[] array){
            for(int i=0;i<array.length;i++){
                int minIndex = i;int minVal = array[i];
                for(int j=i;j<array.length;j++){
                    // 遍历最小的下标，和i所在位置交换
                    if(array[j]<minVal){
                        minIndex = j;
                        minVal = array[j];
                    }
                }
                swap(array, minIndex, i);
            }
        }
        //3. 冒泡排序
        public void bubbleSort(int[] array){
            for (int i = array.length; i > 0; i--) {
                for (int j = 0; j < i-1; j++) {
                    if(array[j]>array[j+1])swap(array, j, j+1);
                }
            }
        }

        // 4. 有序数组二分查找问题 找到n的下标
        public int biFind(int[] array,int n){
            return doBiFind(array, n, 0, array.length-1);
        }
        public int doBiFind(int[] array,int n,int l,int r){
            if (l>r) {
                return -1;
            }
            int mid = (l+r)/2;
            if(array[mid]==n)return mid;
            else if(array[mid]>n) return doBiFind(array, n, l, mid-1);
            else return doBiFind(array, n, mid+1, r);
        }

        // 5. 有序数组二分查找，找到>=n的最左侧下标
        public int biFindMostLeft(int[] array,int n){
            int[] posi = new int[]{-1};
            return doBiFindMostLeft(array,n,0,array.length-1,posi);
        }
        public int doBiFindMostLeft(int[] array,int n,int l,int r,int[] posi){
            if(l>r)return -1;
            int mid = (l+r)/2;  
            if(array[mid]>n)doBiFindMostLeft(array, n, l, mid-1, posi);
            else if(array[mid]<n)doBiFindMostLeft(array, n, mid+1, r, posi);
            else{
                if(mid < posi[0] || posi[0]==-1)posi[0]=mid;
                doBiFindMostLeft(array, n, l, mid-1, posi);
            }
            return posi[0];
        }

        // 5. 无序数组二分查找，求局部最小
        public int biFindLocalMin(int[] array){
            return doBiFindLocalMin(array, 0, array.length-1);
        }
        public int doBiFindLocalMin(int[] array,int l,int r){
            if(l+3>r)return -1;
            int mid = (l+r)/2;
            if(array[mid]<array[l] && array[mid]<array[r]) return mid;
            else if(array[mid]>array[mid+1]) return doBiFindLocalMin(array, mid+1, r);
            else return doBiFindLocalMin(array, l, mid-1);
        }

        // 6. 归并排序
        public void mergerSort(int[] array){
            doMergeSort(array, 0, array.length-1);
        }
        public void doMergeSort(int[] array,int l,int r){
            if(l>=r)return;
            int mid = (l+r)/2;
            doMergeSort(array, l, mid);
            doMergeSort(array, mid+1, r);
            // 左右两个分区已经排好序，进行合并，合并到新的数组
            int[] resArray = new int[r-l+1];
            int p=0;
            int i = l;
            int j = mid+1;
            while (i<=mid && j<=r) {
                // 两个指针没有越界
                if(array[i]<=array[j]) resArray[p++]=array[i++];
                else resArray[p++]=array[j++];
            }
            while(i<=mid)resArray[p++]=array[i++];
            while(j<=r)resArray[p++]=array[j++];
            // resArray 写回
            for(int k=0;k<resArray.length;k++){
                array[l+k] = resArray[k];
            }
        }

        // 7. 小和问题 1 3 4 2 5， 每一个数的左边比自己小的数的和，归并思想，合并两组的时候产生小和
        public int smallSum(int[] array){
            return doSmallSum(array, 0, array.length-1);
        }
        public int doSmallSum(int[] array,int l,int r){
            if(l>=r)return 0;
            int res = 0;
            int mid = (l+r)/2;
            res += doSmallSum(array, l, mid)+doSmallSum(array, mid+1, r);
            // 左右两组已排序并且产生了小和，合并两组并产生小和
            int[] help = new int[r-l+1];
            int p1=0;
            int p2=l;
            int p3=mid+1;
            while(p2<=mid&&p3<=r){
                if(array[p2]<array[p3]){
                    res += array[p2]*(r-p3+1);
                    help[p1++] = array[p2++];
                }else{
                    help[p1++] = array[p3++];
                }
            }
            while(p2<=mid) help[p1++]=array[p2++];
            while(p3<=r) help[p1++]=array[p3++];
            // 拷贝回去
            for(int i=0;i<help.length;i++){
                array[l+i] = help[i];
            }
            return res;
        }

        // 8. 找逆序对数量，和小和问题一样，1 3 4 2 5，逆序对：4 2，3 2。同样归并思想，左：4 3 1，右：5 2，左边比右边大计算逆序对，倒序排序
        public List<Integer[]> findReversePairs(int[] array){
            List<Integer[]> pairs = new ArrayList<>();
            doFindReversePairs(array, pairs,0,array.length-1);
            return pairs;
        }
        public void doFindReversePairs(int[] array,List<Integer[]> pairs,int l,int r){
            if(l>=r)return;
            int mid = (l+r)/2;
            doFindReversePairs(array, pairs, l, mid);
            doFindReversePairs(array, pairs, mid+1, r);
            // 合并并且寻找逆序对  合并时谁大谁放入
            int point=0;
            int i=l;
            int j=mid+1;
            int[] help = new int[r-l+1];
            while (i<=mid && j<=r) {
                if(array[j]>=array[i]) help[point++]=array[j++];
                else{
                    // 逆序对
                    for(int k=j;k<=r;k++){
                        pairs.add(new Integer[]{array[i],array[k]});
                    }
                    help[point++]=array[i++];
                }
            }
            while(i<=mid) help[point++]=array[i++];
            while(j<=r) help[point++]=array[j++];
            for(int k=0;k<help.length;k++){
                array[l+k] = help[k];
            }
        }
        
        // 9. 荷兰国旗问题 1.0 只有小于等于
        public void flagProblemOne(int[] array){
            // 取末尾的数作为对比
            int cmp = array[array.length-1];
            int l = -1;
            for (int i = 0; i < array.length-1; i++) {
                if(array[i]<=cmp)swap(array, i, ++l);
            }
            swap(array, l+1, array.length-1);
        }
        // 10. 荷兰国旗 2.0 小于在左边，等于在中间，大于在右边
        public void flagProblemTwo(int[] array){
            int cmp = array[array.length-1];
            int l=-1;
            int r = array.length-1;
            for(int i=0;i<array.length-1 && i<r;i++){
                if(array[i]<cmp)swap(array, i, ++l);
                else if(array[i]==cmp)continue;
                else {
                    swap(array, i, --r);
                    i--;
                }
            }
            swap(array, array.length-1, r);
        }
        // 10.1 荷兰国旗，while版本，并且返回中间等于区的左右下标
        public int[] flagProblemTwoRewrite(int[] array){
            int i=0;
            int[] index = new int[2];
            int l=-1;
            int r=array.length-1;
            int cmp = array[array.length-1];
            while(i!=r){
                if(array[i]<cmp)swap(array, i++, ++l);
                else if(array[i]==cmp) i++;
                else swap(array, i, --r);
            }
            swap(array, array.length-1, r);
            index[0] = l+1;
            index[1] = r;
            return index;
        }
        // 11. 快排2.0
        public void quickSort(int[] array){
            doQuickSort(array, 0, array.length-1);
        }
        public void doQuickSort(int[] array,int s,int e){
            if(s>=e)return;
            int cmp = array[e];
            int l = s-1;
            int r = e;
            int i=s;
            while(i!=r){
                if(array[i]<cmp)swap(array, ++l, i++);
                else if(array[i]==cmp)i++;
                else swap(array, --r, i);
            }
            swap(array, r, e);
            // 排序左半部分
            doQuickSort(array, s, l);
            // 排序右半部分
            doQuickSort(array, r+1, e);
        }

        // 12. 堆排序  大根堆
        public void heapSort(int[] array){
            // 自底往上建堆
            for (int i = array.length-1; i >=1; i--) {
                // 每个节点和自己的父母比较
                if(array[i]>array[(i-1)/2])swap(array, i, (i-1)/2);
            }
            int i=array.length-1;
            while(i>=1) {
                swap(array, i--, 0); // 取出根节点
                // heapfier 操作
                int k=0;
                while(k<=(i-1)/2){
                    int maxInd = k;
                    if(k*2+1<=i &&array[k]<array[k*2+1]) maxInd = k*2+1;
                    if(k*2+2<=i &&array[k]<array[k*2+2]&&array[k*2+2]>array[k*2+1]) maxInd=k*2+2;
                    if(maxInd==k)break;
                    swap(array, maxInd, k);
                    k = maxInd;
                }
            }
        }

        public void swap(int[] array,int i,int j){
            if(i==j)return;
            array[i] = array[i]^array[j];
            array[j] = array[i]^array[j];
            array[i] = array[i]^array[j];
        }

    }

    public static void printArray(int[] array){
        for(int num:array){
            System.out.print(" = "+num);   
        }
        System.out.println();
    }
}
