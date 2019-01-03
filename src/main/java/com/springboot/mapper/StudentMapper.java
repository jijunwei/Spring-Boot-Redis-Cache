package com.springboot.mapper;

import org.apache.ibatis.annotations.*;
import org.springframework.cache.annotation.CacheConfig;

import com.springboot.model.bean.Student;

@Mapper
@CacheConfig(cacheNames = "student")
public interface StudentMapper {

	@Update("update student set sname=#{name},ssex=#{sex} where sno=#{sno}")
	int update(Student student);

	@Delete("delete from student where sno=#{sno}")
	void deleteStudentBySno(String sno);

	@Insert("insert into student(sno,sname,ssex) "
			+ " VALUES(#{sno},#{name},#{sex})")
    int add(Student student);
	@Select("select * from student where sno=#{sno}")
	@Results(id = "student", value = { @Result(property = "sno", column = "sno", javaType = String.class),
			@Result(property = "name", column = "sname", javaType = String.class),
			@Result(property = "sex", column = "ssex", javaType = String.class) })
	Student queryStudentBySno(String sno);
}
