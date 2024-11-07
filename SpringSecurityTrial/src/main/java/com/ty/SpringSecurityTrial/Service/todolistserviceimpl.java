package com.ty.SpringSecurityTrial.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ty.SpringSecurityTrial.Repository.todolistrepository;
import com.ty.SpringSecurityTrial.dto.Todolisttrequest;
import com.ty.SpringSecurityTrial.model.Todolist;

@Service
public class todolistserviceimpl implements todoservice {

	@Autowired
	private todolistrepository todolistrepository;

	@Override
	public void addtodo(Todolist Todolist) {
		todolistrepository.addtodo(Todolist);
	}

	@Override
	public List<Todolisttrequest> viewbyid(Long user_id) {

		return todolistrepository.viewbyid(user_id);
	}

	@Override
	public List<Todolisttrequest> findallbyrole(Long user_id) {

			return todolistrepository.viewalltodo();
		}
	

	@Override
	public void Update(Long user_id, Long id, Todolisttrequest Todolisttrequest) {
		todolistrepository.Update(user_id, id, Todolisttrequest);
	}

	@Override
	public void deletebyid(Long user_id, Long id) {
		todolistrepository.deletebyid(user_id, id);
	}

}
