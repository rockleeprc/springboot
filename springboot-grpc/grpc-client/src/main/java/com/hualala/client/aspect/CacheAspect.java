package com.hualala.client.aspect;

import com.hualala.client.annotation.Caching;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

@Aspect
@Component
public class CacheAspect {

    @Pointcut(value = "execution(public * com.hualala.client.controller..*.*(..)) && @annotation(com.hualala.client.annotation.Caching)")
    public void pointCut() {
    }

    @Around("pointCut()")
    public Object around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        System.out.println("before around ");
        System.out.println("around ");

        Class<?> controllerClazz = proceedingJoinPoint.getTarget().getClass();
        MethodSignature signature = (MethodSignature) proceedingJoinPoint.getSignature();

        // 获取注解
        Caching caching = getCaching(controllerClazz, signature, Caching.class);
        System.out.println(caching.expire());

        // 获取参数
        Map<String, Object> params = getFields(signature.getParameterNames(), proceedingJoinPoint.getArgs());
        System.out.println(params);

        Object proceed = proceedingJoinPoint.proceed();
        System.out.println(proceed);
        System.out.println("after around ");
        return proceed;
    }

    private Map<String, Object> getFields(String[] parameterNames, Object[] args) {
        Map<String, Object> map = new HashMap<>();
        for (int i = 0; i < parameterNames.length; i++) {
            if (args[i] == null) continue;
            map.put(parameterNames[i], args[i]);
        }
        return map;
    }

    public <T extends Annotation> T getCaching(Class<?> targetClazz, MethodSignature signature, Class<T> clazz) throws NoSuchMethodException {
        Class[] parameterTypes = signature.getParameterTypes();
        Method targetMethod = targetClazz.getMethod(signature.getMethod().getName(), parameterTypes);
        return targetMethod.getAnnotation(clazz);
    }
}


