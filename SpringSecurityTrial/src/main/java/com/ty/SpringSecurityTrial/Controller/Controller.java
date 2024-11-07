package com.ty.SpringSecurityTrial.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ty.SpringSecurityTrial.Service.service;
import com.ty.SpringSecurityTrial.model.user;


@RestController
@RequestMapping("/h1")
public class Controller {

	@Autowired
	public service service;

	@GetMapping("/hello")
	public String hello() {
		return "hello world";
	}

	// @PreAuthorize("hasRole('USER')")
	@PostMapping("/user")
	public String user() {
		return "hello user";
	}

	// @PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/admin")
	public String admin() {
		return "hello admin";
	}

	@PostMapping("/put")
	public ResponseEntity<?> addUser1(@RequestBody user user) {

		service.adduser1(user);

		return ResponseEntity.ok("data added");
	}

	@PostMapping("/login")
	public String login(@RequestBody user user) {

		return service.verify(user);
	}

}
