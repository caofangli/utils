package com.cfl.oneself.utils.inter.interviewjvm2;

import java.util.Random;

/**
 * OutOfMemoryError
 */
public class HOutOfMemoryErrorDemo {
    public static void main(String[] args){
        String str = "aaaaa";
        while (true) {
            str+=str + new Random().nextInt(1111111) + new Random().nextInt(2222222);
            str.intern();
        }
        // Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
    }
}
