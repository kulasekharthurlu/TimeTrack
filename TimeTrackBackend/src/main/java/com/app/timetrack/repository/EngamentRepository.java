package com.app.timetrack.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.app.timetrack.entity.Engagement;

public interface EngamentRepository extends JpaRepository<Engagement, Long> {

	//List<Engagement> findbySatus(Integer s1);
	
	//List<Engagement> findbyStatus(@Param("staus1")Integer p1,@Param("staus1") Integer p2);
}
