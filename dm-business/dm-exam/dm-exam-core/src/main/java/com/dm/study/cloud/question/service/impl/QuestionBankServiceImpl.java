package com.dm.study.cloud.question.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dm.study.cloud.question.mapper.QuestionBankMapper;
import com.dm.study.cloud.question.param.QuestionBankParam;
import com.dm.study.cloud.question.po.QuestionBank;
import com.dm.study.cloud.question.service.QuestionBankService;
import com.dm.study.cloud.util.StrUtil;
import org.springframework.stereotype.Service;

/**
 * <p>标题：题库管理service实现类</p>
 * <p>功能：</p>
 * <pre>
 * 其他说明：
 * </pre>
 * <p>作者：lizh</p>
 * <p>审核：</p>
 * <p>重构：</p>
 * <p>创建日期：2022年07月05日 19:06</p>
 * <p>类全名：com.dm.study.cloud.question.service.impl.QuestionBankServiceImpl</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
@Service
public class QuestionBankServiceImpl extends ServiceImpl<QuestionBankMapper, QuestionBank> implements QuestionBankService {
    @Override
    public Page<QuestionBank> queryQuestionBank(QuestionBankParam param) {
        Page<QuestionBank> page = new Page<>(param.getPageNum(), param.getPageSize());
        // TODO 测试orders
        page.setOrders(OrderItem.descs("create_time"));
        QueryWrapper<QuestionBank> wrapper = new QueryWrapper<>();
        wrapper.like(StrUtil.isNotEmpty(param.getBankName()), "bank_name", param.getBankName());
        Page<QuestionBank> questionBankPage = getBaseMapper().selectPage(page, wrapper);
        return questionBankPage;
    }
}
