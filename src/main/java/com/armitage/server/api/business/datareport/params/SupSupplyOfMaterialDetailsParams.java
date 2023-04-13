package com.armitage.server.api.business.datareport.params;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="SupSupplyOfMaterialDetailsParams",description="供应商供货明细查询参数对象")
public class SupSupplyOfMaterialDetailsParams {
	@ApiModelProperty(value="财务组织",dataType="String",example="00000001",required=true)
	private String invOrgUnitNo;
	
	@ApiModelProperty(value="单号",dataType="String",example="00000001")
	private String wrNo;
	
	@ApiModelProperty(value="业务类型",dataType="String",example="00000001")
	private String bizType;
	
	@ApiModelProperty(value="开始日期",dataType="Date",example="yyyy-MM-dd 00:00:00",required=true)
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private Date begDate;
	
	@ApiModelProperty(value="结束日期",dataType="Date",example="yyyy-MM-dd 00:00:00",required=true)
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private Date endDate;

	@ApiModelProperty(value="类别级别",dataType="Int",example="1:一级;2:二级;3:三级...",required=false)
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private int summaryLevel;

	public String getInvOrgUnitNo() {
		return invOrgUnitNo;
	}

	public void setInvOrgUnitNo(String invOrgUnitNo) {
		this.invOrgUnitNo = invOrgUnitNo;
	}

	public String getWrNo() {
		return wrNo;
	}

	public void setWrNo(String wrNo) {
		this.wrNo = wrNo;
	}

	public String getBizType() {
		return bizType;
	}

	public void setBizType(String bizType) {
		this.bizType = bizType;
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

	public int getSummaryLevel() {
		return summaryLevel;
	}

	public void setSummaryLevel(int summaryLevel) {
		this.summaryLevel = summaryLevel;
	}
	
}
