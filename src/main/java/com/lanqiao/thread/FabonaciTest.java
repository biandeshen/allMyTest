package com.lanqiao.thread;

public class FabonaciTest {

    public static void main(String[] args) {
        int a,b,c = 0;
        a = 1;
        b = 1;
        int n = 7;
        if(n ==1 || n ==2){
        c=1;
        }else{
            for( int i = 3; i <= n; i++){
               c = a+b;
               a = b;
               b = c;
            }
        }
        System.out.println(c);
    }

}
