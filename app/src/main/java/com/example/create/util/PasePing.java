package com.example.create.util;

import net.sourceforge.pinyin4j.PinyinHelper;

/**
 * Create by 张瀛煜 on 2020-02-04 ：）
 */
public class PasePing {
    public static String getPinYinFirstLetter(String str) {
        if ("".equals(str)) {
            return "#";
        } else {
            StringBuffer sb = new StringBuffer();
            sb.setLength(0);
            char c = str.charAt(0);
            String[] pinyinArray = PinyinHelper.toHanyuPinyinStringArray(c);
            if (pinyinArray != null) {
                sb.append(pinyinArray[0].charAt(0));
            } else {
                sb.append(c);
            }
            return sb.toString().toUpperCase();
        }
    }
}
