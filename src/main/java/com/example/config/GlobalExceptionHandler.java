package com.example.config;

import com.example.exception.BaseException;
import com.example.exception.ErrorInfo;
import org.apache.shiro.ShiroException;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.UnauthorizedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;

/**
 * 统一捕捉shiro的异常，返回给前台一个Object信息，前台根据这个信息显示对应的提示，或者做页面的跳转。
 */
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);
    private static final String DEFAULT_RUNTIME_EXCEPTION_ALERT_MESSAGE = "系统错误";
    private static final Integer DEFAULT_RUNTIME_EXCEPTION_ALERT_CODE = 500;

    @ExceptionHandler(ShiroException.class)
    @ResponseBody
    public Object handleShiroException(ShiroException e) {
        String eName = e.getClass().getSimpleName();
        log.error("shiro执行出错：{}", eName);
        return "shiro执行出错：鉴权或授权过程出错";
    }

    @ExceptionHandler(UnauthenticatedException.class)
    @ResponseBody
    public Object page401() {
        return "shiro执行出错：用户未登录";
    }

    @ExceptionHandler(UnauthorizedException.class)
    @ResponseBody
    public Object page403() {
        return "shiro执行出错：用户没有访问权限";
    }

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ErrorInfo jsonHandler(HttpServletRequest request, Exception exception) throws Exception {
        ErrorInfo errorInfo = new ErrorInfo();
        if (exception instanceof BaseException) {
            BaseException baseException = (BaseException) exception;
            errorInfo.setMessage(baseException.getMessage());
            errorInfo.setCode(baseException.getCode());
            errorInfo.setUrl(request.getRequestURL().toString());
        } else {
            exception.printStackTrace();
            errorInfo.setMessage(DEFAULT_RUNTIME_EXCEPTION_ALERT_MESSAGE);
            errorInfo.setCode(DEFAULT_RUNTIME_EXCEPTION_ALERT_CODE);
            errorInfo.setUrl(request.getRequestURL().toString());
            log.warn("a RuntimeException happened ,the exception is {} ,and the requestUrl is ({})",
                    exception.getStackTrace(), errorInfo.getUrl());
        }

        return errorInfo;
    }
}