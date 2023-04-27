package com.app.timetrack.IService;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.app.timetrack.ServiceImpl.EmployeeServiceImpl.EmployeeDto1;
import com.app.timetrack.controller.EmployeeController.EmpFilters;
import com.app.timetrack.entity.Employee;

@Service
public interface IEmployeeService {

	Employee saveEmployee(Employee employeeDTO);

	Employee updateEmployee(Employee employee);

	int deleteEmployeeById(Long employyeId);

	Employee getEmployeeById(Long employeeId);

	List<Employee> getAllEmployees();
	
	List<Map<String,Object>>getAllEmployeesByFilters(EmpFilters empFilters);
}
