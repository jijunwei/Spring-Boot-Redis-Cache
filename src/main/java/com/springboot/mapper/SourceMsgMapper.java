package com.springboot.mapper;

import com.springboot.model.route.SourceMsg;
import org.apache.ibatis.annotations.*;
import org.springframework.cache.annotation.CacheConfig;

@Mapper
@CacheConfig(cacheNames = "sourceMsg")
public interface SourceMsgMapper {


    @Delete("delete from sourcemsg where id=#{id}")
    void deleteSourceMsgById(int id);

   /* @Insert("insert into sourcemsg(msg) "
            + " VALUES(#{msg})")
    int addSourceMsgString(String sourceMsg);*/
    @Insert("insert into sourcemsgbean(channelDate,channelTime,channelSeq,channelCode,msg) "
            + " VALUES(#{channelDate},#{channelTime},#{channelSeq},#{channelCode},#{msg})")
    int add(SourceMsg sourceMsg);
    @Select("select * from sourcemsgbean where id=#{id}")
    @Results(id = "sourcemsgbean", value = { @Result(property = "id", column = "id", javaType = Integer.class),
            @Result(property = "channelDate", column = "channelDate", javaType = String.class),
            @Result(property = "channelTime", column = "channelTime", javaType = String.class),
            @Result(property = "channelSeq", column = "channelSeq", javaType = String.class),
            @Result(property = "channelCode", column = "channelCode", javaType = String.class),
            @Result(property = "msg", column = "msg", javaType = String.class),
    })
    SourceMsg querySourceMsgById(int  id);
    @Select("select * from sourcemsg where id=#{id}")
    @Results(id = "sourcemsg", value = { @Result(property = "id", column = "id", javaType = Integer.class),
            @Result(property = "msg", column = "msg", javaType = String.class)})
    String querySourceMsgStringById(int  id);
}
