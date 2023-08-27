package x.y.z.controller;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import x.y.z.exception.BusinessException;
import x.y.z.exception.SystemException;

// 异常处理器
@RestControllerAdvice
public class ProjectExceptionAdvice {

    @ExceptionHandler(BusinessException.class)
    public Result doSystemException(BusinessException exception){
        System.out.println(exception.getMessage());
        return new Result(null,Code.FAIL,exception.getMessage());
    }

    @ExceptionHandler(SystemException.class)
    public Result doSystemException(SystemException exception){
        System.out.println(exception.getMessage());
        return new Result(null,Code.FAIL,exception.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public Result doException(Exception exception){
        System.out.println(exception.getMessage());
        return new Result(null,Code.FAIL,exception.getMessage());
    }
}
