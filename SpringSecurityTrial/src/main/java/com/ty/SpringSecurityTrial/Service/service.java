package com.ty.SpringSecurityTrial.Service;

import java.util.List;

import com.ty.SpringSecurityTrial.model.user;

public interface service {

	public boolean ValidateUser(String username, String Password);

	public void adduser1(user user);

	public String verify(user user);

	public List<user> adminViewAllUsers();

	public void deleteUser(Long userId);

	public List<user> getAllUsers();
	
	public void ChangePassword(Long userId, String Password);

}
