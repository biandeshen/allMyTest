package com.lanqiao.algorithm;


public class ShellSortTest {

    public static void main(String[] args) {
        int [] arr ={12,6,82,54,62,1,9,145,224,11};
        ShellSort shellSort = new ShellSort();
        shellSort.sort(arr, 0, 5);
    }

}
