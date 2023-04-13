package com.armitage.server.api.business.invmove.result;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="InvMoveResult",description="返回结果集resultList")
public class InvMoveResult {
	@ApiModelProperty(value="移库单号",dataType="String")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private String wtNo;
	
	@ApiModelProperty(value="业务日期",dataType="Date",example="yyyy-MM-dd 00:00:00")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private Date bizDate;
	
	@ApiModelProperty(value="制单组织",dataType="String")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private String createOrgUnitName;

	@ApiModelProperty(value="调出部门",dataType="String")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private String outOrgUnitNo;
	
	@ApiModelProperty(value="调出部门名称",dataType="String")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private String outOrgUnitName;
	
	@ApiModelProperty(value="调入部门",dataType="String")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private String inOrgUnitNo;
	
	@ApiModelProperty(value="调入部门名称",dataType="String")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private String inOrgUnitName;

	@ApiModelProperty(value="制单人",dataType="String")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private String creator;

	@ApiModelProperty(value="制单人名称",dataType="String")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private String creatorName;

	@ApiModelProperty(value="制单日期",dataType="Date",example="yyyy-MM-dd 00:00:00")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private Date createDate;

	@ApiModelProperty(value="修改人",dataType="String")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private String editor;

	@ApiModelProperty(value="修改人名称",dataType="String")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private String editorName;

	@ApiModelProperty(value="修改日期",dataType="Date",example="yyyy-MM-dd 00:00:00")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private Date editDate;

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
	
	@ApiModelProperty(value="待审人",dataType="String")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private String pendingUsr;

	@ApiModelProperty(value="单据明细",dataType="List")
	private List<InvMoveDetailResult> detailList;

	public String getWtNo() {
		return wtNo;
	}

	public void setWtNo(String wtNo) {
		this.wtNo = wtNo;
	}

	public Date getBizDate() {
		return bizDate;
	}

	public void setBizDate(Date bizDate) {
		this.bizDate = bizDate;
	}

	public String getCreateOrgUnitName() {
		return createOrgUnitName;
	}

	public void setCreateOrgUnitName(String createOrgUnitName) {
		this.createOrgUnitName = createOrgUnitName;
	}

	public String getOutOrgUnitNo() {
		return outOrgUnitNo;
	}

	public void setOutOrgUnitNo(String outOrgUnitNo) {
		this.outOrgUnitNo = outOrgUnitNo;
	}

	public String getOutOrgUnitName() {
		return outOrgUnitName;
	}

	public void setOutOrgUnitName(String outOrgUnitName) {
		this.outOrgUnitName = outOrgUnitName;
	}

	public String getInOrgUnitNo() {
		return inOrgUnitNo;
	}

	public void setInOrgUnitNo(String inOrgUnitNo) {
		this.inOrgUnitNo = inOrgUnitNo;
	}

	public String getInOrgUnitName() {
		return inOrgUnitName;
	}

	public void setInOrgUnitName(String inOrgUnitName) {
		this.inOrgUnitName = inOrgUnitName;
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

	public String getEditor() {
		return editor;
	}

	public void setEditor(String editor) {
		this.editor = editor;
	}

	public String getEditorName() {
		return editorName;
	}

	public void setEditorName(String editorName) {
		this.editorName = editorName;
	}

	public Date getEditDate() {
		return editDate;
	}

	public void setEditDate(Date editDate) {
		this.editDate = editDate;
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

	public String getPendingUsr() {
		return pendingUsr;
	}

	public void setPendingUsr(String pendingUsr) {
		this.pendingUsr = pendingUsr;
	}

	public List<InvMoveDetailResult> getDetailList() {
		return detailList;
	}

	public void setDetailList(List<InvMoveDetailResult> detailList) {
		this.detailList = detailList;
	}
}
