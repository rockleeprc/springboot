package com.example5.util;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

public class JsonUtils {
    private static final ObjectMapper mapper = new ObjectMapper();
//    ObjectMapper mapper = Jackson2ObjectMapperBuilder.json().build();

    static {
        // 美化输出
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
// 允许序列化空的POJO类 // （否则会抛出异常）
        mapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
        // 把java.util.Date, Calendar输出为数字（时间戳）
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        // 在遇到未知属性的时候不抛出异常
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        // 强制JSON 空字符串("")转换为null对象值:
        mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
        // 在JSON中允许C/C++ 样式的注释(非标准，默认禁用)
        mapper.configure(JsonParser.Feature.ALLOW_COMMENTS, true);
        // 允许没有引号的字段名（非标准）
        mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
        // 允许单引号（非标准）
        mapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
        // 强制转义非ASCII字符
        mapper.configure(JsonGenerator.Feature.ESCAPE_NON_ASCII, true);
        // 将内容包裹为一个JSON属性，属性名由@JsonRootName注解指定
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, true);

    }

    public static String toJson(Object obj) throws Exception {
        return mapper.writeValueAsString(obj);
    }
}
