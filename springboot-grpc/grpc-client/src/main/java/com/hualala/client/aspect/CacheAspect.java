package com.hualala.client.aspect;

import com.hualala.client.annotation.Caching;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.*;
import java.util.stream.Collectors;

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
        String controllerFullName = controllerClazz.getName();
        MethodSignature signature = (MethodSignature) proceedingJoinPoint.getSignature();

        // 获取注解
        Caching caching = getCaching(controllerClazz, signature, Caching.class);
        System.out.println(caching.expire());

        // 获取参数
        Map<String, Object> params = getRequestParams(signature.getParameterNames(), proceedingJoinPoint.getArgs());
        String param = requestParamToString(params);
        System.out.println(param);

        Object proceed = proceedingJoinPoint.proceed();
        System.out.println(proceed);
        System.out.println("after around ");
        return proceed;
    }

    private String requestParamToString(Map<String, Object> params) {
        StringBuilder sb = new StringBuilder();
        Set<Map.Entry<String, Object>> entries = params.entrySet();
        for (Map.Entry<String, Object> entry : entries) {
            String key = entry.getKey();
            Object value = entry.getValue();
            if (value == null) continue;

            Method[] declaredMethods = value.getClass().getDeclaredMethods();
            int separatorIndex = 0;
            for (Method method : declaredMethods) {
                String methodName = method.getName();
                int modifiers = method.getModifiers();
                Class<?> returnType = method.getReturnType();

                if (methodName.startsWith("get") // getter
                        && Modifier.isPublic(modifiers) // public method
                        && returnType != Void.TYPE // return !void
                ) {
                    Object returnValue = ReflectionUtils.invokeMethod(method, value);
                    if (returnValue == null) continue; //getter返回值null不处理
                    if (separatorIndex != 0) sb.append("&");
                    sb.append(methodName).append("=");

                    Class<?> returnValueClazz = returnValue.getClass();
                    if (List.class.isAssignableFrom(returnValueClazz)) {
                        List list = (List) returnValue;
                        list.sort(Comparator.naturalOrder());
                        appendContent(list, sb);
                    }
                    if (Set.class.isAssignableFrom(returnValueClazz)) {
                        Set set = (Set) returnValue;
                        appendContent(set, sb);
                    }
                    if (Map.class.isAssignableFrom(returnValueClazz)) {
                        Map map = (Map) returnValue;
                        appendContent(map, sb);
                    }

                    if (returnValueClazz.isArray()) {
                        Object[] objects = (Object[]) returnValue;
                        Comparator com = Comparator.naturalOrder();
                        List list = (List) Arrays.stream(objects).sorted(com).collect(Collectors.toList());
                        appendContent(list, sb);
                    }
                    separatorIndex++;
                }
            }
        }
        return sb.toString();
    }


    private void appendContent(Map map, StringBuilder sb) {
        Set<Map.Entry> set = map.entrySet();
        int separatorIndex = 0;
        for (Map.Entry entry : set) {
            if (separatorIndex == 0) sb.append("[");
            if (separatorIndex != 0) sb.append(",");
            sb.append(entry.getKey());
            sb.append(":");
            sb.append(entry.getValue());
            separatorIndex++;
        }
        sb.append("]");
    }

    private void appendContent(Iterable iterable, StringBuilder sb) {
        int separatorIndex = 0;
        for (Object obj : iterable) {
            if (separatorIndex != 0) sb.append(",");
            sb.append(obj);
            separatorIndex++;
        }
    }

    private Map<String, Object> getRequestParams(String[] parameterNames, Object[] args) {
        Map<String, Object> map = new HashMap<>();
        for (int i = 0; i < parameterNames.length; i++) {
            // if (args[i] == null) continue; // 外层判断，保留原始数据的样子
            map.put(parameterNames[i], args[i]);
        }
        return map;
    }

    public <T extends Annotation> T getCaching(Class<?> targetClazz, MethodSignature
            signature, Class<T> clazz) throws NoSuchMethodException {
        Class[] parameterTypes = signature.getParameterTypes();
        Method targetMethod = targetClazz.getMethod(signature.getMethod().getName(), parameterTypes);
        return targetMethod.getAnnotation(clazz);
    }
}


