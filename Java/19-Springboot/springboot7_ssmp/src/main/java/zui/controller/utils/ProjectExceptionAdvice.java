package zui.controller.utils;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

//@ControllerAdvice
@RestControllerAdvice
public class ProjectExceptionAdvice {

//    拦截所有的异常信息
    @ExceptionHandler
    public Result doException(Exception exception){
        //记录日志， 通知运维，通知开发
        exception.printStackTrace();
        return new Result(false, "服务器故障，请稍后再试");

    }
}
