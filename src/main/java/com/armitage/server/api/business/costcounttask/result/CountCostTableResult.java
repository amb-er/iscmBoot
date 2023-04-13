package com.armitage.server.api.business.costcounttask.result;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@ApiModel(value="CountCostTableResult",description="盘存表查询返回结果")
public class CountCostTableResult {
	
	@ApiModelProperty(value="部门编号",dataType="String")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private String deptNo;

	@ApiModelProperty(value="部门名称",dataType="String")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private String deptName;

	@ApiModelProperty(value="业务日期",dataType="Date")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private Date bizDate;
	
	@ApiModelProperty(value="经手人编号",dataType="String")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private String countingPerson;

	@ApiModelProperty(value="经手人名称",dataType="String")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private String countingPersonName;

	@ApiModelProperty(value="复盘人编号",dataType="String")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private String countingAgainPerson;

	@ApiModelProperty(value="复盘人名称",dataType="String")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private String countingAgainPersonName;

	@ApiModelProperty(value="监盘人编号",dataType="String")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private String countingMonitorer;

	@ApiModelProperty(value="监盘人名称",dataType="String")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private String countingMonitorerName;

	@ApiModelProperty(value="作业开始时间",dataType="Date")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private Date taskBegTime;

	@ApiModelProperty(value="作业结束时间",dataType="Date")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private Date taskEndTime;

	@ApiModelProperty(value="制单人编号",dataType="String")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private String creator;
	
	@ApiModelProperty(value="制单人名称",dataType="String")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private String creatorName;

	@ApiModelProperty(value="制单日期",dataType="Date")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private Date createDate;
	
	@ApiModelProperty(value="单据状态",dataType="String")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private String status;
	
	@ApiModelProperty(value="备注",dataType="String")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private String remarks;

	@ApiModelProperty(value="单据明细",dataType="List")
	private List<CountCostTableDetailResult> detailList;
	
	public String getDeptNo() {
		return deptNo;
	}

	public void setDeptNo(String deptNo) {
		this.deptNo = deptNo;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public Date getBizDate() {
		return bizDate;
	}

	public void setBizDate(Date bizDate) {
		this.bizDate = bizDate;
	}

	public String getCountingPerson() {
		return countingPerson;
	}

	public void setCountingPerson(String countingPerson) {
		this.countingPerson = countingPerson;
	}

	public String getCountingPersonName() {
		return countingPersonName;
	}

	public void setCountingPersonName(String countingPersonName) {
		this.countingPersonName = countingPersonName;
	}

	public String getCountingAgainPerson() {
		return countingAgainPerson;
	}

	public void setCountingAgainPerson(String countingAgainPerson) {
		this.countingAgainPerson = countingAgainPerson;
	}

	public String getCountingAgainPersonName() {
		return countingAgainPersonName;
	}

	public void setCountingAgainPersonName(String countingAgainPersonName) {
		this.countingAgainPersonName = countingAgainPersonName;
	}

	public String getCountingMonitorer() {
		return countingMonitorer;
	}

	public void setCountingMonitorer(String countingMonitorer) {
		this.countingMonitorer = countingMonitorer;
	}

	public String getCountingMonitorerName() {
		return countingMonitorerName;
	}

	public void setCountingMonitorerName(String countingMonitorerName) {
		this.countingMonitorerName = countingMonitorerName;
	}

	public Date getTaskBegTime() {
		return taskBegTime;
	}

	public void setTaskBegTime(Date taskBegTime) {
		this.taskBegTime = taskBegTime;
	}

	public Date getTaskEndTime() {
		return taskEndTime;
	}

	public void setTaskEndTime(Date taskEndTime) {
		this.taskEndTime = taskEndTime;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public String getCreatorName() {
		return creatorName;
	}

	public void setCreatorName(String creatorName) {
		this.creatorName = creatorName;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public List<CountCostTableDetailResult> getDetailList() {
		return detailList;
	}

	public void setDetailList(List<CountCostTableDetailResult> detailList) {
		this.detailList = detailList;
	}
	
	
}
