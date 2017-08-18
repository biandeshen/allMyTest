package com.lanqiao.string;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

import javax.script.Bindings;
import javax.script.Compilable;
import javax.script.CompiledScript;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import org.apache.commons.io.FileUtils;

public class RandomStringTest {
    public static void main(String[] args) throws Exception {
        String sb = getRandomString(1000000);
        // System.out.println(sb);
        int count = 0;
//        String string = getName();
//        System.out.println(string);
        
        System.out.println("本程序统计txt或html文件的英文词频");  
        System.out.println("注意：此程序不识别缩写词，会把缩写词以'为界当做两个单词处理！");  
        System.out.println("请输入文件(绝对路径,包括后缀名):");  
        Scanner reader=new Scanner(System.in);  
        String name=reader.next();  
          
        String context="";  
        int c;  
  
        FileReader fr=new FileReader(name);  
        while((c=fr.read())!=-1){  
            context+=(char)c;  
        }  
        context=context.toLowerCase();  
  
          
        if(name.endsWith(".txt"))  
            countTextWords(context);  
        else if(name.endsWith(".html"))  
            countHtmlWords(context);  
        else  
            System.out.println("不处理这种文件");  
        reader.close();  
        fr.close();  
        // FileUtils.write(new File("F:/random/random.txt"), sb,"UTF-8");

        // 打开流，如果不存在创建文件及其目录结构  
        // 第二个参数表示 文件流是否是追加方式 
        // FileOutputStream fos = FileUtils.openOutputStream(new
        // File("F:/random/random.txt"), true);
        // fos.write(sb.getBytes());
        // fos.close();
        // System.out.println(FileUtils.readFileToString(new
        // File("F:/random/random.txt"),"UTF-8"));
    }

    /**
     * 随机生成指定长度的字符串
     * 
     * @param length
     *            字符串长度
     * @return 生成的字符串
     */
    public static String getRandomString(int length) {
        String base = "abcedfghijklmnopqrstuvwxyz";
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }

        return sb.toString();

    }

    
    static void countTextWords(String string){  
        String stringArray[]=string.split("[^a-zA-Z]");  
          
        final Map<String,Integer> map=new HashMap<String,Integer>();  
        Integer count;  
        for(int i=0;i<stringArray.length;i++){  
            count=map.get(stringArray[i]);  
            if(count==null){  
                map.put(stringArray[i], 1);  
            }else{  
                map.put(stringArray[i], count+1);  
            }  
        }  
        map.remove("");    //不知道为什么会有 什么值都不是的东西统计进去，这样可以去掉  
        Set set=map.keySet();  
          
        Integer sum=0,value;  
        String key;  
        for(Iterator iter=set.iterator();iter.hasNext();){   //先计算出总的单词数  
            key=(String)iter.next();  
            value=map.get(key);  
            sum+=value;  
        }  
        for(Iterator iter=set.iterator();iter.hasNext();){  
            key=(String)iter.next();  
            value=map.get(key);  
            float frequency=(float)value/sum;  
              
            java.text.DecimalFormat df=new java.text.DecimalFormat("#0.000");  
            System.out.println(key+":"+value+"  "+df.format(frequency));  
        }  
  
    } 
    
    static void countHtmlWords(String string){  
        string=string.replaceAll("<.*?>", "");  
        countTextWords(string);  
    }

    
    public static String getName() throws Exception {
        String name = null;
        //  ScriptEngineManager manager = new ScriptEngineManager();
        //   
        //      ScriptEngine engine = manager.getEngineByName("javascript");
        //      try {
        //          String str="2&1";
        //          Double d = (Double) engine.eval(str);
        //          Integer i=d.intValue();
        //          System.out.println(i);
        //      } catch (ScriptException ex) {
        //         ex.printStackTrace();
        //      }
        // ScriptEngineManager manager = new ScriptEngineManager();
        // ScriptEngine engine1 = manager.getEngineByName("js");
        // String script="";
        // Object string = engine.eval(script);

        String jsFileName = "F:\\random\\word_generator.js"; // 读取js文件
        FileReader reader = new FileReader(jsFileName); // 执行指定脚本
        // Object string = engine1.eval(reader);

        // ScriptEngineManager mgr = new ScriptEngineManager();
        // ScriptEngine jsEngine = mgr.getEngineByName("JavaScript");
        // jsEngine.eval(new FileReader(new
        // File("F:\\random\\word_generator.js")));
        // Invocable invocableEngine = (Invocable) jsEngine;
        // String wordgenerator = "word_generator.js";
        // String output = (String) invocableEngine.invokeFunction("this",
        // wordgenerator);
        // return output;
        Object result = null;
        try {
            ScriptEngine engine = new ScriptEngineManager().getEngineByName("javascript");
            Compilable compilable = (Compilable) engine;
            Bindings bindings = engine.createBindings(); // Local级别的Binding
            String script = FileUtils.readFileToString(new File("F:\\random\\word_generator.js"), "UTF-8"); // 定义函数并调用
            CompiledScript JSFunction = compilable.compile(script); // 解析编译脚本函数
            // bindings.put("a", 1);bindings.put("b", 2); //通过Bindings加入参数
            bindings.put(script, result);
            result = JSFunction.eval(bindings);
            System.out.println(result); // 调用缓存着的脚本函数对象，Bindings作为参数容器传入
        } catch (ScriptException e) {
        }
        return (String) result;
    }
    // public int getFrequency(String[] article, int n, String word) {  
    //         int count=0;  
    //         for(int i=0;i<n;i++){  
    //             if(article[i].equals(word))            
    //                 count++;  
    //         }  
    //         return count;  
    //     }  
    // public static String getRandomString(int length) { //length表示生成字符串的长度  
    //     String base = "abcdefghijklmnopqrstuvwxyz0123456789";     
    //     Random random = new Random();     
    //     StringBuffer sb = new StringBuffer();     
    //     for (int i = 0; i < length; i++) {     
    //         int number = random.nextInt(base.length());     
    //         sb.append(base.charAt(number));     
    //     }     
    //     return sb.toString();     
    //  }  
}
