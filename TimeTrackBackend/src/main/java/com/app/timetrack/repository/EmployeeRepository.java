package com.app.timetrack.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.timetrack.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
