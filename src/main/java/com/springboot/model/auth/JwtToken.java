package com.springboot.model.auth;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * JwtToken
 *
 * @author skyer
 * @date 2018/8/21 18:01
 */
public class JwtToken implements AuthenticationToken {
    private String token;

    public JwtToken(String token) {
        this.token = token;
    }

    @Override
    public Object getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }
}
