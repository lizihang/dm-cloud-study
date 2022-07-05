package com.dm.study.cloud.question.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dm.study.cloud.question.param.QuestionBankParam;
import com.dm.study.cloud.question.po.QuestionBank;

/**
 * <p>标题：题库管理service</p>
 * <p>功能：</p>
 * <pre>
 * 其他说明：
 * </pre>
 * <p>作者：lizh</p>
 * <p>审核：</p>
 * <p>重构：</p>
 * <p>创建日期：2022年07月05日 19:04</p>
 * <p>类全名：com.dm.study.cloud.question.service.QuestionBankService</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
public interface QuestionBankService extends IService<QuestionBank> {
    /**
     * 查询题库分页信息
     * @param param
     * @return
     */
    Page<QuestionBank> queryQuestionBank(QuestionBankParam param);
}
