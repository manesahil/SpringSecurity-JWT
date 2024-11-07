package com.ty.SpringSecurityTrial.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ty.SpringSecurityTrial.Service.todoservice;
import com.ty.SpringSecurityTrial.dto.Todolisttrequest;
import com.ty.SpringSecurityTrial.model.Todolist;

import java.util.List;

import javax.management.RuntimeErrorException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/todo")
public class todoController {

	@Autowired
	private todoservice Todoservice;

	@PostMapping("/add")
	public ResponseEntity<String> addtodo(@RequestBody Todolist Todolist) {
		try {
			Todoservice.addtodo(Todolist);
			return ResponseEntity.ok("Data Added");
		} catch (Error e) {
			throw new RuntimeErrorException(e, "data not been added");
		}
	}

	@GetMapping("/viewall")
	public ResponseEntity<?> getTodos() {
		return ResponseEntity.ok(Todoservice.viewalltodo());

	}

	@GetMapping("/viewbyid/{user_id}")
	public ResponseEntity<List<Todolisttrequest>> viewbyid(@PathVariable Long user_id) {
		try {
			Todoservice.viewbyid(user_id);
			return ResponseEntity.ok(Todoservice.viewbyid(user_id));
		} catch (Error e) {
			throw new RuntimeErrorException(e, "ID NOT FOUND");
		}
	}

	@DeleteMapping("/deletebyid/{user_id}/{id}")
	public ResponseEntity<String> deletebyid(@PathVariable Long user_id, @PathVariable Long id) {
		Todoservice.deletebyid(user_id, id);

		return ResponseEntity.ok("DATA DELETED");
	}

}
