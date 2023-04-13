package com.armitage.server.api.business.purrequire.params;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="PurRequireListParams",description="查询列表参数对象")
public class PurRequireListParams {
	@ApiModelProperty(value="请购单号",dataType="String",example="123",required=false)
	private String prNo;
	
	@ApiModelProperty(value="申购组织",dataType="String",example="123",required=false)
	private String reqOrgUnitNo;
	
	@ApiModelProperty(value="申购人",dataType="String",example="123",required=false)
	private String applicants;

	@ApiModelProperty(value="收货仓库",dataType="String",example="W00001",required=false)
	private String receiveWareHouseNo;
	
	@ApiModelProperty(value="申购开始日期",dataType="Date",example="2019-05-22 00:00:00",required=false)
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	private Date reqDateFrom;
	
	@ApiModelProperty(value="申购结束日期",dataType="Date",example="2019-05-22 00:00:00",required=false)
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	private Date reqDateTo;

	@ApiModelProperty(value="单据状态",dataType="String",example="I：新建，D：待审，P：审核中，A：通过，B：部分通过，N：未通过，S：部分下达，E：下达，F：部分关闭，C：关闭，多个状态如：I,D,A",required=false)
	private String status;

	public String getPrNo() {
		return prNo;
	}

	public void setPrNo(String prNo) {
		this.prNo = prNo;
	}

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

	public String getReceiveWareHouseNo() {
		return receiveWareHouseNo;
	}

	public void setReceiveWareHouseNo(String receiveWareHouseNo) {
		this.receiveWareHouseNo = receiveWareHouseNo;
	}

	public Date getReqDateFrom() {
		return reqDateFrom;
	}

	public void setReqDateFrom(Date reqDateFrom) {
		this.reqDateFrom = reqDateFrom;
	}

	public Date getReqDateTo() {
		return reqDateTo;
	}

	public void setReqDateTo(Date reqDateTo) {
		this.reqDateTo = reqDateTo;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
