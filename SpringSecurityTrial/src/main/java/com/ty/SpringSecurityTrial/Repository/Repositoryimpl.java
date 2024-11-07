package com.ty.SpringSecurityTrial.Repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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
	
	
	public List<user> adminViewAllUsers()
	{
		String sql="SELECT * FROM user_data";
		return jdbcTemplate.query(sql,new UserRowMapper());
		
	}

	 @Transactional
	public void deleteUser(Long userId) {
		String sqluser="DELETE FROM todo WHERE user_id=?";
		jdbcTemplate.update(sqluser,userId);
		
		String sqltodo="DELETE FROM user_data WHERE user_id=?";
		jdbcTemplate.update(sqltodo,userId);
		
	}

	 @SuppressWarnings("deprecation")
	public user findByUsername(String username) {
	        String sql = "SELECT * FROM user_data WHERE username = ?";
	        
	        return jdbcTemplate.queryForObject(sql, new Object[]{username},new UserRowMapper()); 
	 }

	 public List<user> findAllUsers() {
	        String sql = "SELECT * FROM user_data";  
	        return jdbcTemplate.query(sql, new UserRowMapper());
	        		}
	 
	 public void ChangePassword(Long userId, String Password) {
	        String sql = "UPDATE user_data SET password = ? WHERE user_id=?";
	        
	        jdbcTemplate.update(sql,Password,userId); 
	 }
	

}
