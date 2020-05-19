package com.example5.config;

import com.example5.util.JsonUtils;
import com.example5.util.ValidUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.DefaultParameterNameDiscoverer;
import org.springframework.core.MethodParameter;
import org.springframework.core.ParameterNameDiscoverer;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.lang.reflect.Parameter;
import java.util.*;

public class ValidParamsInterceptor extends HandlerInterceptorAdapter {
    private static Logger LOG = LoggerFactory.getLogger(ValidParamsInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!(handler instanceof HandlerMethod)) {
            LOG.warn("UnSupport handler");
            return true;
        }

        String message = validateParameter(request, (HandlerMethod) handler);
        if (message != null && message.trim().length() > 0) {
            Map<String, String> result = new HashMap<>();
            result.put("code", "参数错误");
            result.put("message", message);
            response.setHeader("Content-type", "application/json;charset=UTF-8");
            response.getWriter().write(JsonUtils.toJson(result));
            return false;
        }
        /*
        //拿到该方法上加了注解的参数名称
        List<String> list = getParameterNames((HandlerMethod) handler);
        for (String param : list) {
            //获取到参数名称并判断是否为空
            String parameter = request.getParameter(param);
            if (parameter == null || "".equals(parameter.trim())) {
                // 异常的统一处理
                Map<String, String> result = new HashMap<>();
                result.put("code", "参数错误");
                result.put("message", param + " 参数不能为null");
                response.setHeader("Content-type", "application/json;charset=UTF-8");
                response.getWriter().write(JsonUtils.obj2json(result));
                return false;
            }
        }
         */
        return true;
    }

    private String validateParameter(HttpServletRequest request, HandlerMethod handlerMethod) {
        MethodParameter[] methodParameters = handlerMethod.getMethodParameters();
        ParameterNameDiscoverer parameterNameDiscoverer = new DefaultParameterNameDiscoverer();
        StringBuilder sb = new StringBuilder();

        for (MethodParameter methodParameter : methodParameters) {
            methodParameter.initParameterNameDiscovery(parameterNameDiscoverer);
            Parameter parameter = methodParameter.getParameter();
            String parameterName = methodParameter.getParameterName();
            String value = request.getParameter(parameterName);

            if (parameter.isAnnotationPresent(NotNull.class)
                    && ValidUtils.isNull(value)) {
                sb.append(ValidUtils.isNullMessage(parameterName));
            }

            if (parameter.isAnnotationPresent(NotEmpty.class)
                    && (ValidUtils.isEmpty(value))) {
                sb.append(ValidUtils.isEmptyMessage(parameterName));
            }

            if (parameter.isAnnotationPresent(Email.class)
                    && (ValidUtils.isEmail(value))) {
            }

            if (parameter.isAnnotationPresent(Size.class)
            && (ValidUtils.isSize(value))) {
            }
        }
        return sb.toString();
    }

    private List<String> getParameterNames(HandlerMethod handlerMethod) {
        MethodParameter[] methodParameters = handlerMethod.getMethodParameters();
        ParameterNameDiscoverer parameterNameDiscoverer = new DefaultParameterNameDiscoverer();
        List<String> paramNames = new ArrayList<>();
        for (MethodParameter methodParameter : methodParameters) {
            Parameter parameter = methodParameter.getParameter();
            methodParameter.initParameterNameDiscovery(parameterNameDiscoverer);
            System.out.println("----" + methodParameter.getParameterName());
            if (parameter.isAnnotationPresent(NotNull.class)) {
                paramNames.add(methodParameter.getParameterName());
            }
            if (parameter.isAnnotationPresent(NotEmpty.class)) {
                paramNames.add(methodParameter.getParameterName());
            }
        }
        return paramNames;
    }
}
