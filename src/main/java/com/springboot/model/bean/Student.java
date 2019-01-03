package com.springboot.model.bean;

import lombok.Data;

import javax.persistence.Column;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.io.Serializable;

@Data
@XmlRootElement(name = "student")
public class Student implements Serializable{
	
	private static final long serialVersionUID = -339516038496531943L;
	@XmlElement(name = "sno")
	@Column(name = "sno")
	@XmlElementAnno
	private String sno;

	@XmlElement(name = "name")
	@Column(name = "name")
	@XmlElementAnno
	private String name;

	@Column(name = "sex")
	@XmlElement(name = "sex")
	@XmlElementAnno
	private String sex;
	@XmlTransient
	public String getSno() {
		return sno;
	}
	public void setSno(String sno) {
		this.sno = sno;
	}
	@XmlTransient
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@XmlTransient
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	
	
}
