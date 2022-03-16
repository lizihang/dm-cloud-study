package com.dm.study.cloud.vo;

import com.dm.study.cloud.constant.Constants;

import java.io.Serializable;

/**
 * <p>标题：</p>
 * <p>功能：</p>
 * <pre>
 * 其他说明：
 * </pre>
 * <p>作者：lizh</p>
 * <p>审核：</p>
 * <p>重构：</p>
 * <p>创建日期：2022年03月15日 16:48</p>
 * <p>类全名：com.dm.study.cloud.vo.Result</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
public class Result implements Serializable {
    private static final long serialVersionUID = -2491020297441477914L;
    /**
     * 状态
     */
    private int status;
    /**
     * 消息
     */
    private String msg;
    /**
     * 数据
     */
    private Object data;

    private Result() {
    }

    private Result(int status, String msg, Object data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    public static Result success(String msg) {
        return success(msg, null);
    }

    public static Result success(String msg, Object data) {
        return new Result(Constants.HTTP_STATUS_OK_VALUE, msg, data);
    }

    public static Result error(int errorCode, String msg) {
        return error(errorCode, msg, null);
    }

    public static Result error(int errorCode, String msg, Object data) {
        return new Result(errorCode, msg, data);
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
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
