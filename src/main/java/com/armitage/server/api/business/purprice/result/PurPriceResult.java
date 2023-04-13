package com.armitage.server.api.business.purprice.result;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="PurPriceResult",description="返回结果集resultList")
public class PurPriceResult {
	@ApiModelProperty(value="定价单号",dataType="String")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private String pmNo;
	
	@ApiModelProperty(value="定价日期",dataType="Date",example="yyyy-MM-dd 00:00:00")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private Date pmDate;
	
	@ApiModelProperty(value="财务组织",dataType="String")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private String orgUnitNo;
	
	@ApiModelProperty(value="财务组织名称",dataType="String")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private String orgUnitName;
	
	@ApiModelProperty(value="有效期起",dataType="Date",example="yyyy-MM-dd 00:00:00")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private Date begDate;
	
	@ApiModelProperty(value="有效期止",dataType="Date",example="yyyy-MM-dd 00:00:00")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private Date endDate;

	@ApiModelProperty(value="选定供应商",dataType="String")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private String selVndrCode;
	
	@ApiModelProperty(value="选定供应商名称",dataType="String")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private String selVndrName;

	@ApiModelProperty(value="定价员",dataType="String")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private String priceName;
	
	@ApiModelProperty(value="供应商1",dataType="String")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private String vendorName1;
	
	@ApiModelProperty(value="供应商2",dataType="String")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private String vendorName2;
	
	@ApiModelProperty(value="供应商3",dataType="String")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private String vendorName3;
	
	@ApiModelProperty(value="询价组1",dataType="String")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private String groupName1;
	
	@ApiModelProperty(value="询价组2",dataType="String")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private String groupName2;
	
	@ApiModelProperty(value="询价组3",dataType="String")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private String groupName3;

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
	private Date editorDate;

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
	private List<PurPriceDetailResult> detailList;

	public String getPmNo() {
		return pmNo;
	}

	public void setPmNo(String pmNo) {
		this.pmNo = pmNo;
	}

	public Date getPmDate() {
		return pmDate;
	}

	public void setPmDate(Date pmDate) {
		this.pmDate = pmDate;
	}

	public String getOrgUnitNo() {
		return orgUnitNo;
	}

	public void setOrgUnitNo(String orgUnitNo) {
		this.orgUnitNo = orgUnitNo;
	}

	public String getOrgUnitName() {
		return orgUnitName;
	}

	public void setOrgUnitName(String orgUnitName) {
		this.orgUnitName = orgUnitName;
	}

	public Date getBegDate() {
		return begDate;
	}

	public void setBegDate(Date begDate) {
		this.begDate = begDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getSelVndrCode() {
		return selVndrCode;
	}

	public void setSelVndrCode(String selVndrCode) {
		this.selVndrCode = selVndrCode;
	}

	public String getSelVndrName() {
		return selVndrName;
	}

	public void setSelVndrName(String selVndrName) {
		this.selVndrName = selVndrName;
	}

	public String getPriceName() {
		return priceName;
	}

	public void setPriceName(String priceName) {
		this.priceName = priceName;
	}

	public String getVendorName1() {
		return vendorName1;
	}

	public void setVendorName1(String vendorName1) {
		this.vendorName1 = vendorName1;
	}

	public String getVendorName2() {
		return vendorName2;
	}

	public void setVendorName2(String vendorName2) {
		this.vendorName2 = vendorName2;
	}

	public String getVendorName3() {
		return vendorName3;
	}

	public void setVendorName3(String vendorName3) {
		this.vendorName3 = vendorName3;
	}

	public String getGroupName1() {
		return groupName1;
	}

	public void setGroupName1(String groupName1) {
		this.groupName1 = groupName1;
	}

	public String getGroupName2() {
		return groupName2;
	}

	public void setGroupName2(String groupName2) {
		this.groupName2 = groupName2;
	}

	public String getGroupName3() {
		return groupName3;
	}

	public void setGroupName3(String groupName3) {
		this.groupName3 = groupName3;
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

	public Date getEditorDate() {
		return editorDate;
	}

	public void setEditorDate(Date editorDate) {
		this.editorDate = editorDate;
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

	public List<PurPriceDetailResult> getDetailList() {
		return detailList;
	}

	public void setDetailList(List<PurPriceDetailResult> detailList) {
		this.detailList = detailList;
	}
}
