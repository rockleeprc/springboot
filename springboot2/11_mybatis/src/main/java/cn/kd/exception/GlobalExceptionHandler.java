package cn.kd.exception;

import cn.kd.common.Result;
import cn.kd.common.SystemCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;


/**
 * 统一异常处理
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);


    /**
     * 处理空指针的异常
     *
     * @return
     */
    @ExceptionHandler(value = NullPointerException.class)
    @ResponseBody
    public Result<?> exceptionHandler(HttpServletRequest req, NullPointerException exception) {
        logger.error("发生空指针异常，原因是:", exception);
        // TODO 定义npe code、 删掉HttpServletRequest
        return Result.error(SystemCode.INTERNAL_SERVER_ERROR);
    }


    /**
     * 处理其他异常
     *
     * @return
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result<?> exceptionHandler(Exception exception) {
        logger.error("未知异常！原因是:", exception);
        return Result.error(SystemCode.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = ConstraintViolationException.class)
    @ResponseBody
    public Result<?> constraintViolationException(ConstraintViolationException exception) {
        Set<ConstraintViolation<?>> violations = exception.getConstraintViolations();
        Map<String, String> errorMap = new HashMap<>();
        for (ConstraintViolation violation : violations) {
            errorMap.put(violation.getPropertyPath().toString(), violation.getMessage());
        }
        return Result.error(SystemCode.BAD_REQUEST, errorMap);
    }
}
