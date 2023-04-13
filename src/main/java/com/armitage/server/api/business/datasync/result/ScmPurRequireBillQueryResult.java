package com.armitage.server.api.business.datasync.result;

import java.util.Date;
import java.util.List;

import com.armitage.server.api.business.audit.result.AuditStatusResult;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="ScmPurRequireBillQueryResult",description="请购单返回参数对象")
public class ScmPurRequireBillQueryResult {
	
	@ApiModelProperty(value="请购单号",dataType="String")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private String prNo;	
	@ApiModelProperty(value="业务类型",dataType="String")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private String bizType;	
	@ApiModelProperty(value="申购组织",dataType="String")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private String orgUnitNo;	
	@ApiModelProperty(value="申请人",dataType="String")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private String applicants;	
	@ApiModelProperty(value="申购日期",dataType="String")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	private Date applyDate;	
	@ApiModelProperty(value="需求日期",dataType="String")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	private Date reqDate;	
	@ApiModelProperty(value="是否仓库收货",dataType="String")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private String toWareHouse;	
	@ApiModelProperty(value="收货仓库编号",dataType="String")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private String receiveWareHouseNo;	
	@ApiModelProperty(value="收货仓库名称",dataType="String")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private String receiveWareHouseName;	
	@ApiModelProperty(value="采购组织",dataType="String")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private String purOrgUnitNo;
	@ApiModelProperty(value="控制单元",dataType="String")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private String controlUnitNo;
	@ApiModelProperty(value="单据状态",dataType="String")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private String status;
	@ApiModelProperty(value="创建时间",dataType="String")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	private Date createDate;
	@ApiModelProperty(value="修改时间",dataType="String")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	private Date editDate;
	@ApiModelProperty(value="审核人",dataType="String")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private String checker;
	@ApiModelProperty(value="审核日期",dataType="String")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private String checkDate;
	@ApiModelProperty(value="备注",dataType="String")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private String remarks;
	@ApiModelProperty(value="请购单明细",dataType="List")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private List<ScmPurRequireItemsQueryResult> items;
	
	@ApiModelProperty(value="审批信息",dataType="List")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private List< AuditStatusResult > auditStatusResults;
	
	
	
	public Date getCreateDate() {
		return createDate;
	}
	public Date getEditDate() {
		return editDate;
	}
	public void setEditDate(Date editDate) {
		this.editDate = editDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public List<AuditStatusResult> getAuditStatusResults() {
		return auditStatusResults;
	}
	public void setAuditStatusResults(List<AuditStatusResult> auditStatusResults) {
		this.auditStatusResults = auditStatusResults;
	}
	public String getPrNo() {
		return prNo;
	}
	public void setPrNo(String prNo) {
		this.prNo = prNo;
	}
	public String getBizType() {
		return bizType;
	}
	public void setBizType(String bizType) {
		this.bizType = bizType;
	}
	public String getOrgUnitNo() {
		return orgUnitNo;
	}
	public void setOrgUnitNo(String orgUnitNo) {
		this.orgUnitNo = orgUnitNo;
	}
	public String getApplicants() {
		return applicants;
	}
	public void setApplicants(String applicants) {
		this.applicants = applicants;
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
	public String getToWareHouse() {
		return toWareHouse;
	}
	public void setToWareHouse(String toWareHouse) {
		this.toWareHouse = toWareHouse;
	}
	public String getReceiveWareHouseNo() {
		return receiveWareHouseNo;
	}
	public void setReceiveWareHouseNo(String receiveWareHouseNo) {
		this.receiveWareHouseNo = receiveWareHouseNo;
	}
	public String getReceiveWareHouseName() {
		return receiveWareHouseName;
	}
	public void setReceiveWareHouseName(String receiveWareHouseName) {
		this.receiveWareHouseName = receiveWareHouseName;
	}
	public String getPurOrgUnitNo() {
		return purOrgUnitNo;
	}
	public void setPurOrgUnitNo(String purOrgUnitNo) {
		this.purOrgUnitNo = purOrgUnitNo;
	}
	public String getControlUnitNo() {
		return controlUnitNo;
	}
	public void setControlUnitNo(String controlUnitNo) {
		this.controlUnitNo = controlUnitNo;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getChecker() {
		return checker;
	}
	public void setChecker(String checker) {
		this.checker = checker;
	}
	public String getCheckDate() {
		return checkDate;
	}
	public void setCheckDate(String checkDate) {
		this.checkDate = checkDate;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public List<ScmPurRequireItemsQueryResult> getItems() {
		return items;
	}
	public void setItems(List<ScmPurRequireItemsQueryResult> items) {
		this.items = items;
	}
	
}
