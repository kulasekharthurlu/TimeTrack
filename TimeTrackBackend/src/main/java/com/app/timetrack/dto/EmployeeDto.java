package com.app.timetrack.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

public class EmployeeDto {
	private Long empId;
	private String empFirstName;
	private String empLastName;
	private String empEmail;
	private String empPassword;
	private String empPhoneNumber;
	private LocalDate empDOB;
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

	public EmployeeDto(Long empId, String empFirstName, String empLastName, String empEmail, String empPassword,
			String empPhoneNumber, LocalDate empDOB, String empGender) {
		super();
		this.empId = empId;
		this.empFirstName = empFirstName;
		this.empLastName = empLastName;
		this.empEmail = empEmail;
		this.empPassword = empPassword;
		this.empPhoneNumber = empPhoneNumber;
		this.empDOB = empDOB;
		this.empGender = empGender;
	}

	@Override
	public String toString() {
		return "EmployeeDto [empId=" + empId + ", empFirstName=" + empFirstName + ", empLastName=" + empLastName
				+ ", empEmail=" + empEmail + ", empPassword=" + empPassword + ", empPhoneNumber=" + empPhoneNumber
				+ ", empDOB=" + empDOB + ", empGender=" + empGender + "]";
	}

}
