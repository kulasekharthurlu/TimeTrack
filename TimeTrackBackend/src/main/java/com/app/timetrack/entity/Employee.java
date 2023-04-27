package com.app.timetrack.entity;

import java.io.Serializable;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "emp_table")
@EqualsAndHashCode(callSuper = false)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Employee  implements Serializable {

	private static final long serialVersionUID = 1L;
	
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(name = "id")
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

		public Employee() {
			super();
		}

		
		public Employee(Long empId, String empFirstName, String empLastName, String empEmail, String empPassword,
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


		@Override
		public String toString() {
			return "Employee [empId=" + empId + ", empFirstName=" + empFirstName + ", empLastName=" + empLastName
					+ ", empEmail=" + empEmail + ", empPassword=" + empPassword + ", empPhoneNumber=" + empPhoneNumber
					+ ", empDOB=" + empDOB + ", empGender=" + empGender + "]";
		}

		
		
	    
	    
	
}