package com.dm.study.cloud.question.controller;

import com.dm.study.cloud.question.param.QuestionBankParam;
import com.dm.study.cloud.question.service.QuestionBankService;
import com.dm.study.cloud.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>标题：题库管理controller</p>
 * <p>功能：</p>
 * <pre>
 * 其他说明：
 * </pre>
 * <p>作者：lizh</p>
 * <p>审核：</p>
 * <p>重构：</p>
 * <p>创建日期：2022年07月05日 19:03</p>
 * <p>类全名：com.dm.study.cloud.question.controller.QuestionBankController</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
@RestController
@RequestMapping("/exam/questionBank")
public class QuestionBankController {
    @Autowired
    QuestionBankService service;

    @RequestMapping("/queryQuestionBank")
    public Result queryQuestionBank(QuestionBankParam param) {
        return Result.success("查询成功！", service.queryQuestionBank(param));
    }
}
