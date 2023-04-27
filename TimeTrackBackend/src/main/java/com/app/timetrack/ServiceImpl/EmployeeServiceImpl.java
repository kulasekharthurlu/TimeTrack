package com.app.timetrack.ServiceImpl;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.app.timetrack.IService.IEmployeeService;
import com.app.timetrack.controller.EmployeeController.EmpFilters;
import com.app.timetrack.entity.Employee;
import com.app.timetrack.repository.EmployeeRepository;
import com.app.timetrack.util.JdbcTemplateHelper;
import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;

@Service
public class EmployeeServiceImpl implements IEmployeeService{
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Autowired
	private JdbcTemplateHelper jdbcTemplateHelper;
	

	@Override
	public Employee saveEmployee(Employee  employee) {
		employee.setEmpDOB(LocalDate.now());
		return employeeRepository.save(employee);
	}

	@Override
	public Employee updateEmployee(Employee employee) {
		 
		return employeeRepository.save(employee);
	}

	@Override
	public int deleteEmployeeById(Long employyeId) {
		Optional<Employee> findById = employeeRepository.findById(employyeId);
		
		 if(findById.isPresent()) {
			 employeeRepository.deleteById(employyeId);
			 return 1;
		 }
		return 0;
	}

	@Override
	public Employee getEmployeeById(Long employyeId) {
		Optional<Employee> findById = employeeRepository.findById(employyeId);
		
		 if(findById.isPresent()) {
			  
			 return findById.get();
		 }
	   throw new ObjectNotFoundException(employyeId, " In database there is no object with"+employyeId+"");
	}

	@Override
	public List<Employee> getAllEmployees() {
		return employeeRepository.findAll();
	}

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public List<Map<String,Object>> getAllEmployeesByFilters(EmpFilters empFilters) {
		try {
			StringBuilder builder = new StringBuilder();
			builder.append("select * from emp_table et where et.id is not null ");
			if(empFilters.getEmpId() != null) {
				builder.append(" and et.id = "+empFilters.getEmpId());
			}
			if(empFilters.getEmpFirstName() != null) {
				builder.append(" and et.employee_first_name = '"+empFilters.getEmpFirstName()+"'");
			}
			if(empFilters.getEmpLastName() != null) {
				builder.append(" and et.employee_last_name = '"+empFilters.getEmpLastName()+"'");
			}
			if(empFilters.getEmpEmail() != null) {
				builder.append(" and et.employee_email = '"+empFilters.getEmpEmail()+"'");
			}
			if(empFilters.getEmpPhoneNumber() != null) {
				builder.append(" and et.employee_phone_number = '"+empFilters.getEmpPhoneNumber()+"'");
			}
			if(empFilters.getEmpDOB() != null) {
				builder.append(" and et.employee_date_of_birth = '"+empFilters.getEmpDOB()+"'");
			}
			if(empFilters.getEmpGender() != null) {
				builder.append(" and et.employee_gender = '"+empFilters.getEmpGender()+"'");
			}
			
			String convertedToString = builder.toString();
			System.out.println("======"+convertedToString);
			List<Map<String,Object>> queryForList = jdbcTemplate.queryForList(convertedToString);
			return queryForList;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	public static class EmployeeDto1 {
	    private Long id;
	    private String emp_first_name;
	    private String emp_last_name;
	    private String emp_email;
	    private String emp_password;
	    private String emp_phone_number;
	    private String emp_gender;
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public String getEmp_first_name() {
			return emp_first_name;
		}
		public void setEmp_first_name(String emp_first_name) {
			this.emp_first_name = emp_first_name;
		}
		public String getEmp_last_name() {
			return emp_last_name;
		}
		public void setEmp_last_name(String emp_last_name) {
			this.emp_last_name = emp_last_name;
		}
		public String getEmp_email() {
			return emp_email;
		}
		public void setEmp_email(String emp_email) {
			this.emp_email = emp_email;
		}
		public String getEmp_password() {
			return emp_password;
		}
		public void setEmp_password(String emp_password) {
			this.emp_password = emp_password;
		}
		public String getEmp_phone_number() {
			return emp_phone_number;
		}
		public void setEmp_phone_number(String emp_phone_number) {
			this.emp_phone_number = emp_phone_number;
		}
		 
		public String getEmp_gender() {
			return emp_gender;
		}
		public void setEmp_gender(String emp_gender) {
			this.emp_gender = emp_gender;
		}
		public EmployeeDto1(Long id, String emp_first_name, String emp_last_name, String emp_email, String emp_password,
				String emp_phone_number, LocalDate emp_date_of_birth, String emp_gender) {
			super();
			this.id = id;
			this.emp_first_name = emp_first_name;
			this.emp_last_name = emp_last_name;
			this.emp_email = emp_email;
			this.emp_password = emp_password;
			this.emp_phone_number = emp_phone_number;
			this.emp_gender = emp_gender;
		}
	    
	}

}
