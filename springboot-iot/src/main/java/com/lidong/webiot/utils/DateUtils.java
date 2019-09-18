package com.lidong.webiot.utils;

import org.apache.commons.lang3.time.DateFormatUtils;

import java.util.Date;

public class DateUtils {
    /**
     *
     * @return
     */
    public static String dateToString(Date date) {
        String format = DateFormatUtils.format(date, "yyyy-MM-dd HH:mm:ss");
        return format;
    }

    public static String dateToStringYyyyMMddHHmmss(Date date) {
        String format = DateFormatUtils.format(date, "yyyyMMddHHmmss");
        return format;
    }
}
