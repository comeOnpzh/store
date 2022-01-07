package com.example.store.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * Created by pengzh5 Cotter on 2022/1/5.
 * 方法执行时长统计
 */
@Component
@Aspect
public class TimeAspect {
    @Around("execution(* com.example.store.service.impl.*.*(..))")
    public Object around(ProceedingJoinPoint pdj) throws Throwable {
        long start = System.currentTimeMillis();
        System.out.println(pdj.getTarget());       //这个可以获得目标方法
        Object result = pdj.proceed();        //执行目标方法，会返回一个object的值
        long end = System.currentTimeMillis();
        System.out.println(end-start);
        return result;
    }
}
