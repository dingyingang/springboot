package com.example.demo.config.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Copyright © 2018 Shanghai Yejia Digital Technology Co., Ltd. All rights reserved.
 * 跳过记录入参日志
 *
 * @author yy
 * @date 2019/5/27
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface PassLogger {
    boolean passRequest() default true;
    boolean passResponse() default true;
}
