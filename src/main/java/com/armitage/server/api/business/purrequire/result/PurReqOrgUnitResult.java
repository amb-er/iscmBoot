package com.armitage.server.api.business.purrequire.result;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="PurReqOrgUnitResult",description="申购组织结果集")
public class PurReqOrgUnitResult {
	@ApiModelProperty(value="申购组织编号",dataType="String")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private String orgUnitNo;

	@ApiModelProperty(value="申购组织名称",dataType="String")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private String orgUnitName;

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
	
}
