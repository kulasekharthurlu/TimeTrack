package com.app.timetrack.util;

 

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class JdbcTemplateHelper {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public <T> List<T> getResults(String query, Class<T> class1){
		return (List<T>) jdbcTemplate.query(query, new BeanPropertyRowMapper<>(class1));
	}
	

}