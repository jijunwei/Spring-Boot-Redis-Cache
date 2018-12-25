package com.springboot.model.user;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/** 登陆返回信息模型类
 *  on 2017/09/24.
 */
@Setter
@Getter
@ToString
public class AuthUser implements Serializable {

    private static final long serialVersionUID = 8587379253082602382L;
    
    public AuthUser() {
        
    }
    
    // 用户id
    private Long userId;
   
    // 用户名
    private String userName;
    
    // token
    private String token;
    
    // 手机号
    private String mobile;
    
    // 第三方ID
    //private String uuid;
    
    // 过期时间
    private Long expirationTime;
    
    //private Set<String> authorities = Sets.newHashSet();

    // 用户角色ID
    //private Long roleId;
    
    // 是否为管理员
    private Boolean admin;
    
    // 账户id
    private Long accountId;
    
    private Long bankId;

}
