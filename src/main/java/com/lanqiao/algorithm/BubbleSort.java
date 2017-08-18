package com.lanqiao.algorithm;

public class BubbleSort {
    
    public BubbleSort(int[] arr) {
        sort(arr);
    }

    private void sort(int[] arr) {
        int tmp = 0;
        for (int n = 0; n < arr.length-1; n++) {
            // 首先找到最大的数，放在最后面，然后多趟循环，找出第二、三。。。
            for (int i = 0; i < arr.length-n-1; i++) {
                if (arr[i] > arr[i+1]) {
                    tmp = arr[i];
                    arr[i] = arr[i + 1];
                    arr[i + 1] = tmp;
                }
            }
            for(int i = 0 ; i < arr.length;i++){
                System.out.println(arr[i]);
            }
        }

    }

}
