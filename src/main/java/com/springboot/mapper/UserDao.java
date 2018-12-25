package com.springboot.mapper;



import com.springboot.model.user.User;
import org.apache.ibatis.annotations.*;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;
import java.util.Map;

/** 
* user表相关查询
*
* @version 创建时间：2018年5月8日 下午16:52:49
* 
*/
public interface UserDao extends BaseDao<User> {

    /**
     * 根据手机号和密码查询用户信息
     * @param mobile 手机号
     * @param password 密码
     * @return
     */
    @Select("select * from user where mobile=#{mobile} and password = #{password} and enable = 1")
    @Results({
        @Result(property = "userName", column = "user_name"),
        @Result(property = "bankId", column = "bank_id"),
        @Result(property = "transactionSecret", column = "transaction_secret"),
        @Result(property = "createTime", column = "create_time")
    })
    User findByMobileAndPassword(@Param("mobile") String mobile, @Param("password") String password);



     /**
     * 增加新的用户
     * @param user 用户信息
     * @return
     */
    @CacheEvict(cacheNames = "user:baseinfo", key="#p0.mobile")
    @Insert("INSERT INTO user(user_name, mobile , password, transaction_secret, bank_id) VALUES(#{userName},"
            + " #{mobile}, #{password}, #{transactionSecret}, #{bankId})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    Integer addNewUser(User user);

    /**
     * 根据手机号修改用户的密码
     *
     * @param mobile 手机号
     * @param newPassword 新密码
     * @return
     */
    @CacheEvict(cacheNames = "user:baseinfo", key="#p0")
    @Update("update user set password=#{newPassword} where mobile=#{mobile} and enable = 1")
    void updateUserPasswordByMobile(@Param("mobile") String mobile, @Param("newPassword") String newPassword);

    /**
     * 根据手机号查找用户的基本信息
     *
     * @param mobile 手机号
     * @return
     */
    @Cacheable(cacheNames = "user:baseinfo", key="#p0 + ''")
    @Select("select * from user where mobile=#{mobile} and enable = 1")
    @Results({
        @Result(property = "userName", column = "user_name"),
        @Result(property = "bankId", column = "bank_id"),
        @Result(property = "transactionSecret", column = "transaction_secret"),
        @Result(property = "createTime", column = "create_time")
    })
    User findByMobileAndEnable(@Param("mobile") String mobile);

    /**
     * 根据mobile和可用性enable修改用户的手机号
     *
     * @param mobile 手机号
     * @param newMobile 新的手机号
     * @return
     */
    @CacheEvict(cacheNames = "user:baseinfo", key="#p0")
    @Update("update user set mobile=#{newMobile} where mobile=#{mobile} and enable = 1")
    void updateMobileByOldMobile(@Param("mobile") String mobile, @Param("newMobile") String newMobile);

    /**
     * 根据userId找用户的基本信息
     *
     * @param id 用户id
     * @return
     */
    @Select("select * from user where id = #{id} and enable = 1")
    @Results({
        @Result(property = "userName", column = "user_name"),
        @Result(property = "bankId", column = "bank_id"),
        @Result(property = "transactionSecret", column = "transaction_secret"),
        @Result(property = "createTime", column = "create_time")
    })
    User findByIdAndEnable(@Param("id") Long id);


    
    /**
     * 根据手机号获取用户的盐和交易密码
     * @param mobile
     * @return
     */
    @Select("select salt, transaction_secret from user where mobile = #{mobile} and enable = 1")
    @Results({
        @Result(property = "transactionSecret", column = "transaction_secret"),
    })
    User findSaltByMobile(@Param("mobile") String mobile);
    
    @Select("select distinct u.mobile,u.bank_id,ud.idcard,ud.real_name,a.available_amount from user u "
    		+ " LEFT JOIN account a on u.id = a.user_id LEFT JOIN user_detail ud on u.id = ud.user_id where a.id=#{accountId} and u.enable = 1")
    Map<String, Object> findUserInfoByAccountId(@Param("accountId") Long accountId);



    /**
     * 逻辑删除用户
     * @param userId
     * @return
     */
    @Update("UPDATE `user` SET `enable` = '0' WHERE id = #{userId}")
    boolean deleteUser(@Param("userId") Long userId);

    /**
     * 修改用户登陆密码
     * @param password
     * @param userId
     * @return
     */
    @Update("UPDATE user SET `password` = #{password} WHERE id = #{userId}")
    boolean updateUserPassword(@Param("password") String password, @Param("userId") Long userId);

    /**
     * 根据用户Id与密码获取用户信息
     * @param userId
     * @param password
     * @return
     */
    @Select("SELECT id FROM user WHERE id = #{userId} AND `password` = #{password} AND `enable` = '1'")
    String getUserByIdAndPassword(@Param("userId") Long userId, @Param("password") String password);

    /**
     * 增加衡水对接注册的新用户
     * @param user 用户信息
     * @return
     */
    @CacheEvict(cacheNames = "user:baseinfo", key="#p0.mobile")
    @Insert("INSERT INTO user(user_name, mobile, password, transaction_secret, bank_id, " +
            "hs_register_user) VALUES (#{userName}, #{mobile}, #{password}, #{transactionSecret}, " +
            "#{bankId}, #{hsRegisterUser})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    Integer addHSNewUser(User user);

    /**
     * 获取衡水用户信息by手机号
     * @param mobile
     * @return
     */
    @Select(" SELECT id, user_name AS userName, bank_id AS bankId, hs_register_user AS hsRegisterUserm, " +
            " mobile AS mobile, enable FROM `user` WHERE mobile = #{mobile} AND hs_register_user = '1' ")
    User getHsUserIfo(@Param("mobile") String mobile);



    /**
     * 获取用户密码
     * @param userId 用户id
     * @return
     */
    @Select("select password from user where enable = 1 and bank_id is not null and id = #{userId}")
    String getPassword(Long userId);


    /**
     * 根据用户id获取账号
     * @param userId 用户id
     * @return
     */
    @Select("select mobile from user where enable = 1 and id = #{userId}")
    String getMobile(Long userId);

    /**
     * 检查用户是否为管理后台
     * 检查条件为bank_id是否为空,不为空的为管理人员
     * @param userId
     * @return
     */
    @Select("select count(*) from user where enable = 1 and id = #{userId} and bank_id is not null")
    Integer checkUserIsManager(Long userId);

    /**
     * 获取用户姓名
     * @return
     */
    @Select("select mobile from user where enable = 1 and id = #{userId} and bank_id is not null")
    String getUserName(Long userId);
}
