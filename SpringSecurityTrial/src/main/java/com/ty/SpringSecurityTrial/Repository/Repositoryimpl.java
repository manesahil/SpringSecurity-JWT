package com.ty.SpringSecurityTrial.Repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

import com.ty.SpringSecurityTrial.model.UserRowMapper;
import com.ty.SpringSecurityTrial.model.user;

@Repository
public class Repositoryimpl implements repository {

	@Autowired
	public JdbcTemplate jdbcTemplate;

	public user FindUsername(String Username) {
		String sql = "SELECT * FROM user_data WHERE username=?";

		return jdbcTemplate.queryForObject(sql, new UserRowMapper(), Username);
	}

	public void adduser1(user user) {
		String sql = "INSERT INTO user_data(username,password,role) VALUES(?,?,?)";
		jdbcTemplate.update(sql, user.getUserName(), user.getPassword(),user.getRole());
	}

}
