package com.app.timetrack.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
import org.springframework.web.client.RestTemplate;

import com.app.timetrack.IService.IEmployeeService;
import com.app.timetrack.IService.Test;
import com.app.timetrack.entity.Employee;
import com.app.timetrack.security.JwtUtil;
import com.app.timetrack.util.CommonConstants;
import com.app.timetrack.util.LoginPojo;
import com.app.timetrack.util.Response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@RestController
@CrossOrigin(origins =  "http://localhost:5173/", allowedHeaders = {"Content-Type", "Authorization"})
@RequestMapping("api/employee/v1")
public class EmployeeController {

	private static final Logger log = LoggerFactory.getLogger(EmployeeController.class);

	@Autowired
	private IEmployeeService employeeService; 

	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private JwtUtil jwtTokenUtil;
	
	@Autowired
	private Test test1;
	
	private final RestTemplate restTemplate;

    @Autowired
    public EmployeeController(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }
	
	
	@PostMapping(value = "/login")
	public ResponseEntity<Map<String, String>> login(@RequestBody LoginPojo user) {
		String message = "Messasge";
		Map<String, String> m = new HashMap<>();
		try {
			final Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword()));
			SecurityContextHolder.getContext().setAuthentication(authentication);
			final String token = jwtTokenUtil.generateToken(authentication);
			m.put("AuthToken", token);
			 
			m.put(message, CommonConstants.LOGIN_SUCCESS);
			return new ResponseEntity<>(m, HttpStatus.ACCEPTED);
		} catch (BadCredentialsException e) {
			m.put(message, CommonConstants.AUTH_FAIL);
			return new ResponseEntity<>(m, HttpStatus.UNAUTHORIZED);
		} catch (InternalAuthenticationServiceException e) {
			m.put(message, CommonConstants.AUTH_FAIL);
			return new ResponseEntity<>(m, HttpStatus.UNAUTHORIZED);
		} catch (Exception e) {
			m.put(message, e.getCause().getMessage());
			return new ResponseEntity<>(m, HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping("/signup")
	public Response<?> saveEmployee(@RequestBody Employee employee) {
		employee = employeeService.saveEmployee(employee);
		return new Response<>(HttpStatus.OK, "Employee Saved Successfully", employee);
	}

	@PutMapping("/update")
	@PreAuthorize("hasAnyRole('ADMIN', 'EMPLOYEE')")
	public Response<?> updateEmployee(@RequestBody Employee employee) {
		log.info("Entering into update empId : {}", employee.getEmpId());
		if (employee.getEmpId() == null)
			return new Response<Employee>(HttpStatus.BAD_REQUEST, "EmployeeId must be required to update", employee);
		Employee updatedEmployee = employeeService.updateEmployee(employee);
		return new Response<>(HttpStatus.OK, "Employee Updated successfully", updatedEmployee);
	}

	@GetMapping("/get/{employeeId}")
//	@PreAuthorize("hasAnyRole('ADMIN', 'EMPLOYEE')")
	public Response<?> getEmployeeById(@PathVariable Long employeeId) {
		var employeeById = employeeService.getEmployeeById(employeeId);

		return new Response<>(HttpStatus.OK, "success", employeeById);
	}
	
	@DeleteMapping("/delete")
	//@PreAuthorize("hasRole('ADMIN')")
	public Response<?> deleteEmployeeById(@RequestParam("employeeId") Long employeeId) {
		log.info("request enters to the delete method");
		var employeeById = employeeService.getEmployeeById(employeeId);
		if(employeeById != null) {
			
			return new Response<>(HttpStatus.OK,
					"Employee deleted successfully", 
					employeeService.deleteEmployeeById(employeeId));
		}
		return new Response<>(HttpStatus.BAD_REQUEST,
				"Employee id not found in db", 
				null);
	}

	@GetMapping("/getall")
	//@PreAuthorize("hasRole('ADMIN')")
	public Response<?> getAllEmployees(@RequestParam("pageNumber") Integer pageNumber,@RequestParam("pageSize") Integer pageSize) {
		  List<Employee> allEmployees = employeeService.getAllEmployees(pageNumber,pageSize);
		  
		  
		  
		  
		  List<Employee> collect = allEmployees
		                                        .stream()
		                                        .filter(emp -> emp.getEmpFirstName().equals("kulasekhar"))
		                                        .collect(Collectors.toList());
		  
		  
		  
		  
//		var forObject = restTemplate.getForObject("http://localhost:2020/swagger-test/api/test/get/", Res.class, "Asuran");
		return new Response<>(HttpStatus.OK, "success", allEmployees);
	}

	@PostMapping("/get-employees-by-filters")
	public Response<?> getEmployeesByFilters(@RequestBody EmpFilters empFilters) {
		  List<Employee> allEmployeesByFilters = employeeService.getAllEmployeesByFilters(empFilters);
		return new Response<>(HttpStatus.OK, "success", allEmployeesByFilters);
	}
	@Setter
	@Getter
	@Builder
	@AllArgsConstructor
	@NoArgsConstructor
	public static class Res{
		String name;

		@Override
		public String toString() {
			return "Res [name=" + name + "]";
		}
		
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
			
			List<Employee> listOfEmployees = new ArrayList<>();
			
			Set<Employee> collect = listOfEmployees
					.stream()
					.collect(Collectors.toSet());
			
			String accountNumber = "123456789";
			StringBuilder sb = new StringBuilder();
			
			
			int length = accountNumber.length();
			
			for(int i=0;i<length-4;i++) {
				sb.append("*");
			}
			 
			String substring2 = accountNumber.substring(4, accountNumber.length()+1);
			
			String finalString = sb.toString()+substring2;
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
		}

	}

}
