package com.lanqiao.string;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Savepoint;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Map.Entry;
import java.util.TreeMap;

import org.apache.commons.io.FileUtils;

/**
 * 
 * @author cute
 * 
 * 
 *         实现从文件中读入英文文章，统计单词个数,并按值从大到小输出
 */

public class WordCount {

    public static void main(String[] args) throws Exception {
        String sb = getRandomString(1000000);
        String fileName = "F:/random/random.txt";
        save(fileName,sb);
        BufferedReader br = new BufferedReader(new FileReader("F:/random/random.txt"));
        List<String> lists = new ArrayList<String>(); // 存储过滤后单词的列表
        String readLine = null;
        while ((readLine = br.readLine()) != null) {
            String[] wordsArr1 = readLine.split("[^a-zA-Z]"); // 过滤出只含有字母的
            for (String word : wordsArr1) {
                if (word.length() != 0) { // 去除长度为0的行
                    lists.add(word);
                }
            }
        }

        br.close();

        Map<String, Integer> wordsCount = new TreeMap<String, Integer>(); // 存储单词计数信息，key值为单词，value为单词数

        // 单词的词频统计
        for (String li : lists) {
            if (wordsCount.get(li) != null) {
                wordsCount.put(li, wordsCount.get(li) + 1);
            } else {
                wordsCount.put(li, 1);
            }

        }

        SortMap(wordsCount); // 按值进行排序

    }

    // 按value的大小进行排序
    public static void SortMap(Map<String, Integer> oldmap) {

        ArrayList<Map.Entry<String, Integer>> list = new ArrayList<Map.Entry<String, Integer>>(oldmap.entrySet());

        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Entry<String, Integer> o1, Entry<String, Integer> o2) {
                return o2.getValue() - o1.getValue(); // 降序
            }
        });

        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i).getKey() + ": " + list.get(i).getValue());
        }
    }

    
    /**
     * 将字符串存入输入的文件名中 如果没有该文件，将新建该文件
     * 
     * @param fileName
     *            文件名
     * @param sb
     *            输入流
     * @throws IOException
     * 
     */
    public static void save(String fileName, String sb) throws IOException {
        // 打开流，如果不存在创建文件及其目录结构  
        // 第二个参数表示 文件流是否是追加方式 
        FileOutputStream fos = FileUtils.openOutputStream(new File("F:/random/random.txt"), true);
        fos.write(sb.getBytes());
        fos.close();
        System.out.println(FileUtils.readFileToString(new File("F:/random/random.txt"), "UTF-8"));
    }

    /**
     * 随机生成指定长度的字符串
     * 
     * @param length
     *            字符串长度
     * @return 生成的字符串
     */
    public static String getRandomString(int length) {
        String base = "abcedfghijklmnopqrstuvwxyz,.; !#@$%^&*";
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }

        return sb.toString();

    }

}