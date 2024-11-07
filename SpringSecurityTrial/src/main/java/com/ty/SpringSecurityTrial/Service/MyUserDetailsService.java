package com.ty.SpringSecurityTrial.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ty.SpringSecurityTrial.Repository.repository;
import com.ty.SpringSecurityTrial.model.UserPrincipal;
import com.ty.SpringSecurityTrial.model.user;
@Service
public class MyUserDetailsService implements UserDetailsService {

	@Autowired
	private repository repository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	user user =repository.FindUsername(username);
		
	if(user==null)
	{
		
		System.out.println("User not found");
		throw new UsernameNotFoundException("User not found");
	}
	
	return new UserPrincipal(user);
	}

}
