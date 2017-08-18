package com.lanqiao.string;

import java.util.HashSet;
import java.util.Set;

public class FullPermutation {
    public static void main(String[] args) {
        char[] A = "abc".toCharArray();
        Set<String> set = fullPer(A, 0, A.length - 1);
        System.out.println("set:" + set + "\nSize:" + set.size());
    }

    /**
     * @param A 待全排列的字符数组
     * @param p 子数组左边界
     * @param r 子数组右边界
     * @return 全排列的集合
     */
    static Set<String> fullPer(char[] A, int p, int r) {
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
        return insertChar(A[p], fullPer(A, p + 1, r));
    }

    /**
     * 将字符 c 插入到集合setIn所有字符串的任意位置
     * @param c char
     * @param setIn Set<String>
     * @return  Set<String>
     */
    private static Set<String> insertChar(char c, Set<String> setIn) {
        Set<String> set = new HashSet<String>();
        for (String s : setIn) {
            char[] cs = s.toCharArray();
            int len = cs.length + 1;
            char[] result = new char[len];
            for (int i = 0; i < len; i++) {
                result[i] = c;
                for (int j = 0, k = 0; k < len - 1; j++, k++) {
                    if (j == i)
                        j++;
                    result[j] = cs[k];
                }
                set.add(new String(result));
            }
        }
        return set;
    }
}