package cn.kd.exception;

import cn.kd.common.Result;
import cn.kd.common.SystemCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;


/**
 * 统一异常处理
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);


    /**
     * 处理空指针的异常
     *
     * @return
     */
    @ExceptionHandler(NullPointerException.class)
    public Result<?> nullPointerException(NullPointerException exception, HttpServletRequest request) {
        logger.error("exception cause {},path {}", exception.getClass(), request.getRequestURI(), exception);
        return Result.failure(SystemCode.INTERNAL_SERVER_ERROR, exception.getClass());
    }

    @ExceptionHandler(ArithmeticException.class)
    public Result<?> arithmeticException(ArithmeticException exception, HttpServletRequest request) {
        logger.error("exception cause {},path {}", exception.getClass(), request.getRequestURI(), exception);
        return Result.failure(SystemCode.INTERNAL_SERVER_ERROR, exception.getClass());
    }


    /**
     * 处理其他异常
     *
     * @return
     */
    @ExceptionHandler(Exception.class)
    public Result<?> exception(Exception exception, HttpServletRequest request) {
        logger.error("exception cause {},path {}", exception.getClass(), request.getRequestURI(), exception);
        return Result.failure(SystemCode.INTERNAL_SERVER_ERROR, exception.getClass());
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public Result<?> constraintViolationException(ConstraintViolationException exception) {
        Set<ConstraintViolation<?>> violations = exception.getConstraintViolations();
        Map<String, String> errorMap = new HashMap<>();
        for (ConstraintViolation violation : violations) {
            errorMap.put(violation.getPropertyPath().toString(), violation.getMessage());
        }
        return Result.failure(SystemCode.BAD_REQUEST, errorMap);
    }
}
