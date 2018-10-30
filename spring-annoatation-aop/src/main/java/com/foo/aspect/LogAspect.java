package com.foo.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;

@Aspect
public class LogAspect {

    @Pointcut("execution(public Integer com.foo.service.CalcService.*(..))")
    public void pointCut() {
    }

    @Before("pointCut()")
    public void before(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        System.out.println("LogAspect.before..." + methodName);
    }

    @AfterReturning(value = "pointCut()", returning = "result")
    public void afterReturning(JoinPoint joinPoint, Integer result) {
        String methodName = joinPoint.getSignature().getName();
        System.out.println("LogAspect.before..." + methodName + " 返回值:" + result);
    }

    @After("com.foo.aspect.LogAspect.pointCut()")
    public void after(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        System.out.println("LogAspect.after..." + methodName);
    }

    @Around("pointCut()")
    public void around(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("before around ");
        System.out.println("around "+pjp.getTarget().getClass());
        Object[] args = pjp.getArgs();
        System.out.println("around "+args[0]);
        pjp.proceed();
        System.out.println("after around ");
    }


    @AfterThrowing(value = "pointCut()", throwing = "exception")
    public void afterThrowing(JoinPoint joinPoint, Exception exception) {
        String methodName = joinPoint.getSignature().getName();
        System.out.println("LogAspect.before..." + methodName+" exception:"+exception.getMessage());
    }
}
