package com.lidong.auth.comm;


public class RestResponse {
    private long code;
    private String msg;
    private Object data;

    public RestResponse(long code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public RestResponse(long code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public RestResponse() {
    }

    public long getCode() {
        return code;
    }

    public void setCode(long code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
