package com.armitage.server.api.business.datasync.params;

import com.armitage.server.api.config.StringDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="ScmPurRequireBillQueryParams",description="请购单查询对象")
public class ScmPurRequireBillQueryParams {
	
	@JsonDeserialize(using = StringDeserializer.class)
	@ApiModelProperty(value="请购单号",dataType="String",example="PR20220308035",required=false)
	private String prNo;
	@ApiModelProperty(value="开始日期",dataType="String",example="2010-05-23 17:40:16",required=false)
	private String begDate;
	@ApiModelProperty(value="结束日期",dataType="String",example="2025-05-23 17:40:16",required=false)
	private String endDate;
	@ApiModelProperty(value="单据状态",dataType="String",example="E",required=false)
	private String status;

	
	public String getPrNo() {
		return prNo;
	}
	public void setPrNo(String prNo) {
		this.prNo = prNo;
	}
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

}
