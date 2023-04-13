package com.armitage.server.api.business.datareport.params;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="DeptConsumeEntryDataParams",description="锁单参数对象")
public class DeptConsumeEntryDataParams {
	
	@ApiModelProperty(value="开始日期",dataType="Date",example="yyyy-MM-dd 00:00:00")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private Date begDate;
	
	@ApiModelProperty(value="结束日期",dataType="Date",example="yyyy-MM-dd 00:00:00")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private Date endDate;
	
	@ApiModelProperty(value="财务组织",dataType="String",example="00000001",required=true)
	private String finOrgUnitNo;
	
	@ApiModelProperty(value="汇总级别",dataType="Int",required=true)
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private int summaryLevel;
	
	@ApiModelProperty(value="查询类型，1：结存及入账，2：领料",dataType="String",example="1",required=true)
	private String type;

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

	public String getFinOrgUnitNo() {
		return finOrgUnitNo;
	}

	public void setFinOrgUnitNo(String finOrgUnitNo) {
		this.finOrgUnitNo = finOrgUnitNo;
	}

	public int getSummaryLevel() {
		return summaryLevel;
	}

	public void setSummaryLevel(int summaryLevel) {
		this.summaryLevel = summaryLevel;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
}
