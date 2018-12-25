package com.springboot.controller;

import com.springboot.mapper.UserDao;
import com.springboot.model.auth.JwtUtil;
import com.springboot.model.user.AuthUser;
import com.springboot.util.BeanValidators;
import com.springboot.util.RedisUtil;
import io.jsonwebtoken.Claims;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

import javax.annotation.Resource;
import javax.validation.Validator;
import java.util.Date;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 *
 * @time 2017-09-23 10:19
 */
public class BaseController implements IBaseController {
    private static final Logger LOGGER = LoggerFactory.getLogger(BaseController.class);
    @Resource
    private JwtUtil jwtUtil;
    @Resource
    private RedisUtil redisUtil;

    @Autowired
    protected Validator validator;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private UserDao userDao;

    public AuthUser getCurrentUser() {
        /*String token = getRequest().getHeader("x-auth-token");
        if (StringUtils.isBlank(token)) {
            return null;
        }
        AuthUser authUser = (AuthUser) redisTemplate.opsForValue().get(Constants.PRE_TOKEN + token);
        if (Objects.isNull(authUser)) {
            return null;
        } 
        Date now = new Date();
        Long timeDifference = new Long(authUser.getExpirationTime().longValue() - now.getTime());
        if(timeDifference.longValue() <= 0){
            return null;
        }*/
        AuthUser authUser = new AuthUser();
        authUser.setUserId(1L);
        return authUser;
    }

    protected boolean beanValidator(Object object, Class<?>... groups) {
        BeanValidators.validateWithException(validator, object, groups);
        return true;
    }

    /**
     * 获取用户id
     *
     * @return
     */
    protected Long getUserId() {
        String oid = getRequest().getHeader("x-auth-token");
        if (StringUtils.isBlank(oid)) {
            throw new AuthenticationException("请登录后操作");
        }
        // 先从缓存中获取，避免每次都需要解析
        Object obj = redisUtil.get(oid);
        if (!Objects.isNull(obj)) {
            return Long.valueOf((Integer) obj);
        }

        Claims claims = jwtUtil.parser(oid);
        if (Objects.isNull(claims)) {
            throw new AuthenticationException("认证信息无效，请重新登录");
        }
        Date expiration = claims.getExpiration();
        if (jwtUtil.isTokenExpired(expiration)) {
            throw new AuthenticationException("认证信息已过期，请重新登录");
        }
        String subject = claims.getSubject();
        if (StringUtils.isBlank(subject)) {
            throw new AuthenticationException("认证信息有误，请重新登录");
        }
        Long operationId = Long.valueOf(subject);
        redisUtil.set(oid, operationId, 1, TimeUnit.HOURS);
        return operationId;
    }

}
