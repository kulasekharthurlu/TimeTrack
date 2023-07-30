package com.app.timetrack.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.app.timetrack.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

	Optional<Employee> findByEmpEmail(String empEmail);
	
	@Query( value = "SELECT * FROM emp_table OFFSET :pageNumber LIMIT :pageSize",nativeQuery=true)
	List<Employee> findAll(@Param("pageNumber") Integer pageNumber,@Param("pageSize") Integer pageSize);

}
