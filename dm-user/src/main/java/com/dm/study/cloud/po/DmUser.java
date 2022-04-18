package com.dm.study.cloud.po;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

/**
 * <p>标题：</p>
 * <p>功能：</p>
 * <pre>
 * 其他说明：
 * </pre>
 * <p>作者：lizh</p>
 * <p>审核：</p>
 * <p>重构：</p>
 * <p>创建日期：2022年04月18日 21:08</p>
 * <p>类全名：com.dm.study.cloud.po.DmUser</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@JsonInclude // 字段值为null时也序列化，加在类上所有字段生效，也可以单独加字段上
@TableName("dm_user")
public class DmUser extends BasePO {
    private static final long serialVersionUID = -8073239705877216992L;

    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    private Integer id;
    /**
     * 用户名
     */
    private String username;
    /**
     * 密码
     */
    @JSONField(serialize = false)
    @JsonIgnore // Redisson配置了JsonJacksonCodec序列化方式，序列化时使用此注解忽略该字段
    private String password;
    /**
     * 昵称
     */
    private String nickname;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 手机号
     */
    private String phone;
    /**
     * 性别
     */
    private int gender;
    /**
     * 头像
     */
    private String avatar;
    /**
     * 状态
     */
    @TableField(fill = FieldFill.INSERT)
    private String status;
}
