package com.dm.study.cloud.aspect;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private static final Logger logger = LoggerFactory.getLogger(DmLogAspect.class);
    /** 换行符 */
    private static final String LINE_SEPARATOR = System.lineSeparator();
    ThreadLocal<Long> startTime = new ThreadLocal<>();

    /** 以自定义 @DmLog 注解为切点 */
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
        // 开始打印请求日志
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        // 打印请求相关参数
        logger.info("========================================== Start ==========================================");
        // 打印请求 url
        logger.info("URL            : {}", request.getRequestURL().toString());
        // 打印 Http method
        logger.info("HTTP Method    : {}", request.getMethod());
        // 打印调用 controller 的全路径以及执行方法
        logger.info("Class Method   : {}.{}", joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName());
        // 打印请求的 IP
        logger.info("IP             : {}", request.getRemoteAddr());
        // 打印请求入参
        logger.info("Request Args   : {}", getArgsJsonString(joinPoint));
    }

    /**
     * 在切点之后织入
     * @throws Throwable
     */
    @After("logPointcut()")
    public void doAfter() throws Throwable {
        // 接口结束后换行，方便分割查看
        // logger.info("=========================================== End ===========================================" + LINE_SEPARATOR);
    }

    /**
     * 环绕
     * @param proceedingJoinPoint
     * @return
     * @throws Throwable
     */
    @Around("logPointcut()")
    public Object doAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        startTime.set(System.currentTimeMillis());
        Object result = proceedingJoinPoint.proceed();
        // 打印出参
        String jsonResult = JSON.toJSONString(result);
        logger.info("Response Args  : {}", jsonResult);
        // 执行耗时
        logger.info("Time-Consuming : {} ms", System.currentTimeMillis() - startTime.get());
        logger.info("=========================================== End ===========================================" + LINE_SEPARATOR);
        startTime.remove();
        return result;
    }

    /**
     * 异常通知
     * @param joinPoint join point for advice
     * @param e         exception
     */
    @AfterThrowing(pointcut = "logPointcut()", throwing = "e")
    public void doAfterThrowing(JoinPoint joinPoint, Throwable e) {
        logger.error(e.getMessage());
        startTime.remove();
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
