package com.springboot.bean;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
@XmlRootElement(name = "student")
public class Student implements Serializable{
	
	private static final long serialVersionUID = -339516038496531943L;
	@XmlElement(name = "sno")
	private String sno;
	@XmlElement(name = "name")
	private String name;
	@XmlElement(name = "sex")
	private String sex;
	public String getSno() {
		return sno;
	}
	public void setSno(String sno) {
		this.sno = sno;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	
	
}
