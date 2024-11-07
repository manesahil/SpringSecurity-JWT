package com.ty.SpringSecurityTrial.dto;

public class Todolisttrequest {

	private Long id;
	private String title;
	private String description;
	private String status;
	private Long user_id;
	public Todolisttrequest() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Todolisttrequest(Long id, String title, String description, String status, Long user_id) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.status = status;
		this.user_id = user_id;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Long getUser_id() {
		return user_id;
	}
	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}
	
	
	

	
}
