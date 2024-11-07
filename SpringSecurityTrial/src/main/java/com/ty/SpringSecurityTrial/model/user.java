package com.ty.SpringSecurityTrial.model;

public class user {

	private Long Id;
	private String UserName;
	private String Password;
	private String role;

	public user() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public String getUserName() {
		return UserName;
	}

	public void setUserName(String userName) {
		UserName = userName;
	}

	public String getPassword() {
		return Password;
	}

	public void setPassword(String password) {
		Password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}


	public user(Long id, String userName, String password, String role) {
		super();
		Id = id;
		UserName = userName;
		Password = password;
		this.role = role;
	}
	
	

}
