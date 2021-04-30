package cn.kd.util;

/**
 * XXXView相关工具类
 * 页面展示，字段值转换，数据格式化
 */
public class ViewUtils {
    private static Double DEFAULT_VIEW_VALUE = 0D;

    /**
     * 保留两位小数，默认0.00
     * @param divisor  除数
     * @param dividend 被除数
     * @return
     */
    public static String calculateAverageValue(Long divisor, Long dividend) {
        if (divisor == null || divisor == 0.00D
                || dividend == null || dividend == 0.00D) {
            return String.format("%.2f", DEFAULT_VIEW_VALUE);
        }
        Double result = (double) divisor / (double) dividend;
        return String.format("%.2f", result);
    }
}
