package com.example.example.config;

import org.apache.shiro.ShiroException;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.UnauthorizedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * 统一捕捉shiro的异常，返回给前台一个Object信息，前台根据这个信息显示对应的提示，或者做页面的跳转。
 */
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(ShiroException.class)
    @ResponseBody
    public Object handleShiroException(ShiroException e) {
        String eName = e.getClass().getSimpleName();
        log.error("shiro执行出错：{}",eName);
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

}