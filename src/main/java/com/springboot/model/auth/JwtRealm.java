package com.springboot.model.auth;


import com.springboot.util.ApplicationContextHolder;
import io.jsonwebtoken.Claims;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * 授权认证，进入此流程的请求说明通过了JwtFilter过滤器登录验证（JwtFilter过滤器的主要作用是区分登录用户和未登录请求）
 * 通过了JwtFilter登录验证后会调用doGetAuthenticationInfo方法进行认证信息的校验，例如token是否合法、是否过期等，
 * 如果请求的接口仅要求登录(例如方法存在@RequiresAuthentication注解)，那么就不会执行doGetAuthorizationInfo方法
 * 进行授权,如果调用的方法存在@RequiresRoles、@RequiresPermissions注解，在认证后还需要调用doGetAuthorizationInfo方法进行授权验证
 *
 * @author skyer
 * @date 2018/8/21 17:59
 */
@Component
public class JwtRealm extends AuthorizingRealm {
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JwtToken;
    }

    /**
     * 授权(验证权限时调用)
     * 方法存在@RequiresRoles、@RequiresPermissions注解会调用该方法进行授权验证
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        Set<String> permsSet = new HashSet<>();
        permsSet.add("user:test3");
        info.setStringPermissions(permsSet);
        return info;
    }

    /**
     * 认证(登录时调用)
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String token = (String) authenticationToken.getPrincipal();
        JwtUtil jwtUtil = ApplicationContextHolder.getBean(JwtUtil.class);
        Claims claims = jwtUtil.parser(token);
        if (Objects.isNull(claims)) {
            throw new AuthenticationException("认证信息无效，请重新登录");
        }
        Date expiration = claims.getExpiration();
        if (jwtUtil.isTokenExpired(expiration)) {
            throw new AuthenticationException("认证信息已过期，请重新登录");
        }
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(token, token, getName());
        return info;
    }
}
