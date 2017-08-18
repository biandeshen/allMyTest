package com.lanqiao.algorithm;

public class InsertSort {
    public void sort(int[] arr,int low,int high) {
        int tmp = 0;
        if (low < high) {
            for(int i = low + 1; i < high ; i++){
                int j = i-1;
                tmp = arr[i];
                for( ;j>=0 && arr[j] > tmp ;j--){//以选取值low为基点， 挨个往前遍历
                    arr[j+1] = arr[j];//凡是大于基准值的，坐标值+1
                }
                arr[j+1] = tmp;//最后，将最终比较完获得的坐标得到，并赋予基准值，达到插入的效果,由于for循环中最后执行了
                                //j--,故赋值时需要+1
//                for(int n = low; n < high ;n++){
//                    System.out.println(arr[n]);
//                }
            }
            for(int n = low; n < high ;n++){
              System.out.println(arr[n]);
          }
        }
    }
}
