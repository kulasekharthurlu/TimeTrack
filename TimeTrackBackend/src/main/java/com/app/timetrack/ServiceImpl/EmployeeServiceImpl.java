package com.app.timetrack.ServiceImpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.UnaryOperator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.app.timetrack.IService.IEmployeeService;
import com.app.timetrack.controller.EmployeeController.EmpFilters;
import com.app.timetrack.entity.Employee;
import com.app.timetrack.entity.Role;
import com.app.timetrack.exceptionhandler.TimeTrackException;
import com.app.timetrack.repository.EmployeeRepository;
import com.app.timetrack.repository.RoleRepository;
import com.app.timetrack.util.JdbcTemplateHelper;

import lombok.Builder;


@Service
public class EmployeeServiceImpl implements IEmployeeService{
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private JdbcTemplateHelper jdbcTemplateHelper;
	
	

	@Override
	public Employee saveEmployee(Employee  employee) {
		employeeRepository.findByEmpEmail(employee.getEmpEmail()).ifPresent(timeTrackException->{throw new TimeTrackException("E401", "Eployee already exists with "+employee.getEmpEmail());});
		employee.setEmpPassword(passwordEncoder.encode(employee.getEmpPassword()==null?"test":employee.getEmpPassword()));
		employee.setRoles(saveUserRoles.apply(employee.getRoles()));
		return employeeRepository.save(employee);
	}
	
	UnaryOperator<Set<Role>> saveUserRoles = rolesFromUI -> {
		Set<Role> roles = new HashSet<>();
		for (Role role : rolesFromUI) {
			Optional<Role> savedRole = roleRepository.findByRoleName(role.getRoleName());
			if (savedRole.isPresent()) {
				roles.add(savedRole.get());
			} else {
				Role newRole = roleRepository.save(role);
				roles.add(newRole);
			}
		}
		return roles;
	};

	@Override
	public Employee updateEmployee(Employee employee) {
		
		Employee employeeFromDB = employeeRepository.findById(employee.getEmpId()).orElseThrow(()->new TimeTrackException("E400", "Employee not found"));
		employeeFromDB.setEmpFirstName(employee!=null ? employee.getEmpFirstName() : employeeFromDB.getEmpFirstName());
		employeeFromDB.setEmpLastName(employee!=null ? employee.getEmpLastName() : employeeFromDB.getEmpLastName());
		employeeFromDB.setEmpPhoneNumber(employee!=null ?  employee.getEmpPhoneNumber() : employeeFromDB.getEmpPhoneNumber());
		employeeFromDB.setEmpGender(employee!=null ? employee.getEmpGender(): employeeFromDB.getEmpGender());
		employeeFromDB.setEmpDOB(employee!=null ? employee.getEmpDOB(): employeeFromDB.getEmpDOB());
		return employeeRepository.save(employeeFromDB);
	}

	@Override
	public int deleteEmployeeById(Long employyeId) {
		Employee findById = employeeRepository
				.findById(employyeId)
				.orElseThrow(()->new TimeTrackException("E400", "Employee not found"));
		 if(findById!=null) {
			 employeeRepository.deleteById(employyeId);
			 return 1;
		 }
		return 0;
	}

	@Override
	public Employee getEmployeeById(Long employyeId) {
		return employeeRepository
				.findById(employyeId)
				.orElseThrow(()->new TimeTrackException("E400", "Employee not found"));	
	}

	@Override
	public List<Employee> getAllEmployees(Integer pageNumber, Integer pageSize) {
		return employeeRepository.findAll(pageNumber,pageSize);
	}
	
	@Override
	public Employee findByUserEmail(String username) {
		
		return employeeRepository.findByEmpEmail(username).orElseThrow(()->new TimeTrackException("E400", "Employee not found"));
	}


	@Override
	public List<Employee>  getAllEmployeesByFilters(EmpFilters empFilters) {
		List<Employee> listOfEmployees = new ArrayList<>();
		try {
			StringBuilder builder = new StringBuilder();
			builder.append("select * from emp_table et where et.emp_id is not null ");
			if(empFilters.getEmpId() != null) {
				builder.append(" and et.emp_id = "+empFilters.getEmpId());
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
			var listOfEmployees1 = jdbcTemplateHelper.getResults(convertedToString, EmployeeDto1.class);
			System.out.println(listOfEmployees);
			List<Map<String,Object>> queryForList = jdbcTemplate.queryForList(convertedToString);
 
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			for(Map<String,Object> emp:queryForList) {
				
				Employee employee = new Employee();
				employee.setEmpId((long)emp.get("emp_id"));
				employee.setEmpFirstName(emp.get("employee_first_name")+"");
				employee.setEmpLastName(emp.get("employee_last_name")+"");
				employee.setEmpEmail(emp.get("employee_email")+"");
				employee.setEmpGender(emp.get("employee_gender")+"");
		        employee.setEmpDOB( LocalDate.parse(emp.get("employee_date_of_birth")+"", formatter) );
				employee.setEmpPhoneNumber(emp.get("employee_phone_number")+"");
				listOfEmployees.add(employee);
				
			}
			
			return listOfEmployees;
//					jdbcTemplate.query(convertedToString, new org.springframework.jdbc.core.RowMapper<Employee>(){  
//			    @Override  
//			    public Employee mapRow(ResultSet rs, int rownumber) throws SQLException {  
//			    	Employee emp = new Employee();
//			        emp.setEmpId(rs.getLong("emp_id"));
//			        emp.setEmpFirstName(rs.getString("employee_first_name"));
//			        emp.setEmpLastName(rs.getString("employee_last_name"));
//			        emp.setEmpEmail(rs.getString("emp_email"));
////			        emp.setEmpGender(rs.getString("emp_gender"));
////			        emp.setEmpDOB(rs.getDate(""));
//		             emp.setEmpPhoneNumber("emp_phone_number");
//			        return emp; 
//			    }  
//			    });  
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	@Builder
	
	public static class EmployeeDto1 {
		
		private Long emp_id;
		private LocalDate emp_date_of_birth;
		private String emp_email;
		private String emp_first_name;
	    private String emp_last_name;
	    private String emp_password;
	    private String emp_phone_number;
	    private String emp_gender;
		public EmployeeDto1(Long emp_id, LocalDate emp_date_of_birth, String empFirstName, String emp_last_name,
				String emp_email, String emp_password, String emp_phone_number, String emp_gender) {
			super();
			this.emp_id = emp_id;
			this.emp_date_of_birth = emp_date_of_birth;
			this.emp_first_name = empFirstName;
			this.emp_last_name = emp_last_name;
			this.emp_email = emp_email;
			this.emp_password = emp_password;
			this.emp_phone_number = emp_phone_number;
			this.emp_gender = emp_gender;
		}
		@Override
		public String toString() {
			return "EmployeeDto1 [emp_id=" + emp_id + ", emp_date_of_birth=" + emp_date_of_birth + ", empFirstName="
					+ emp_first_name + ", emp_last_name=" + emp_last_name + ", emp_email=" + emp_email + ", emp_password="
					+ emp_password + ", emp_phone_number=" + emp_phone_number + ", emp_gender=" + emp_gender + "]";
		}
		public Long getEmp_id() {
			return emp_id;
		}
		public void setEmp_id(Long emp_id) {
			this.emp_id = emp_id;
		}
		public LocalDate getEmp_date_of_birth() {
			return emp_date_of_birth;
		}
		public void setEmp_date_of_birth(LocalDate emp_date_of_birth) {
			this.emp_date_of_birth = emp_date_of_birth;
		}
		public String getEmpFirstName() {
			return emp_first_name;
		}
		public void setEmpFirstName(String empFirstName) {
			this.emp_first_name = empFirstName;
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
		public EmployeeDto1() {
			super();
		}
	 
		 
	 
		 
		
	    
	}
	

}
