package com.app.timetrack.entity;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="t_engagementwageperiod")
public class Engagementwageperiod {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long wagePeriodId;
	
	@JsonFormat(pattern = "dd-MM-yyyy")
	private LocalDate startDate;
	
	@JsonFormat(pattern = "dd-MM-yyyy")
	private  LocalDate endDate;
	
	private Integer status;

	public Engagementwageperiod() {
		super();
	}

	public Engagementwageperiod(Long wagePeriodId, LocalDate startDate, LocalDate endDate, Integer status) {
		super();
		this.wagePeriodId = wagePeriodId;
		this.startDate = startDate;
		this.endDate = endDate;
		this.status = status;
	}

	public Long getWagePeriodId() {
		return wagePeriodId;
	}

	public void setWagePeriodId(Long wagePeriodId) {
		this.wagePeriodId = wagePeriodId;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Engagementwageperiod [wagePeriodId=" + wagePeriodId + ", startDate=" + startDate + ", endDate="
				+ endDate + ", status=" + status + "]";
	}
	
	
	
}
