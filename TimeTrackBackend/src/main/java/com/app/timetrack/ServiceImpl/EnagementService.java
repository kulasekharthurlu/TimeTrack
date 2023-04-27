package com.app.timetrack.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

import com.app.timetrack.entity.Engagement;
import com.app.timetrack.repository.EngamentRepository;

@Service
public class EnagementService {
	
	@Autowired
	private EngamentRepository engamentRepository;
	
	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;
	
	
	
	public Engagement saveEngagement(Engagement engagement) {
		
		return engamentRepository.save(engagement);
	}
	
	public List<Engagement> getAllEngagements(){
		Integer s1=3;Integer s2=6;
		
		//return engamentRepository.findbySatus(s1,s2);
		return engamentRepository.findAll().stream().filter(e->e.getStatus()==3 || e.getStatus()==6).toList();
	}

}
