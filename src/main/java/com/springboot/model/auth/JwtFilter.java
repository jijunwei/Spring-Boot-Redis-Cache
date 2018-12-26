/*
package com.springboot.model.auth;

import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

*/
/**
 * shiro自定义权限认证过滤器
 * 该过滤器的主要作用并非是验证登录是否合法，而是区分登录用户和游客，如果为游客，则不会进入AuthRealm进行授权验证，
 * 而是直接调用没有任何权限控制的接口（如果接口存在@RequiresAuthentication，@RequiresRoles，@RequiresPermissions
 * 等注解，则会返回无权限异常）,登录用户会进入AuthRealm执行想用的授权验证操作
 *
 * @author skyer
 * @date 2018/8/21 16:31
 *//*

public class JwtFilter extends BasicHttpAuthenticationFilter {
    final static Logger logger = LoggerFactory.getLogger(JwtFilter.class);

    */
/**
     * 判断用户是否想要登入。
     * 检测header里面是否包含Authorization字段即可
     *//*

    @Override
    protected boolean isLoginAttempt(ServletRequest request, ServletResponse response) {
        HttpServletRequest req = (HttpServletRequest) request;
        String authorization = req.getHeader("x-auth-token");
        logger.info("客户认证信息:{}", authorization);
        return authorization != null;
    }

    */
/**
     * 执行shiro登陆操作，其实执行该方法会进入我们自定义的AuthRealm进行认证信息的验证，虽然此处为登录，实际并非登录操作，只是我们
     * 实现了自定义的登录授权验证，需要借助shiro的一些方法规则进行步骤的流转。getSubject(request, response).cus(token);
     * 此方法会调用AuthRealm中的doGetAuthenticationInfo方法进行认证信息的验证，即token是否合法、是否过期等
     *//*

    @Override
    protected boolean executeLogin(ServletRequest request, ServletResponse response) {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String authorization = httpServletRequest.getHeader("x-auth-token");
        JwtToken token = new JwtToken(authorization);
        // 提交给realm进行登入，如果错误他会抛出异常并被捕获
        getSubject(request, response).login(token);
        // 如果没有抛出异常则代表登入成功，返回true
        return true;
    }

    */
/**
     * 此处无论是否是登陆状态都返回true,只是登陆状态会调用executeLogin方法执行shiro的登陆操作，未登录状态也可访问未添加权限控制的接口
     * Controller中可以通过 subject.isAuthenticated() 来判断用户是否登入，用以区分登陆用户和未登录用户。
     * 如果有些资源只有登入用户才能访问，我们只需要在方法上面加上 @RequiresAuthentication 注解即可
     *//*

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        if (isLoginAttempt(request, response)) {
            try {
                executeLogin(request, response);
            } catch (Exception e) {
                logger.warn(e.getMessage());
            }
        }
        return true;
    }


}
*/
