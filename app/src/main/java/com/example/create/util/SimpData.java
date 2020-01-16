package com.example.create.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Create by 张瀛煜 on 2020-01-15
 */
public class SimpData {
    public static String Simp(String type, Date date) {
        SimpleDateFormat format = new SimpleDateFormat(type);
        return format.format(date);
    }
}
