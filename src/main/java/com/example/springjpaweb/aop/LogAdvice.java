package com.example.springjpaweb.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class LogAdvice {

    @Around("execution(* com.example.springjpaweb.service.*.*(..))")
    public Object myAroundAdvice(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println(">>>>>>>>>>>>>>>>>>Advice : " + pjp.getSignature());
        System.out.println(Arrays.toString(pjp.getArgs()));
        long timestamp1 = System.nanoTime();
        Object o = pjp.proceed();
        long timestamp2 = System.nanoTime();
        System.out.println("Elapsed ms: " + (timestamp2-timestamp1)/1000000);
        return o;
    }
}
