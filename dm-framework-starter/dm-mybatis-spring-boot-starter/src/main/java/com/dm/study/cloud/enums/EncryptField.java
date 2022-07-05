package com.dm.study.cloud.enums;

/**
 * <p>标题：加密存储字段枚举</p>
 * <p>功能：</p>
 * <pre>
 * 其他说明：
 * </pre>
 * <p>作者：lizh</p>
 * <p>审核：</p>
 * <p>重构：</p>
 * <p>创建日期：2022年06月14日 8:58</p>
 * <p>类全名：com.dm.study.cloud.enums.EncryptField</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
public enum EncryptField {
    MOBILE("phone"),
    EMAIL("email");
    private final String code;

    EncryptField(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
