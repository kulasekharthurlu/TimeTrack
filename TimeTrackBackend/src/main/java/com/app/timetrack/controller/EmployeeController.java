package com.app.timetrack.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.timetrack.IService.IEmployeeService;
import com.app.timetrack.dto.EmployeeDto;
import com.app.timetrack.entity.Employee;
import com.app.timetrack.util.Response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@RestController
@CrossOrigin("*")
@RequestMapping("api/employee/v1")
public class EmployeeController {

	private static final Logger log = LoggerFactory.getLogger(EmployeeController.class);

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private IEmployeeService employeeService;

	@PostMapping("/save")
	public Response<?> saveEmployee(@RequestBody Employee employee) {
		  employee = employeeService.saveEmployee(employee);
		  employee.setEmpPassword(null);
		return new Response<>(HttpStatus.OK,"success", employee);
	}

	@PutMapping("/update")
	public Response<?> updateEmployee(@RequestBody EmployeeDto employeeDto) {
		log.info("Entering into save emp : {}", employeeDto);
		var employee = modelMapper.map(employeeDto , Employee.class);
		employee = employeeService.updateEmployee(employee);
		  employeeDto = modelMapper.map(employee , EmployeeDto.class);
		  employeeDto.setEmpPassword(null);
		return new Response<>(HttpStatus.OK,"success", employeeDto);
	}

	@GetMapping("/get/{employeeId}")
	public Response<?> getEmployeeById(@PathVariable Long employeeId) {
		var employeeById = employeeService.getEmployeeById(employeeId);
		var employeeDto = modelMapper.map(employeeById , EmployeeDto.class);
		  employeeDto.setEmpPassword(null);
		return new Response<>(HttpStatus.OK,"success", employeeDto);
	}

	@DeleteMapping("/delete/{employeeId}")
	public Response<?> deleteEmployeeById(@PathVariable Long employeeId) {
		return new Response<>(HttpStatus.OK,"success",  employeeService.deleteEmployeeById(employeeId));
	}

	@GetMapping("/getall")
	public Response<?> getAllEmployees() {
		var empDtoList = 
				    employeeService.getAllEmployees();
		return new Response<>(HttpStatus.OK,"success", empDtoList );
	}

	@GetMapping("/get-employees-by-filters")
	public Response<?> getEmployeesByFilters(@RequestBody EmpFilters empFilters) {
		var allEmployeesByFilters = employeeService.getAllEmployeesByFilters(empFilters);
return new Response<>(HttpStatus.OK,"success", allEmployeesByFilters );
	}

	
	@Builder
	@AllArgsConstructor
	@NoArgsConstructor
	public static class EmpFilters {
		private Long empId;
		private String empFirstName;
		private String empLastName;
		private String empEmail;
		private String empPhoneNumber;
		private String empDOB;
		private String empGender;
		public Long getEmpId() {
			return empId;
		}
		public void setEmpId(Long empId) {
			this.empId = empId;
		}
		public String getEmpFirstName() {
			return empFirstName;
		}
		public void setEmpFirstName(String empFirstName) {
			this.empFirstName = empFirstName;
		}
		public String getEmpLastName() {
			return empLastName;
		}
		public void setEmpLastName(String empLastName) {
			this.empLastName = empLastName;
		}
		public String getEmpEmail() {
			return empEmail;
		}
		public void setEmpEmail(String empEmail) {
			this.empEmail = empEmail;
		}
		public String getEmpPhoneNumber() {
			return empPhoneNumber;
		}
		public void setEmpPhoneNumber(String empPhoneNumber) {
			this.empPhoneNumber = empPhoneNumber;
		}
		public String getEmpDOB() {
			return empDOB;
		}
		public void setEmpDOB(String empDOB) {
			this.empDOB = empDOB;
		}
		public String getEmpGender() {
			return empGender;
		}
		public void setEmpGender(String empGender) {
			this.empGender = empGender;
		}
		
	}

}
