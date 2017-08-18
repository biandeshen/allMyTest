package com.lanqiao.thread;

import java.util.Scanner;

public class ScanfTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int sum = 0;
        boolean isExit = false;
        for (int i = 1; i <= 10; i++) {
            System.out.println("请输入第"+i+"个学生的成绩：");
            int score = scanner.nextInt();
            if (score > 100 || score <0) {
                System.out.println("成绩无效！");
                isExit = true;
                break;
            }
            sum += score;
        }
        
        int avg = sum / 10;
        while(!isExit){
            if (avg >= 90 || avg < 100) {
                System.out.println("A");
                break;
            }else if (avg >= 80 || avg < 90) {
                System.out.println("B");
                break;
            }else if (avg >= 70 || avg < 80) {
                System.out.println("C");
                break;
            }else if (avg > 60 || avg < 70) {
                System.out.println("D");
                break;
            }else {
                System.out.println("E");
                break;
            }
        }
    }
}
