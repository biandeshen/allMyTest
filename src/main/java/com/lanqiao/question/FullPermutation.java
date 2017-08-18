package com.lanqiao.question;

import java.util.HashSet;
import java.util.Set;

public class FullPermutation {

    public static void main(String[] args) {
        char[] A = "abcdefghi".toCharArray();
        Set<String> set = fullPer(A, 0, A.length - 1);
        System.out.println("set:" + set + "\nSize:" + set.size());
    }

    // public FullPermutation(String str) {
    // char[] chstr = str.toCharArray();
    // Set<String> set= fullPer(chstr, 0, chstr.length-1);
    // System.out.println("set" + set);
    // }

    /*
     * 分析可知，如果需要字符串全排列，首先将字符串转换为字符数组 此时，数组中就是所有的字符串的字符。数组的可能情况如下。
     * 1.字符数组不足3个，也就是说字符为一个或两个。arr.length- 0 <= 1; right - left = 0;
     * 2.字符数组的字符大于2个。也就是right - left > 2。 当大于2个时，新字符添加就能够插入到多个位置。
     * 即可通过递归调用，一直将字符数组简化到足够简单。 fullper中，数组只有两个值[b,c] , 可能排列为bc , cb；
     * 若数组为[a,b,c]，可能排列就为a 插入到[b,c]，即插入到 bc,cb中去，即调用insertChar方法
     * 同理，更多字符的处理也是如此，可用递归左边界+1来缩小数组， 到达特殊情况时返回。
     */

    /**
     * 
     * @param A
     *            待排的字符数组
     * @param left
     *            子数组左边界
     * @param right
     *            子数组右边界
     * @return 全排列的集合
     */
    private static Set<String> fullPer(char[] A, int p, int r) {
        if (r - p <= 1) {
            char[] cs = new char[2];
            cs[0] = A[p];
            cs[1] = A[r];
            Set<String> set = new HashSet<String>();
            set.add(new String(cs));
            cs[0] = A[r];
            cs[1] = A[p];
            set.add(new String(cs));
            return set;
        }
        /*
         * fullPer方法中包含return insertChar,故想当于递归调用 A , 即相当于上注释中的a，left+1,right
         * 则是数组[b,c];
         * 
         */
        return insertChar(A[p], fullPer(A, p + 1, r));
    }

    private static Set<String> insertChar(char c, Set<String> setIn) {
        Set<String> set = new HashSet<String>();
        for (String s : setIn) {// 取出set中的字符串，并且进行操作。
            // 输入数组chstr
            char[] cs = s.toCharArray();
            int len = cs.length + 1;
            char[] result = new char[len];
            // A在结果数组中的位置可变化，从arr[0]直到arr[len-1],故for循环赋值
            for (int i = 0; i < len; i++) {
                result[i] = c;
                // 若结果数组的值从i开始，而输入数组chstr却每次从0开始，二者变化参数不同
                // 即当结果数组下标为i时，需跳过当前位置，即++！
                for (int j = 0, k = 0; k < len - 1; j++, k++) {
                    if (j == i) {
                        j++;
                    }
                    // if( j==i){
                    // j++;
                    // result[j] = cs[k];
                    // }
                    result[j] = cs[k];
                }
                // 结果数组添加进全排列字符串结果集中。
                set.add(new String(result));
            }
        }
        // 返回将a已插入各位置后字符串的结果集
        return set;
    }
}
