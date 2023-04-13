package com.armitage.server.api.business.purrequire.params;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="PurRequireAddParams",description="新增参数对象")
public class PurRequireAddParams {
	@ApiModelProperty(value="申购组织",dataType="String",example="123",required=false)
	private String reqOrgUnitNo;
	
	@ApiModelProperty(value="申购人",dataType="String",example="123",required=false)
	private String applicants;
	
	@ApiModelProperty(value="申购日期",dataType="Date",example="2019-05-22 00:00:00",required=false)
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	private Date applyDate;
	
	@ApiModelProperty(value="需求日期",dataType="Date",example="2019-05-22 00:00:00",required=false)
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	private Date reqDate;
	
	@ApiModelProperty(value="采购组织",dataType="String",example="00001",required=true)
	private String purOrgUnitNo;

	@ApiModelProperty(value="是否仓库收货",dataType="Boolean",example="0",required=true)
	private boolean toWareHouse;

	@ApiModelProperty(value="收货仓库编号",dataType="String",example="001",required=true)
	private String receiveWareHouseNo;
	
	@ApiModelProperty(value="请求号",dataType="String",example="202008051134034001")
	private String reqNo;

	@ApiModelProperty(value="备注",dataType="String",example="123",required=false)
	private String remarks;
	
	@ApiModelProperty(value="业务类型",dataType="String",example="001",required=true)
	private String bizType;
	
	@ApiModelProperty(value="申购理由",dataType="String",example="123",required=false)
	private String subscribeReason;
	
	@ApiModelProperty(value="申购主题",dataType="String",example="123",required=false)
	private String purRequireTheme;
	
	@ApiModelProperty(value="单据明细",dataType="List<PurRequireDetailParams>",example="123",required=false)
	private List<PurRequireDetailParams> detailList;

	public String getReqOrgUnitNo() {
		return reqOrgUnitNo;
	}

	public void setReqOrgUnitNo(String reqOrgUnitNo) {
		this.reqOrgUnitNo = reqOrgUnitNo;
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

	public boolean isToWareHouse() {
		return toWareHouse;
	}

	public void setToWareHouse(boolean toWareHouse) {
		this.toWareHouse = toWareHouse;
	}

	public String getReceiveWareHouseNo() {
		return receiveWareHouseNo;
	}

	public void setReceiveWareHouseNo(String receiveWareHouseNo) {
		this.receiveWareHouseNo = receiveWareHouseNo;
	}

	public String getPurOrgUnitNo() {
		return purOrgUnitNo;
	}

	public void setPurOrgUnitNo(String purOrgUnitNo) {
		this.purOrgUnitNo = purOrgUnitNo;
	}

	public String getReqNo() {
		return reqNo;
	}

	public void setReqNo(String reqNo) {
		this.reqNo = reqNo;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getBizType() {
		return bizType;
	}

	public void setBizType(String bizType) {
		this.bizType = bizType;
	}

	public String getSubscribeReason() {
		return subscribeReason;
	}

	public void setSubscribeReason(String subscribeReason) {
		this.subscribeReason = subscribeReason;
	}

	public String getPurRequireTheme() {
		return purRequireTheme;
	}

	public void setPurRequireTheme(String purRequireTheme) {
		this.purRequireTheme = purRequireTheme;
	}

	public List<PurRequireDetailParams> getDetailList() {
		return detailList;
	}

	public void setDetailList(List<PurRequireDetailParams> detailList) {
		this.detailList = detailList;
	}
	
}
