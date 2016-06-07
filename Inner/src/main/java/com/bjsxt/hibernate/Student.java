package com.bjsxt.hibernate;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public class Student {
	
	private int id;
	private String name;
	
	private int age;
	private String sex;
	private boolean good;
	public boolean isGood() {
		return good;
	}
	public void setGood(boolean good) {
		this.good = good;
	}
	@Id
	@GeneratedValue
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	
	
	
}
