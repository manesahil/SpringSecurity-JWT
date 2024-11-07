package com.ty.SpringSecurityTrial.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ty.SpringSecurityTrial.Service.service;
import com.ty.SpringSecurityTrial.model.user;

import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/User")
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

	@PostMapping("/put") // add user
	public ResponseEntity<?> addUser1(@RequestBody user user) {

		service.adduser1(user);

		return ResponseEntity.ok("data added");
	}

	@PostMapping("/login") // generate token
	public String login(@RequestBody user user) {

		return service.verify(user);
	}

	@DeleteMapping("/DeleteUser/{userId}")
	public ResponseEntity<String> deleteUser(@PathVariable Long userId) {
		service.deleteUser(userId);
		return ResponseEntity.ok("USER DELETED");
	}

	@GetMapping("/adminViewAllUsers")
	public ResponseEntity<List<user>> adminViewAllUsers() {
		List<user> users = service.getAllUsers();
		return ResponseEntity.ok(users);
	}

	@PutMapping("/ChangePassword/{userId}")
	public ResponseEntity<String> putMethodName(@PathVariable Long userId, @RequestParam String password) {

		service.ChangePassword(userId, password);
		return ResponseEntity.ok("Password Changed");
	}

}
