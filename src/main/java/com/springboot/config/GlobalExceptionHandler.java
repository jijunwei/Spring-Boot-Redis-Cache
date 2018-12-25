package com.springboot.config;


import com.springboot.model.json.JsonResult;
import org.apache.shiro.ShiroException;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.UnauthorizedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 公共异常处理器
 *
 * @author skyer
 * @date 2018/8/22 10:06
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    final static Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    // 捕捉ShiroException
    @ExceptionHandler(ShiroException.class)
    public JsonResult shiroException(ShiroException e) {
        return JsonResult.buildErrorStateResult(e.getMessage());
    }

    // 捕捉AuthenticationException
    @ExceptionHandler(AuthenticationException.class)
    public JsonResult authenticationException(Throwable ex) {
        return JsonResult.buildErrorStateResult("认证信息无效，请重新登录");
    }

    // 捕捉UnauthorizedException
    @ExceptionHandler(UnauthorizedException.class)
    public JsonResult unauthorizedException(Throwable ex) {
        return JsonResult.buildErrorStateResult("您无权限操作");
    }

    // 捕捉UnauthenticatedException
    @ExceptionHandler(UnauthenticatedException.class)
    public JsonResult unauthenticatedException(Throwable ex) {
        return JsonResult.buildErrorStateResult("认证信息无效，请重新登录");
    }



    // 捕捉其他所有异常
    @ExceptionHandler(Exception.class)
    public JsonResult globalException(Throwable ex) {
        ex.printStackTrace();
        return JsonResult.buildErrorStateResult("系统异常,请稍后重试");
    }
}
