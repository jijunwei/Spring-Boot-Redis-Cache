package com.springboot;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.springboot.bean.MyProps;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;


import com.springboot.bean.Student;
import com.springboot.service.StudentService;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public class ApplicationTest {

	@Autowired
	private StudentService studentService;
    @Autowired
    private MyProps myProps;
    @Autowired
    ObjectMapper objectMapper;
	@Test
    public void test1() throws Exception {
        Student student1 = this.studentService.queryStudentBySno("001");
        System.out.println("学号" + student1.getSno() + "的学生姓名为：" + student1.getName());
        
        Student student2 = this.studentService.queryStudentBySno("001");
        System.out.println("学号" + student2.getSno() + "的学生姓名为：" + student2.getName());
    }
	
	@Test
	public void test2() throws Exception {
		Student student1 = this.studentService.queryStudentBySno("001");
		System.out.println("学号" + student1.getSno() + "的学生姓名为：" + student1.getName());

		student1.setName("康康");
		this.studentService.update(student1);
		
		Student student2 = this.studentService.queryStudentBySno("001");
		System.out.println("学号" + student2.getSno() + "的学生姓名为：" + student2.getName());
	}



    @Test
    public void propsTest() throws JsonProcessingException {
        System.out.println("simpleProp: " + myProps.getSimpleProp());

        System.out.println("arrayProps: " + objectMapper.writeValueAsString(myProps.getArrayProps()));
        System.out.println("listProp1: " + objectMapper.writeValueAsString(myProps.getListProp1()));
        System.out.println("listProp2: " + objectMapper.writeValueAsString(myProps.getListProp2()));
        System.out.println("mapProps: " + objectMapper.writeValueAsString(myProps.getMapProps()));
    }

}
