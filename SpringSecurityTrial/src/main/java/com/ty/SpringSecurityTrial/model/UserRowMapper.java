package com.ty.SpringSecurityTrial.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class UserRowMapper implements RowMapper<user> {

	@Override
	public user mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		user user = new user();
		user.setId(rs.getLong("user_id"));
		user.setUserName(rs.getString("username"));
		user.setPassword(rs.getString("password"));
		user.setRole(rs.getString("role"));
		return user;
	}

}
