package com.ty.SpringSecurityTrial.Repository;

import java.util.List;

import com.ty.SpringSecurityTrial.model.user;

public interface repository {

	public user FindUsername(String username);

	public void adduser1(user user);

	public List<user> adminViewAllUsers();
	
	public void deleteUser(Long userId);
	
	public user findByUsername(String username);
	
	public List<user> findAllUsers();
	
	public void ChangePassword(Long userId, String Password);

}
