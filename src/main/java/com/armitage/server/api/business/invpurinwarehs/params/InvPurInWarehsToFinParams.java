package com.armitage.server.api.business.invpurinwarehs.params;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


@ApiModel(value="InvPurInWarehsToFinParams",description="查询列表参数对象")
public class InvPurInWarehsToFinParams {
	@ApiModelProperty(value="开始日期",dataType="Date",example="yyyy-MM-dd 00:00:00")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private String begDate;
	
	@ApiModelProperty(value="结束日期",dataType="Date",example="yyyy-MM-dd 00:00:00")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private String endDate;
	
	@ApiModelProperty(value="财务组织",dataType="String",required=true)
	private String finOrgUnitNo;
	
	@ApiModelProperty(value="业务类型",dataType="String",required=false)
	private String bizType;
	
	@ApiModelProperty(value="汇总级别",dataType="Int",required=true)
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private int summaryLevel;
	
	@ApiModelProperty(value="部门或者仓库",dataType="String",required=false)
	private String HoudeIdOrUnitNo;
	
	@ApiModelProperty(value="单据状态",dataType="String",required=true)
	private String status;

	public String getBegDate() {
		return begDate;
	}

	public void setBegDate(String begDate) {
		this.begDate = begDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getFinOrgUnitNo() {
		return finOrgUnitNo;
	}

	public void setFinOrgUnitNo(String finOrgUnitNo) {
		this.finOrgUnitNo = finOrgUnitNo;
	}

	public String getBizType() {
		return bizType;
	}

	public void setBizType(String bizType) {
		this.bizType = bizType;
	}

	public int getSummaryLevel() {
		return summaryLevel;
	}

	public void setSummaryLevel(int summaryLevel) {
		this.summaryLevel = summaryLevel;
	}

	public String getHoudeIdOrUnitNo() {
		return HoudeIdOrUnitNo;
	}

	public void setHoudeIdOrUnitNo(String houdeIdOrUnitNo) {
		HoudeIdOrUnitNo = houdeIdOrUnitNo;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	
	
	
}
