package com.dm.study.cloud.exception;

/**
 * <p>标题：</p>
 * <p>功能：</p>
 * <pre>
 * 其他说明：
 * </pre>
 * <p>作者：lizh</p>
 * <p>审核：</p>
 * <p>重构：</p>
 * <p>创建日期：2022年05月03日 16:42</p>
 * <p>类全名：com.dm.study.cloud.exception.DmException</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
public class DmException extends RuntimeException {

    private static final long serialVersionUID = -3474075755830353788L;

    public DmException() {
    }

    public DmException(String message) {
        super(message);
    }

    public DmException(String message, Throwable cause) {
        super(message, cause);
    }

    public DmException(Throwable cause) {
        super(cause);
    }

    public DmException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
