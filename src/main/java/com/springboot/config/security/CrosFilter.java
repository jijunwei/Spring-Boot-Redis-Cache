package com.springboot.config.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * 可以去配置访问的请求形式
 *  on 2017/09/24.
 */
@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
@Configuration
public class CrosFilter implements Filter {

    @Value("${security.allowedHeaders}")
    private String allowedHeaders;

    private static final Set<String> DISALLOWED_METHOD = new HashSet<>();

    static {
        DISALLOWED_METHOD.add("OPTIONS");
        //DISALLOWED_METHOD.add("PUT");
        //DISALLOWED_METHOD.add("DELETE");
    }


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, DELETE");
        response.setHeader("Access-Control-Allow-Headers", allowedHeaders);
        response.setHeader("Access-Control-Max-Age", "3600");
        if (DISALLOWED_METHOD.contains(request.getMethod())) {
            return;
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }

}
