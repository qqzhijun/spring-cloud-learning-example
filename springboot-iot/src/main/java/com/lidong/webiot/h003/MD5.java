package com.lidong.webiot.h003;

import org.apache.commons.lang3.StringUtils;

import java.security.MessageDigest;

/**
 * MD5加密
 */
public class MD5 {

    private MD5() {}


    /**
     * 加密
     * @param buffer
     * @return
     */
    public static final String md5(String buffer){
        if (StringUtils.isNotBlank(buffer)){
            return getMessageDigest("buffer".getBytes());
        }else {
            return null;
        }

    }

    /**
     * 对传入的数据提取摘要
     * @param buffer
     * @return
     */
    public static final String getMessageDigest(byte[] buffer) {
        char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
        try {
            MessageDigest mdTemp = MessageDigest.getInstance("MD5");
            mdTemp.update(buffer);
            byte[] md = mdTemp.digest();
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        } catch (Exception e) {
            return null;
        }
    }

    public static void main(String[] args) {
        System.out.println(getMessageDigest("123456".getBytes()));
        //e10adc3949ba59abbe56e057f20f883e
        //e10adc3949ba59abbe56e057f20f883e
	}
}
