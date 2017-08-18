package com.lanqiao.algorithm;

public class ShellSort {

    public void sort(int[] arr, int low, int high) {
        int tmp = 0;
        // int d = arr.length;
        int d1 = (high - low);
        while (true) {
//            d1 = Math.ceil(d1 / 2);
//            int d = (int) d1;

            int d = (d1 / 2);
            //如果结果d为偶数，则加一
            //奇数则不管
            if (d % 2 == 0) {
                d = d + 1;
            }
            for (int m = 0; m < d; m++) {
                for (int i = m + d; i < high; i += d) {
                    tmp = arr[i];
                    int j = i - d;
                    for (; j >= 0 && arr[j] > tmp; j -= d) {
                        arr[j + d] = arr[j];
                    }
                    arr[j + d] = tmp;
                }
            }
            //改变d的值，故此赋值给d1，当结果d为1时，不可再分，故退出
            d1 = d;
            System.out.println(d);
            if (d == 1) {
                break;
            }
        }
        for (int i = low; i < high; i++) {
            System.out.println(arr[i]);
        }
    }

}
