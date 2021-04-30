package cn.kd.common;

import java.io.Serializable;


public class Result<T> implements Serializable {

    private Integer code;

    private String message;

    private T data;

    private Result() {
    }

    private Result(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    private static <T> Result<T> result(Code code, T data) {
        Result<T> result = new Result(code.getCode(), code.getMessage(), data);
        return result;
    }

    public static <T> Result<T> success() {
        return success(null);
    }

    public static <T> Result<T> success(T data) {
        return result(SystemCode.SUCCESS, data);
    }

    public static <T> Result<T> failure(Code code) {
        return failure(code, null);
    }

    public static <T> Result<T> failure(Code code, T data) {
        return result(code, data);
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
