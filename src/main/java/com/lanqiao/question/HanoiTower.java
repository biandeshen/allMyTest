package com.lanqiao.question;

public class HanoiTower {

    /**
     * 将N个盘子从source移动到target的路径的打印
     *
     * @param N
     *            初始的N个从小到达的盘子，N是最大编号
     * @param source
     *            原始柱子
     * @param target
     *            目标柱子
     * @param temp
     *            辅助的柱子
     */
    private void printHanoiTower(int N, String source, String target, String temp) {
        if (N == 1) {
            System.out.println("move " + N + " from " + source + " to " + target);
        } else {
            //即表示为从source借助temp临时空间将N移动到target，此时temp临时空间的状态未1，2...N-1
            printHanoiTower(N - 1, source, temp, target);
            System.out.println("move " + N + " from " + source + " to " + target);
            //此时，需要从临时空间temp将数据移回source,就需借助target来实现，故有以下代码
            printHanoiTower(N - 1, temp, target, source);
        }
    }

    public static void main(String[] args) {
        new HanoiTower().printHanoiTower(4, "A", "C", "B");
    }
}
