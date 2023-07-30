package com.app.timetrack.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "emp_table")
@EqualsAndHashCode(callSuper = false)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Employee implements Serializable {

	
	
	private static final long serialVersionUID = 1L;

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long empId;

	@Column(name = "employee_first_name")
	private String empFirstName;

	@Column(name = "employee_last_name")
	private String empLastName;

	@Column(name = "employee_email")
	private String empEmail;

	@Column(name = "employee_password")
	private String empPassword;

	@Column(name = "employee_phone_number")
	private String empPhoneNumber;

	@Column(name = "employee_date_of_birth")
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate empDOB;

	@Column(name = "employee_gender")
	private String empGender;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "Emp_Roles_Table", joinColumns = { @JoinColumn(name = "empId") }, inverseJoinColumns = { @JoinColumn(name = "roleId") })
	private Set<Role> roles;
	
	@OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
	private List<Project> projects;
	
	@OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	private List<TimeSheet> timesheets;

	public Employee() {
		super();
	}

	public Employee(Long empId, String empFirstName, String empLastName, String empEmail, String empPassword,
			String empPhoneNumber, LocalDate empDOB, String empGender, Set<Role> roles, List<Project> projects,
			List<TimeSheet> timesheets) {
		super();
		this.empId = empId;
		this.empFirstName = empFirstName;
		this.empLastName = empLastName;
		this.empEmail = empEmail;
		this.empPassword = empPassword;
		this.empPhoneNumber = empPhoneNumber;
		this.empDOB = empDOB;
		this.empGender = empGender;
		this.roles = roles;
		this.projects = projects;
		this.timesheets = timesheets;
	}

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

	public String getEmpPassword() {
		return empPassword;
	}

	public void setEmpPassword(String empPassword) {
		this.empPassword = empPassword;
	}

	public String getEmpPhoneNumber() {
		return empPhoneNumber;
	}

	public void setEmpPhoneNumber(String empPhoneNumber) {
		this.empPhoneNumber = empPhoneNumber;
	}

	public LocalDate getEmpDOB() {
		return empDOB;
	}

	public void setEmpDOB(LocalDate empDOB) {
		this.empDOB = empDOB;
	}

	public String getEmpGender() {
		return empGender;
	}

	public void setEmpGender(String empGender) {
		this.empGender = empGender;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public List<Project> getProjects() {
		return projects;
	}

	public void setProjects(List<Project> projects) {
		this.projects = projects;
	}

	public List<TimeSheet> getTimesheets() {
		return timesheets;
	}

	public void setTimesheets(List<TimeSheet> timesheets) {
		this.timesheets = timesheets;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "Employee [empId=" + empId + ", empFirstName=" + empFirstName + ", empLastName=" + empLastName
				+ ", empEmail=" + empEmail + ", empPassword=" + empPassword + ", empPhoneNumber=" + empPhoneNumber
				+ ", empDOB=" + empDOB + ", empGender=" + empGender + ", roles=" + roles + ", projects=" + projects
				+ ", timesheets=" + timesheets + "]";
	}

	
}