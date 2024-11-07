package com.ty.SpringSecurityTrial.Service;

import com.ty.SpringSecurityTrial.model.user;

public interface service {

	public boolean ValidateUser(String username, String Password);

	public void adduser1(user user);

	public String verify(user user);

}
