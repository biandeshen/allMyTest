package com.lanqiao.algorithm;

import java.util.Arrays;

public class MergeSort {
    public MergeSort(int[] arr) {
        sort(arr,0,arr.length-1);
        for(int i = 0; i < arr.length; i++){
            System.out.println(arr[i]);
        }
    }

    /*
     * 排序，递归调用，以便将序列拆分成小序列，直至为两个一组（即左小于右时递归） sort结构大致为leftSort,rightSort,merge
     * leftSort又可分为leftleftSort,leftrightsort,merge。。。 依次细分，直至分无可分，就从最小处进行合并
     */
    public void sort(int[] arr, int left, int right) {
        if (left < right) {
            // 求中间索引
            int center = (left + right) / 2;
            // 对左边数组进行递归
            sort(arr, left, center);
            // 对右边数组进行递归
            sort(arr, center + 1, right);
            // 对递归后的数组进行合并
            Merge(arr, left, center, right);
        }
    }

    /*
     * 合并的规则
     */
    private void Merge(int[] arr, int left, int center, int right) {
        // 临时数组
        int[] tmpArr = new int[arr.length];
        // 左数组第一个元素index
        int tmp = left;
        // 右数组第一个元素index
        int mid = center + 1;
        // tmpindex记录临时数组的索引
        int tmpindex = left;
        /*
         * 循环到最后，数组应为两个值，且简化为两个数组，左右数组
         */
        while (left <= center && mid <= right) {
            // 从两个数组中取出最小值放入临时数组
            if (arr[left] <= arr[mid]) {
                // 此时数组设有4个值，长度为4，则左数组为0,1 右数组为2,3 left = 0,
                // center = 0 + 3 / 2 = 1, mid = center + 1 = 2,right = 3
                // 若数组为[57,68] [52,59] 则有如下情况
                // 1.arr[left 0] <= arr[mid 2] 57 < 52不成立，则执行else，52先放，即[52, , ,
                // ],同时mid++,mid先为2，后自增;
                // 2.mid此时为3，即arr[left 0] <= arr[mid 3] 57 <= 59
                // 成立，则直接执行，57先放，即[52,57, , ],同时left++,left先为0，后自增;
                // 3.left此时为1，即arr[left 1] <= arr[mid 3] 68 <= 59
                // 不成立，执行else，59先放，即[52,57,59, ],同时mid++，mid先为3，后自增;
                // 4.mid此时为4，跳出循环，此时左数组剩余68未放入,left = 1，数组为[52,57,59, ];
                //
                tmpArr[tmpindex++] = arr[left++];
            } else {
                tmpArr[tmpindex++] = arr[mid++];
            }
        }
        // 下面两组while实际因为上面的while循环只能执行一组，因为上一循环中
        // 要么left++退出循环，要么mid++退出循环，即一个数仍然正常，一个数却因为超出退出循环
        // 所以将会剩余一个值无法被存储在临时数组中，导致值未完全复制。此时唯有再次用循环判断
        // 此值要么在左侧，要么在右侧，可根据上一循环得知必有一值超出
        // 找出正常值，专门为其赋值，使值完全被复制
        while (mid <= right) {
            tmpArr[tmpindex++] = arr[mid++];
        }
        //
        while (left <= center) {
            tmpArr[tmpindex++] = arr[left++];
        }

        // 将中间数组的值复制回原数组,从左数组索引tmp(left)开始，直至right
        while (tmp <= right) {
            arr[tmp] = tmpArr[tmp++];
        }

        // 重写Arrays.toString()方法，打印数组arr
        System.out.println(Arrays.toString(arr));

    }
}
