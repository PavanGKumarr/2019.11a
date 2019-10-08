package org.user.app.model;

import java.util.Date;

import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
@ApiModel(description = "It is exposing user information")
public class User {
	
	private Integer id;
	
	@Size(min=2, message="Name should be atlease 2 character")
	@ApiModelProperty(notes = "Name should be greator than or equals to 2 character")
	private String name;
	
	@Past
	@ApiModelProperty(notes = "Date of birth should be greator than feature date")
	private Date dob;
	
	
	public User(Integer id, String name, Date dob) {
		this.id = id;
		this.name = name;
		this.dob = dob;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", dob=" + dob + "]";
	}
	
}
