package com.armitage.server.api.business.purrequire.result;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="PurRequireListResult",description="返回结果集resultList")
public class PurRequireListResult {
	@ApiModelProperty(value="请购单号",dataType="String")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private String prNo;

	@ApiModelProperty(value="申购组织名称",dataType="String")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private String reqOrgUnitName;

	@ApiModelProperty(value="采购组织名称",dataType="String")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private String purOrgUnitName;

	@ApiModelProperty(value="申购人名称",dataType="String")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private String applicantsName;

	@ApiModelProperty(value="申购日期",dataType="Date",example="yyyy-MM-dd 00:00:00")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private Date applyDate;

	@ApiModelProperty(value="需求日期",dataType="Date",example="yyyy-MM-dd 00:00:00")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private Date reqDate;

	@ApiModelProperty(value="是否仓库申购",dataType="Boolean")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private boolean toWareHouse;

	@ApiModelProperty(value="收货仓库名称",dataType="String")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private String receiveWareHouseName;

	@ApiModelProperty(value="申购总金额",dataType="BigDecimal")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private BigDecimal totalAmt;

	@ApiModelProperty(value="制单人名称",dataType="String")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private String creatorName;

	@ApiModelProperty(value="制单日期",dataType="Date",example="yyyy-MM-dd 00:00:00")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private Date createDate;

	@ApiModelProperty(value="审核人",dataType="String")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private String checker;
	
	@ApiModelProperty(value="审核人名称",dataType="String")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private String checkerName;

	@ApiModelProperty(value="审核日期",dataType="Date",example="yyyy-MM-dd 00:00:00")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private Date checkDate;

	@ApiModelProperty(value="单据状态",dataType="String")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private String status;

	@ApiModelProperty(value="单据状态名称",dataType="String")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private String statusName;

	@ApiModelProperty(value="备注",dataType="String")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private String remarks;

	public String getPrNo() {
		return prNo;
	}
	public void setPrNo(String prNo) {
		this.prNo = prNo;
	}
	public String getReqOrgUnitName() {
		return reqOrgUnitName;
	}
	public void setReqOrgUnitName(String reqOrgUnitName) {
		this.reqOrgUnitName = reqOrgUnitName;
	}
	public String getPurOrgUnitName() {
		return purOrgUnitName;
	}
	public void setPurOrgUnitName(String purOrgUnitName) {
		this.purOrgUnitName = purOrgUnitName;
	}
	public String getApplicantsName() {
		return applicantsName;
	}
	public void setApplicantsName(String applicantsName) {
		this.applicantsName = applicantsName;
	}
	public Date getApplyDate() {
		return applyDate;
	}
	public void setApplyDate(Date applyDate) {
		this.applyDate = applyDate;
	}
	public Date getReqDate() {
		return reqDate;
	}
	public void setReqDate(Date reqDate) {
		this.reqDate = reqDate;
	}
	public boolean isToWareHouse() {
		return toWareHouse;
	}
	public void setToWareHouse(boolean toWareHouse) {
		this.toWareHouse = toWareHouse;
	}
	public String getReceiveWareHouseName() {
		return receiveWareHouseName;
	}
	public void setReceiveWareHouseName(String receiveWareHouseName) {
		this.receiveWareHouseName = receiveWareHouseName;
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
	public String getChecker() {
		return checker;
	}
	public void setChecker(String checker) {
		this.checker = checker;
	}
	public String getCheckerName() {
		return checkerName;
	}
	public void setCheckerName(String checkerName) {
		this.checkerName = checkerName;
	}
	public Date getCheckDate() {
		return checkDate;
	}
	public void setCheckDate(Date checkDate) {
		this.checkDate = checkDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getStatusName() {
		return statusName;
	}
	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public BigDecimal getTotalAmt() {
		return totalAmt;
	}
	public void setTotalAmt(BigDecimal totalAmt) {
		this.totalAmt = totalAmt;
	}
	
}
