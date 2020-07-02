package com.example.demo.config.aspect.aop;


import cn.com.sj56.corgi.util.StringUtils;
import com.example.demo.config.annotation.PassLogger;
import com.example.demo.utils.NetworkUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;

@Component
@Aspect
@Order(5)
public class LogReqAndResAspect {

    private static final Logger logger = LogManager.getLogger(LogReqAndResAspect.class);

    private boolean hasPassLogger = false;
    private boolean passResponse = true;
    private boolean passRequest = true;

    ThreadLocal<Long> startTime = new ThreadLocal<Long>();

    // 不能拦截@RequestMapping，这样会导致swagger无法使用
    // @Pointcut("@annotation(org.springframework.web.bind.annotation.RequestMapping)")
    @Pointcut("execution(public * com.example.demo.web..*.*(..))")
    public void pointCutMethod() {
    }


    @Before("pointCutMethod()")
    public void doBefore(JoinPoint joinPoint) throws NoSuchMethodException {


        startTime.set(System.currentTimeMillis());

        // 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature)signature;
        Method targetMethod = methodSignature.getMethod();
        Method realMethod = joinPoint.getTarget().getClass().getDeclaredMethod(signature.getName(), targetMethod.getParameterTypes());

        // 检查是否有PassLogger注释，有则跳过记录日志
        if (!realMethod.isAnnotationPresent(PassLogger.class)) {
            hasPassLogger = false;
        } else {
            hasPassLogger = true;
            PassLogger passLogger = realMethod.getAnnotation(PassLogger.class);
            passRequest = passLogger.passRequest();
            passResponse = passLogger.passResponse();
        }

        String url = request.getRequestURL().toString();
        String method = request.getMethod();
        String uri = request.getRequestURI();

        if (!hasPassLogger || !passRequest) {

            // 记录下请求内容
            Enumeration<String> enums = request.getParameterNames();
            List<String> params = new ArrayList();
            while (enums.hasMoreElements()) {
                String paraName = enums.nextElement();
                String param = paraName + ":" + request.getParameter(paraName);
                params.add(param);
            }

            logger.info("请求开始>>>>>> URL: {}, Method: {}, Params: {}, IP: {} ", request.getRequestURL().toString(), request.getMethod(), params.toString(), NetworkUtils.getClientIp(request));
            logger.info("请求开始>>>>>> Class Method: {}, Args: {} ", joinPoint.getSignature().getDeclaringTypeName() + "."  + joinPoint.getSignature().getName(), Arrays.toString(joinPoint.getArgs()));

        }

        if (hasPassLogger && passRequest) {
            logger.info("请求开始>>>>>>", "跳过入参日志, 请求人id: " + uri + ",请求类型: " + method);
        }
    }

    @AfterReturning(returning = "ret", pointcut = "pointCutMethod()")
    public void doAfterReturning(Object ret) throws Throwable {
        // 处理完请求，返回内容
        logger.info("请求结束>>>>>>RESPONSE: {} " , ret);
        logger.info("请求结束>>>>>>SPEND TIME: {} ms", System.currentTimeMillis() - startTime.get());
    }
}
