package com.sau.rest.socialsau.dto;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table(name = "user")
public class User implements Serializable {
	
	private static final long serialVersionUID = -6249474269952977249L;

	@Id
	@Column(name = "USER_ID")
	private Integer userId;
	
	@Column(name = "FIRSTNAME")
	private String firstname;
	
	@Column(name = "LASTNAME")
	private String lastname;
	
	@Column(name = "EMAIL")
	private String email;
	
	@Column(name = "PASSWORD")
	private String password;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "BIRTHDAY")
	private Date birthday;
	
	@Column(name = "ROLE")
	private String role;
	
	@Column(name = "URL_PROFILE")
	private String urlProfile;
	
	@Column(name = "CONFIRM_CODE")
	private String confirmCode;
	
	@Column(name = "STATUS")
	private String status;
	
	@Column(name = "COLOR_PROFILE")
	private String colorProfile;
	
	@Column(name = "IMAGE_PROFILE")
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
	public User(Integer userId, String firstname, String lastname,
			String email, String password, Date birthday, String confirmCode,
			String status, String role, String urlProfile) {
		this.userId = userId;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.password = password;
		this.birthday = birthday;
		this.confirmCode = confirmCode;
		this.status = status;
		this.role = role;
		this.urlProfile = urlProfile;
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
	public String getConfirmCode() {
		return confirmCode;
	}
	public void setConfirmCode(String confirmCode) {
		this.confirmCode = confirmCode;
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
	public String getUrlProfile() {
		return urlProfile;
	}
	public void setUrlProfile(String urlProfile) {
		this.urlProfile = urlProfile;
	}
	public String getColorProfile() {
		return colorProfile;
	}
	public void setColorProfile(String colorProfile) {
		this.colorProfile = colorProfile;
	}
	public String getImageProfile() {
		return imageProfile;
	}
	public void setImageProfile(String imageProfile) {
		this.imageProfile = imageProfile;
	}

}
