package com.lidong.webiot.utils;

import java.io.UnsupportedEncodingException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * unicode和字符串之间的转化
 */
public class CoderUtils {

    public static void main(String[] args) {
//        System.out.println(stringToUnicode(convertStringToUTF8("北京市昌平区北七家镇望都家园24号楼;立汤路与定泗路路口东872米")));
    }
    /**
     * 获取字符串的unicode编码
     * 汉字“木”的Unicode 码点为Ox6728
     *
     * @param str 木
     * @return \ufeff\u6728  \ufeff控制字符 用来表示「字节次序标记（Byte Order Mark）」不占用宽度
     * 在java中一个char是采用unicode存储的 占用2个字节 比如 汉字木 就是 Ox6728 4bit+4bit+4bit+4bit=2字节
     */
    public static String stringToUnicode(String str) {
        return HU.convertStringToUTF8(HU.unicodeToString(str));
    }

}

