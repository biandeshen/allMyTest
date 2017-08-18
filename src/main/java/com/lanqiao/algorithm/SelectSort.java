package com.lanqiao.algorithm;


public class SelectSort {

    public void sort(int[] arr) {
        // 首先创建一个临时变量，需要交换值时可用
        int tmp = 0;
        // 外层嵌套循环，由于每次占未排序第一个位置，故动态变化，用j表示
        for (int j = 0; j < arr.length; j++) {
            // 首先找出最小值,假定为数组第一个值
            tmp = arr[j];// j
            // 循环结束后，最小值在tmp中
            for (int i = j+1/*0*/; i < arr.length; i++) {
                if (arr[i] < tmp) {
                    arr[j] = arr[i];
                    arr[i] = tmp;
                    tmp = arr[j];

                }
                for(int m = 0 ; m < arr.length; m++){
                    System.out.print(arr[m]+" ");
                }
                System.out.println();
            }
            System.out.println();
        }

    }
}
