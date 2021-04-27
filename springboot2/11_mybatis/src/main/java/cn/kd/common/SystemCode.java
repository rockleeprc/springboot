package cn.kd.common;


public enum SystemCode implements Code {
    // 系统级错误码
    SUCCESS(200, "成功!"),
    BAD_REQUEST(400, "请求的数据格式不符!"),
    UNAUTHORIZED(401, "请求的数字签名不匹配!"),
    NOT_FOUND(404, "未找到该资源!"),
    INTERNAL_SERVER_ERROR(500, "服务器内部错误!"),
    SERVER_BUSY(503, "服务器正忙，请稍后再试!");

    private Integer code;

    private String message;

    SystemCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }


    @Override
    public Integer getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
