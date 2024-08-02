package com.foo.kd;

import org.apache.commons.lang.StringUtils;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 数据校验
 */
public final class ValidataUtil {
    private static final String NONE = "null";
    private static final Long DEFAULT_LONG = 0L;
    private static final Integer DEFAULT_INTEGER = 0;
    private static final Object DEFAULT_NULL = null;
    private static final BigDecimal DEFAULT_DECIMAL = new BigDecimal("0.00");
    private static final Double DEFAULT_DOUBLE = 0.00D;

    /**
     * 校验Long
     *
     * @param data
     * @return
     */
    public static Long valiLong(String data) {
        if (!StringUtils.equals(data, NONE) && StringUtils.isNumeric(data)) {
            return Long.parseLong(data);
        }
        return DEFAULT_LONG;
    }

    /**
     * 校验Integer
     *
     * @param data
     * @return
     */
    public static Integer valiInt(String data) {
        if (!StringUtils.equals(data, NONE) && StringUtils.isNumeric(data)) {
            return Integer.parseInt(data);
        }
        return DEFAULT_INTEGER;
    }

    /**
     * 校验String
     *
     * @param data
     * @return
     */
    public static String valiString(String data) {
        if (!StringUtils.equals(data, NONE) && StringUtils.isNotBlank(data)) {
            return data;
        }
        return (String) DEFAULT_NULL;
    }

    /**
     * 校验Double
     *
     * @param data
     * @return
     */
    public static Double valiDouble(String data) {
        if (!StringUtils.equals(data, NONE)) {
            return Double.parseDouble(data);
        }
        return DEFAULT_DOUBLE;
    }

    /**
     * 校验时间戳
     *
     * @param data
     * @return 时间维表key
     */
    public static Long valiTimestamp(String data) {
        if (!StringUtils.equals(data, NONE) && isValidTimestamp(data)) {
            String dateKey = LocalDateTime.parse(data, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")).format(DateTimeFormatter.ofPattern("yyyyMMdd"));
            return Long.parseLong(dateKey);
        }
        return DEFAULT_LONG;
    }

    /**
     * 判断是否为yyyy-MM-dd HH:mm:ss格式时间
     *
     * @param str
     * @return
     */
    private static boolean isValidTimestamp(String str) {
        boolean result = true;
        if (str.length() == 19) {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            try {
                format.setLenient(false);
                format.parse(str);
            } catch (ParseException e) {
                result = false;
            }
        } else {
            result = false;
        }

        return result;
    }

    public static void main(String[] args) throws ParseException {

        System.out.println(valiTimestamp("2019-01-03 10:01:12"));
    }
}
