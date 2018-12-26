package com.springboot.bean;


import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;


@XmlRootElement(name="root")
public class JaBeanToXml {

    @XmlElement(name="xmlname")
    @XmlElementAnno
    private String name;

    @XmlElement(name="xmladdress")
    @XmlElementAnno
    private String address;

    @XmlElement(name="xmlage")
    @XmlElementAnno
    private int age;

    @XmlTransient
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlTransient
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @XmlTransient
    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "JaBeanToXml{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", age=" + age +
                '}';
    }
}
