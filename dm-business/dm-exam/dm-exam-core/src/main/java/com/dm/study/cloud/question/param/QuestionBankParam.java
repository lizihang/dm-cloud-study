package com.dm.study.cloud.question.param;

import com.dm.study.cloud.vo.QueryParams;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>标题：题库管理查询参数</p>
 * <p>功能：</p>
 * <pre>
 * 其他说明：
 * </pre>
 * <p>作者：lizh</p>
 * <p>审核：</p>
 * <p>重构：</p>
 * <p>创建日期：2022年07月05日 19:13</p>
 * <p>类全名：com.dm.study.cloud.question.param.QuestionBankParam</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
@Getter
@Setter
public class QuestionBankParam extends QueryParams {
    private static final long serialVersionUID = -8391787713613064992L;
    /** 题库名称 */
    private String bankName;
}
