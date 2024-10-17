package com.dm.study.cloud.liteflow.web;

import lombok.Data;
/**
 * @author jiangtao
 * @create 2022/10/2 20:13
 */
@Data
public class Result<T> {
    private boolean success = true;
    private String message = "执行成功！";
    private Integer code = 0;
    private T data;
    private long timestamp = System.currentTimeMillis();

    public static <T> Result<T> success(T data) {
        Result<T> r = new Result<>();
        r.setData(data);
        return r;
    }

    public static <T> Result<T> success() {
        return new Result<>();
    }

    public static <T> Result<T> error(String msg, T data) {
        Result<T> r = new Result<>();
        r.setSuccess(false);
        r.setCode(-1);
        r.setMessage(msg);
        r.setData(data);
        return r;
    }
}

