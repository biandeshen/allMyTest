package com.lanqiao.string;

import java.util.Random;

import org.apache.commons.lang3.RandomStringUtils;

public class Test {

    public static void main(String[] args) {
        String filename=RandomStringUtils.randomAlphanumeric(10);
        String name = RandomStringUtils.randomAlphabetic(100);
        System.out.println(filename);
        System.out.println(name);
        String rString = RandomStringUtils.random(new Random().nextInt(100),new char[] {'a','b','c','d','e','f','g','h','i','j','k'
                ,'l','m','n','o','p','q','r','s','t','u','v','w','x','y','z',' '});
        System.out.println(rString);
//        for(int i = 0; i <= 0xFFFF ; ++i)
//            System.out.println((char) i);
    }

}
