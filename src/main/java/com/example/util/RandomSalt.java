package com.example.util;

import java.util.Random;

/**
 * @author JYuan
 * @create 2021-08-19 18:38
 */
public class RandomSalt {
    private static final String SALT = "qwertyuiop[]';lkjhgfdsazxcvbnm,./";

    /**
     * 自定义生成随机盐的方法
     * @param length
     * @return
     */
    public static String getSalt(int length) {
        char[] random = SALT.toCharArray();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            stringBuilder.append(random[new Random().nextInt(random.length)]);
        }
        return stringBuilder.toString();
    }
}
