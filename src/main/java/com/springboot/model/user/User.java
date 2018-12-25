package com.springboot.model.user;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

/**
 * 用户表：user
 * */
@Data
public class User implements Serializable {

    private static final long serialVersionUID = 1793503868909512538L;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    //用户名
    @Column(name = "user_name")
    private String userName;

    //id
    @Column(name = "bank_id")
    private Long bankId;

    /**
     * 2018-11-2 新增字段，是否是衡水注册用户新增字段
     * modifier：朱迪
     */
    @Column(name = "hs_register_user")
    private Integer hsRegisterUser;
    
    //密码
    @Column(name = "password")
    private String password;
   
    // 手机号码18519121912或PC端登陆账号05585
    @Column(name = "mobile")
    private String mobile;
    
    // 交易密码
    @Column(name = "transaction_secret")
    private String transactionSecret;
    
    // 加密用的盐
    @Column(name = "salt")
    private transient String salt;
    
    // 创建时间
    @Column(name = "create_time")
    private Date createTime;
    
    // 更新时间
    @Column(name = "update_time")
    private Date updateTime;
    
    //有效性
    @Column(name = "enable")
    private Boolean enable;
    // 0:白名单  1：黑名单  2 其他
    @Column(name = "type")
    private Integer type;

}
