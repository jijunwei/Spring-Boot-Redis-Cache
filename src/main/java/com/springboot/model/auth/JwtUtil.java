package com.springboot.model.auth;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Jwt工具
 *
 * @author skyer
 * @date 2018/8/21 16:32
 */
@Component
public class JwtUtil {
    private Logger logger = LoggerFactory.getLogger(JwtUtil.class);

    @Value("${jwt.secret}")
    private String secret;
    @Value("${jwt.expire}")
    private Integer expire;

    /**
     * 生成jwt token
     *
     * @param userId 用户ID
     * @return token
     */
    public String generateToken(Long userId, String issuer) {
        Date nowDate = new Date();
        return Jwts.builder()
                .setHeaderParam("typ", "JWT")
                // 后续获取 subject 是 userid
                .setSubject(userId + "")
                .setIssuer(issuer)
                .setIssuedAt(nowDate)
                .setNotBefore(nowDate)
                .setExpiration(DateUtils.addSeconds(nowDate, expire))
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    /**
     * 解析 token，
     * 利用 jjwt 提供的parser传入秘钥，
     *
     * @param token token
     * @return 数据声明 Map<String, Object>
     */
    public Claims parser(String token) {
        try {
            return Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * token是否过期
     *
     * @return true：过期
     */
    public boolean isTokenExpired(Date expiration) {
        return expiration.before(new Date());
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public int getExpire() {
        return expire;
    }

    public void setExpire(int expire) {
        this.expire = expire;
    }

}
