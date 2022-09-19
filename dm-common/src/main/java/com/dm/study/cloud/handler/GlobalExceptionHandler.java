package com.dm.study.cloud.handler;

import com.dm.study.cloud.exception.DmException;
import com.dm.study.cloud.vo.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
/**
 * <p>标题：</p>
 * <p>功能：</p>
 * <pre>
 * 其他说明：
 * </pre>
 * <p>作者：lizh</p>
 * <p>审核：</p>
 * <p>重构：</p>
 * <p>创建日期：2022年09月16日 17:29</p>
 * <p>类全名：com.dm.study.cloud.handler.GlobalExceptionHandler</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
@ConditionalOnClass(javax.servlet.ServletException.class) // 解决spring cloud gateway报错
@RestControllerAdvice
public class GlobalExceptionHandler {
	private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	/**
	 * 处理自定义异常
	 * @param exception
	 * @return
	 */
	@ExceptionHandler(DmException.class)
	public Result baseException(DmException exception) {
		logger.error("自定义异常！错误信息：" + exception.getMessage());
		return Result.error(HttpStatus.INTERNAL_SERVER_ERROR.value(), exception.getMessage());
	}

	/**
	 * 处理空指针的异常
	 * @param req
	 * @param exception
	 * @return
	 */
	@ExceptionHandler(value = NullPointerException.class)
	public Result nullPointerExceptionHandler(HttpServletRequest req, NullPointerException exception) {
		logger.error("发生空指针异常！原因是:", exception);
		return Result.error(HttpStatus.INTERNAL_SERVER_ERROR.value(), "发生空指针异常！");
	}

	/**
	 * 处理系统内部异常
	 * @param exception
	 * @return
	 */
	@ExceptionHandler(Exception.class)
	public Result exceptionHandler(Exception exception) {
		logger.error("服务发生异常！原因是:", exception);
		return Result.error(HttpStatus.INTERNAL_SERVER_ERROR.value(), "系统内部错误！");
	}
}
