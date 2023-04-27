package com.app.timetrack.entity;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "t_engagement")
public class Engagement {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long engagementid;
	
	private Integer status;
	
	@JsonFormat(pattern = "dd-MM-yyyy")
	private LocalDate joiningDate;
	
	@JsonFormat(pattern = "dd-MM-yyyy")
	private LocalDate leavingDate;
	
	private Integer approvalStatus;
	
	@OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	private List<Engagementwageperiod> engagementwageperiod;

	public Engagement() {
		super();
	}

	public Engagement(Long engagementid, Integer status, LocalDate joiningDate, LocalDate leavingDate,
			Integer approvalStatus, List<Engagementwageperiod> engagementwageperiod) {
		super();
		this.engagementid = engagementid;
		this.status = status;
		this.joiningDate = joiningDate;
		this.leavingDate = leavingDate;
		this.approvalStatus = approvalStatus;
		this.engagementwageperiod = engagementwageperiod;
	}

	public Long getEngagementid() {
		return engagementid;
	}

	public void setEngagementid(Long engagementid) {
		this.engagementid = engagementid;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public LocalDate getJoiningDate() {
		return joiningDate;
	}

	public void setJoiningDate(LocalDate joiningDate) {
		this.joiningDate = joiningDate;
	}

	public LocalDate getLeavingDate() {
		return leavingDate;
	}

	public void setLeavingDate(LocalDate leavingDate) {
		this.leavingDate = leavingDate;
	}

	public Integer getApprovalStatus() {
		return approvalStatus;
	}

	public void setApprovalStatus(Integer approvalStatus) {
		this.approvalStatus = approvalStatus;
	}

	public List<Engagementwageperiod> getEngagementwageperiod() {
		return engagementwageperiod;
	}

	public void setEngagementwageperiod(List<Engagementwageperiod> engagementwageperiod) {
		this.engagementwageperiod = engagementwageperiod;
	}

	@Override
	public String toString() {
		return "Engagement [engagementid=" + engagementid + ", status=" + status + ", joiningDate=" + joiningDate
				+ ", leavingDate=" + leavingDate + ", approvalStatus=" + approvalStatus + ", engagementwageperiod="
				+ engagementwageperiod + "]";
	}

	
	
	
}
