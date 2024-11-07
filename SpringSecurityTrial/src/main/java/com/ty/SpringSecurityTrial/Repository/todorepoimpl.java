package com.ty.SpringSecurityTrial.Repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ty.SpringSecurityTrial.model.Todolist;
import com.ty.SpringSecurityTrial.model.todolistrowmapper;
import com.ty.SpringSecurityTrial.dto.Todolisttrequest;
import com.ty.SpringSecurityTrial.dto.todolisttrequest;



@Repository
public class todorepoimpl implements todolistrepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	


	@SuppressWarnings("deprecation")
	public void addtodo(Todolist Todolist) {
		String userCheckSql = "SELECT COUNT(*) FROM user_data WHERE user_id = ?";
		Integer count = jdbcTemplate.queryForObject(userCheckSql, new Object[] { Todolist.getUser_id() },
				Integer.class);

		if (count == null || count == 0) {
			throw new IllegalArgumentException("User ID does not exist.");
		}

		String sql = "INSERT INTO todo (title, description, status, user_id) VALUES (?, ?, ?, ?)";
		jdbcTemplate.update(sql, Todolist.getTitle(), Todolist.getDescription(), Todolist.getStatus(),
				Todolist.getUser_id());
	}

	@SuppressWarnings({ "deprecation" })
	@Override
	public List<Todolisttrequest> viewbyid(Long user_id) {
		String sql = "SELECT * FROM todo WHERE user_id=?";

		return (List<Todolisttrequest>) jdbcTemplate.query(sql, new Object[] { user_id }, new todolistrowmapper());

	}
	
	public List<Todolisttrequest> findByUserId(Long userId) {
        String sql = "SELECT * FROM todo WHERE user_id=?";
        
        // Correct query method: Pass the RowMapper to map the rows to TodoListRequest
        return jdbcTemplate.query(sql, new Object[]{userId}, new todolistrowmapper());
    }
	

	@SuppressWarnings("deprecation")
	@Override
	public String findroles(Long user_id) {
		String sql = "SELECT role FROM user_data WHERE user_id=?";

		return jdbcTemplate.queryForObject(sql, new Object[] { user_id }, String.class);
	}

	@Override
	public List<Todolisttrequest> viewalltodo() {
		String sql = "SELECT * FROM todo";
		return jdbcTemplate.query(sql, new todolistrowmapper());
	}
//
	@Override
	public void Update(Long user_id,Long id, Todolisttrequest Todolisttrequest) {
		String sql = "UPDATE todo SET title=?,description=?,status=? WHERE user_id=? AND id=?";
		jdbcTemplate.update(sql, Todolisttrequest.getTitle(), Todolisttrequest.getDescription(),
				Todolisttrequest.getStatus(), user_id,id);
	}

	@Override
	public void deletebyid(Long user_id,Long id) {
		String sql = "DELETE FROM todo WHERE user_id=? AND id=?";
		jdbcTemplate.update(sql, user_id,id);
	}



}
