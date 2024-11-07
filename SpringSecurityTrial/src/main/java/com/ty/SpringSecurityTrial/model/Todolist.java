package com.ty.SpringSecurityTrial.model;

public class Todolist {

	private int id;
	private String title;
	private String description;
	private String status;
	private int user_id;

	public Todolist() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Todolist(int id, String title, String description, String status, int user_id) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.status = status;
		this.user_id = user_id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
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

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

}
