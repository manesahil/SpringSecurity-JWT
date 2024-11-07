package com.ty.SpringSecurityTrial.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.ty.SpringSecurityTrial.dto.Todolisttrequest;

public class todolistrowmapper implements RowMapper<Todolisttrequest>{

	@Override
	public Todolisttrequest mapRow(ResultSet rs, int rowNum) throws SQLException {
		Todolisttrequest todolist = new Todolisttrequest();
		todolist.setId(rs.getLong("id"));
		todolist.setTitle(rs.getString("title"));
		todolist.setDescription(rs.getString("description"));
		todolist.setStatus(rs.getString("status"));
		todolist.setUser_id(rs.getLong("user_id"));
		return todolist;
	}

}
