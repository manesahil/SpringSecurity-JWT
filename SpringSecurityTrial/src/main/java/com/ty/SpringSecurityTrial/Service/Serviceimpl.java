package com.ty.SpringSecurityTrial.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.ty.SpringSecurityTrial.Repository.repository;
import com.ty.SpringSecurityTrial.model.user;

@Service
public class Serviceimpl implements service {
	@Autowired
	private repository repository;

	@Autowired
	AuthenticationManager authManager;
	
	@Autowired
	JWTService jwtService;

	private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

	public boolean ValidateUser(String username, String Password) {
		user users = repository.FindUsername(username);
		return users != null && users.getPassword().equals(Password);
	}

	

	public void adduser1(user user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		repository.adduser1(user);
	}

	public String verify(user user) {

		Authentication authentication = authManager
				.authenticate(new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword()));

		if (authentication.isAuthenticated()) {
			return jwtService.generateToken(user.getUserName());

		}
		return "fail";
	}

}
