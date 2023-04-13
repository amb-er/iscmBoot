package com.armitage.server.api.business.apinvoice.params;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="ApinvoiceQueryAccountDetailParams",description="往来对账明细查询参数对象")
public class ApinvoiceQueryAccountDetailParams {
	
	@ApiModelProperty(value="开始日期",dataType="Date",example="2019-05-22 00:00:00",required=true)
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	private Date beginDate;
	
	@ApiModelProperty(value="结束日期",dataType="Date",example="2019-05-22 00:00:00",required=true)
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	private Date endDate;
	
	@ApiModelProperty(value="供应商平台供应商Id",dataType="Long",example="170",required=true)
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private long platVendorId;
	
	@ApiModelProperty(value="需求方Id",dataType="Long",example="170",required=true)
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private long demanderId;
	
	@ApiModelProperty(value="供应链采购入库单号串",dataType="String",example="123,456",required=false)
	private String sourceWrNos;
	
	@ApiModelProperty(value="财务组织编码",dataType="String",example="123",required=false)
	private String finOrgUnitNo;

	public Date getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public long getPlatVendorId() {
		return platVendorId;
	}

	public void setPlatVendorId(long platVendorId) {
		this.platVendorId = platVendorId;
	}

	public long getDemanderId() {
		return demanderId;
	}

	public void setDemanderId(long demanderId) {
		this.demanderId = demanderId;
	}

	public String getSourceWrNos() {
		return sourceWrNos;
	}

	public void setSourceWrNos(String sourceWrNos) {
		this.sourceWrNos = sourceWrNos;
	}

	public String getFinOrgUnitNo() {
		return finOrgUnitNo;
	}

	public void setFinOrgUnitNo(String finOrgUnitNo) {
		this.finOrgUnitNo = finOrgUnitNo;
	}
}
