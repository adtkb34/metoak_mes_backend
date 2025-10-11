package com.metoak.mes.common.util;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class TimestampUtils {

    /**
     * 将毫秒级时间戳转换为指定格式的日期时间字符串
     *
     * @param timestamp 毫秒级时间戳
     * @param pattern   日期时间格式（如"yyyy-MM-dd HH:mm:ss"）
     * @return 格式化后的日期时间字符串
     */
//    public static String convertToDateTime(long timestamp, String pattern) {
//        // 将时间戳转换为LocalDateTime
//        LocalDateTime dateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(timestamp), ZoneId.systemDefault());
//
//        // 使用DateTimeFormatter格式化日期
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
//        return dateTime.format(formatter);
//    }
//
//    public static String convertToDateTime(String timestampStr, String pattern) {
//        // 将时间戳转换为LocalDateTime
//        LocalDateTime dateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(Long.parseLong(timestampStr)), ZoneId.systemDefault());
//
//        // 使用DateTimeFormatter格式化日期
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
//        return dateTime.format(formatter);
//    }

    public static LocalDateTime convertToDateTime(String timestampStr) throws NumberFormatException {
        long timestamp = Long.parseLong(timestampStr);
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(timestamp), ZoneId.systemDefault());
    }

}