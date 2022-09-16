package com.dm.study.cloud.aspect;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

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
 * <p>创建日期：2022年05月03日 17:00</p>
 * <p>类全名：com.dm.study.cloud.aspect.DmLogAspect</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
@Aspect
@Component
public class DmLogAspect {
	private static final Logger logger         = LoggerFactory.getLogger(DmLogAspect.class);
	@Value("${spring.application.name}")
	private              String applicationName;
	/**
	 * 换行符
	 */
	private static final String LINE_SEPARATOR = System.lineSeparator();

	/**
	 * 以自定义 @DmLog 注解为切点
	 */
	@Pointcut("@annotation(com.dm.study.cloud.annotation.DmLog)")
	public void logPointcut() {
	}

	/**
	 * 在切点之前织入
	 * @param joinPoint
	 * @throws Throwable
	 */
	@Before("logPointcut()")
	public void doBefore(JoinPoint joinPoint) throws Throwable {
	}

	/**
	 * 在切点之后织入
	 * @throws Throwable
	 */
	@After("logPointcut()")
	public void doAfter() throws Throwable {
	}

	/**
	 * 环绕通知
	 * @param joinPoint 切入点
	 * @return result
	 * @throws Throwable 异常
	 */
	@Around("logPointcut()")
	public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
		long startTime = System.currentTimeMillis();
		// 开始打印请求日志
		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = attributes.getRequest();
		//
		StringBuilder sb = new StringBuilder();
		sb.append(LINE_SEPARATOR);
		// 打印请求相关参数
		sb.append("========================================== Start ==========================================").append(LINE_SEPARATOR);
		// 服务名称
		sb.append("Application Name  : ").append(applicationName).append(LINE_SEPARATOR);
		// 打印请求 url
		sb.append("URL               : ").append(request.getRequestURL().toString()).append(LINE_SEPARATOR);
		// 打印 Http method
		sb.append("HTTP Method       : ").append(request.getMethod()).append(LINE_SEPARATOR);
		// 打印调用 controller 的全路径以及执行方法
		sb.append("Class Method      : ").append(joinPoint.getSignature().getDeclaringTypeName()).append(".").append(joinPoint.getSignature().getName()).append(LINE_SEPARATOR);
		// 打印请求的 IP
		sb.append("IP                : ").append(request.getRemoteAddr()).append(LINE_SEPARATOR);
		// 打印请求入参
		sb.append("Request Args      : ").append(getArgsJsonString(joinPoint)).append(LINE_SEPARATOR);
		Object result = joinPoint.proceed();
		// 打印出参
		String jsonResult = JSON.toJSONString(result);
		sb.append("Response Args     : ").append(jsonResult).append(LINE_SEPARATOR);
		// 执行耗时
		sb.append("Time-Consuming    : ").append(System.currentTimeMillis() - startTime).append(" ms").append(LINE_SEPARATOR);
		sb.append("=========================================== End ===========================================").append(LINE_SEPARATOR);
		logger.info(sb.toString());
		return result;
	}

	/**
	 * 异常通知
	 * @param joinPoint join point for advice
	 * @param e exception
	 */
	@AfterThrowing(pointcut = "logPointcut()", throwing = "e")
	public void doAfterThrowing(JoinPoint joinPoint, Throwable e) {
		logger.error(e.getMessage());
	}

	/**
	 * 对一些不能直接转json的参数进行特殊处理
	 * @param joinPoint
	 * @return
	 */
	private String getArgsJsonString(JoinPoint joinPoint) {
		try {
			return JSON.toJSONString(joinPoint.getArgs());
		} catch (JSONException e) {
			return getArgsType(joinPoint.getArgs());
		}
	}

	/**
	 * @param args
	 * @return
	 */
	private String getArgsType(Object[] args) {
		String str = "";
		for (Object arg : args) {
			String typeName = arg.getClass().getTypeName();
			String paramName = arg.getClass().getName();
			str += "请求参数名<" + paramName + ">，请求参数类型<" + typeName + ">;";
			System.out.println(str);
		}
		return str;
	}
}
