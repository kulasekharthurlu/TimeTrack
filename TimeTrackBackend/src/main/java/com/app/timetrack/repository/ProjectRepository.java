package com.app.timetrack.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.timetrack.entity.Project;

public interface ProjectRepository extends JpaRepository<Project, Long>{

}
