package com.ccx.common.lang.utils;

import org.apache.commons.codec.digest.DigestUtils;

import com.alibaba.fastjson.JSON;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class ConstantUtils {

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static enum INTEGER {
        MINUS(-1), ZERO(0), ONE(1), TWO(2), THREE(3), FORE(4), FIVE(5), SIX(6), SEVEN(7), EIGHT(8), NINE(9), TEN(10);

        private int value;

    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static enum LONG {
        MINUS(-1L), ZERO(0L), ONE(1L), TWO(2L), THREE(3L), FORE(4L), FIVE(5L), SIX(6L), SEVEN(7L), EIGHT(8L), NINE(9L), TEN(10L);

        private long value;

    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static enum STRING {
        MINUS("-1"), ZERO("0"), ONE("1"), TWO("2"), THREE("3"), FORE("4"), FIVE("5"), SIX("6"), SEVEN("7"), EIGHT("8"), NINE("9"), TEN("10");

        private String value;
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static enum INTEGERSEC {
        SECOND(1), MINU(60), HOUR(60 * 60), DAY(24 * 60 * 60), WEEK(7 * 24 * 60 * 60);

        private int value;
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static enum LONGSEC {
        SECOND(1), MINU(60), HOUR(60 * 60), DAY(24 * 60 * 60), WEEK(7 * 24 * 60 * 60);

        private long value;
    }

    // charsetName
    public static final String UTF8 = "UTF-8";

    public static final Long timeout = LONGSEC.MINU.getValue();

    public static <T> String toString(T t) {
        // 此处不能用 String.valueOf(t)，因为多机部署或是当前对象被销毁后又重新产生的对象，他们的 hashCode() 不一样
        return (t == null) ? null : DigestUtils.md5Hex(JSON.toJSONString(t));
    }

    public static <T> String toString(String key, T t) {
        return key + "_" + toString(t);
    }

    public static String append(Object... args) {
        StringBuilder sb = new StringBuilder();
        for (Object object : args) {
            sb.append(object).append("_");
        }
        return sb.toString();
    }

}
