package com.sau.android.socialsau.dto;

import java.io.Serializable;
import java.util.Date;

public class User implements Serializable {

	private static final long serialVersionUID = 3145896505829235138L;
	
	private Integer userId;
	private String firstname;
	private String lastname;
	private String email;
	private String password;
	private Date birthday;
	private String status;
	private String role;
	private String imageProfile;
	
	// Constructor
	public User() {}
	public User(String email, String password) {
		this.email = email;
		this.password = password;
	}
	public User(Integer userId, String firstname, String lastname, Date birthday) {
		this.userId = userId;
		this.firstname = firstname;
		this.lastname = lastname;
		this.birthday = birthday;
	}
	
	public User(String firstname, String lastname, String email,String password, Date birthday) {
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.password = password;
		this.birthday = birthday;
	}
	
	// Generate Getter and Setter
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getImageProfile() {
		return imageProfile;
	}
	public void setImageProfile(String imageProfile) {
		this.imageProfile = imageProfile;
	}
}
