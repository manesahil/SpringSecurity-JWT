package com.ty.SpringSecurityTrial.Service;

import java.util.List;

import com.ty.SpringSecurityTrial.dto.Todolisttrequest;
import com.ty.SpringSecurityTrial.model.Todolist;

public interface todoservice {

	public void addtodo(Todolist Todolist);

	public List<Todolisttrequest> viewbyid(Long user_id);
//
	public List<Todolisttrequest> viewalltodo();
//
	public void Update(Long user_id,Long id,Todolisttrequest Todolisttrequest);
//	
	public void deletebyid(Long user_id,Long id);

}
