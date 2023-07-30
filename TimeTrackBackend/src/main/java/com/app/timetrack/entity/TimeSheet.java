package com.app.timetrack.entity;

import java.time.LocalDateTime;

import com.app.timetrack.enums.ShiftType;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;

@Entity
@Table(name = "time_sheet_table")
@JsonIgnoreProperties(ignoreUnknown = true)
public class TimeSheet {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
	private LocalDateTime shiftStart=LocalDateTime.now();
	
	@JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
	private LocalDateTime shiftEnd=LocalDateTime.now();
	
	private ShiftType shiftType;
	
	private Long workingHours;
	
	@JoinColumn(name = "emp_id")
	private Employee employee;

	
	public TimeSheet() {
		super();
	}


	public TimeSheet(Long id, LocalDateTime shiftStart, LocalDateTime shiftEnd, ShiftType shiftType, Long workingHours,
			Employee employee) {
		super();
		this.id = id;
		this.shiftStart = shiftStart;
		this.shiftEnd = shiftEnd;
		this.shiftType = shiftType;
		this.workingHours = workingHours;
		this.employee = employee;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public LocalDateTime getShiftStart() {
		return shiftStart;
	}


	public void setShiftStart(LocalDateTime shiftStart) {
		this.shiftStart = shiftStart;
	}


	public LocalDateTime getShiftEnd() {
		return shiftEnd;
	}


	public void setShiftEnd(LocalDateTime shiftEnd) {
		this.shiftEnd = shiftEnd;
	}


	public ShiftType getShiftType() {
		return shiftType;
	}


	public void setShiftType(ShiftType shiftType) {
		this.shiftType = shiftType;
	}


	public Long getWorkingHours() {
		return workingHours;
	}


	public void setWorkingHours(Long workingHours) {
		this.workingHours = workingHours;
	}


	public Employee getEmployee() {
		return employee;
	}


	public void setEmployee(Employee employee) {
		this.employee = employee;
	}


	@Override
	public String toString() {
		return "TimeSheet [id=" + id + ", shiftStart=" + shiftStart + ", shiftEnd=" + shiftEnd + ", shiftType="
				+ shiftType + ", workingHours=" + workingHours + ", employee=" + employee + "]";
	}
	
	
}
