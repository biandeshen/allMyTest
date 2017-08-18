package com.lanqiao.algorithm;

import java.awt.List;

public class QuickSort {
    public QuickSort(int arr[]) {
        _QuickSort(arr, 0, arr.length-1);
        for(int i:arr){
            System.out.println(i);
        }
    }

    public QuickSort(int[] arr ,int low ,int high) {
        _QuickSort(arr, low, high);
        for(int i:arr){
            System.out.println(i);
        }
    }
    public void _QuickSort(int[] arr ,int low ,int high) {
        if(low < high){
            int middle = getMiddle(arr, low, high);
            _QuickSort(arr,low,middle-1);
            _QuickSort(arr,middle+1,high);
        }
    }

    private int getMiddle(int[] arr,int low,int high) {
        int tmp = arr[low];
        while (low < high) {
            while(low < high && arr[high] >= tmp){
                high--;
            }
            arr[low] = arr[high];
            while(low < high && arr[low] <= tmp){
                low++;
            }
            arr[high] = arr[low];
        }
        arr[low] = tmp;
        return low;
        
    }
}
