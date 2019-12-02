package com.yzj.cep.common.pojo.vo;

import com.yzj.cep.common.pojo.constant.ResponseEnum;

public class ResponseVO {
    private String code;
    private String msg;
    private String status;
    private Object data;

    public ResponseVO() {
    }

    public ResponseVO(ResponseEnum responseEnum) {
        this.code = responseEnum.getCode();
        this.msg = responseEnum.getMsg();
        this.status = responseEnum.getStatus();
    }

    public ResponseVO(ResponseEnum responseEnum,Object data) {
        this.code = responseEnum.getCode();
        this.msg = responseEnum.getMsg();
        this.status = responseEnum.getStatus();
        this.data = data;
    }

    public static ResponseVO genOkResponse() {
        return new ResponseVO(ResponseEnum.OK);
    }

    public static ResponseVO genOkResponse(Object data) {
        return new ResponseVO(ResponseEnum.OK,data);
    }

    public static ResponseVO genErrorResponse() {
        return new ResponseVO(ResponseEnum.ERROR);
    }

    public static ResponseVO genUnauthResponse() {
        return new ResponseVO(ResponseEnum.UN_AUTH);
    }

    public static ResponseVO genBadRequestResponse() {
        return new ResponseVO(ResponseEnum.BAD_REQUEST);
    }

    public static ResponseVO genMethodNotAllowedResponse() {
        return new ResponseVO(ResponseEnum.METHOD_NOT_ALLOWED);
    }

    public static ResponseVO genExpireResponse() {
        return new ResponseVO(ResponseEnum.EXPIRE);
    }

    public static ResponseVO genIllegalRequestResponse() {
        return new ResponseVO(ResponseEnum.ILLEGAL_REQUEST);
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public String getStatus() {
        return status;
    }

    public Object getData() {
        return data;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
