package com.example5.util;

@Deprecated
public class ValidUtils {

    public static boolean isNull(String value) {
        return value == null;
    }

    public static String isNullMessage(String parameterName) {
        return "[" + parameterName + "] 不能为null";
    }

    public static boolean isEmpty(String value) {
        return isNull(value) || value.length() == 0;
    }

    public static String isEmptyMessage(String parameterName) {
        return "[" + parameterName + "]不能为blank";
    }

    public static boolean isEmail(String value) {
        // TODO
        return false;
    }

    public static boolean isSize(String value) {
        // TODO
        return false;
    }
}
