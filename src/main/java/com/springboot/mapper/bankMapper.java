package com.springboot.mapper;


import com.springboot.model.route.RouteMsg;
import org.apache.ibatis.annotations.*;
import org.springframework.cache.annotation.CacheConfig;

@Mapper
@CacheConfig(cacheNames = "bankMsg")
public interface bankMapper {



	@Insert("insert into bankmsg2(channelCode,channelTime,channelSeq,channelCode,msg) "
			+ " VALUES(#{channelCode},#{channelTime},#{channelSeq},#{channelCode},#{msg})")
    int add(RouteMsg routeMsg);
	@Insert("insert into bankMsg(msg) "
			+ " VALUES(#{routeMsg})")
	int add2(String routeMsg);

}
