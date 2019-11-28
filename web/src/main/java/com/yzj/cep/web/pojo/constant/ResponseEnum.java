package com.yzj.cep.web.pojo.constant;

public enum ResponseEnum {

    OK("100200","ok","成功"),
    BAD_REQUEST("100400","badRequest","请求错误"),
    UN_AUTH("100401","unAuth","未登录"),
    NO_PERMISSION("100403","noPermission","无权限"),
    EXPIRE("100406","expire","登录过期"),
    ILLEGAL_REQUEST("100407","illegalRequest","非法token"),
    ERROR("100500","error","服务器异常");

    private String code;
    private String status;
    private String msg;


    ResponseEnum(String code, String status, String msg) {
        this.code = code;
        this.status = status;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public String getStatus() {
        return status;
    }

    public String getMsg() {
        return msg;
    }
}
