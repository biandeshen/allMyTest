package com.lanqiao.algorithm;

public class InsertSortTest {
    public static void main(String[] args) {
        int [] arr ={12,6,82,54,62,1,9,145,224,11};
        InsertSort insertSort = new InsertSort();
        insertSort.sort(arr, 0, 4);
    }
}
