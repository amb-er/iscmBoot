package com.armitage.server.api.business.security.result;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="OrgAdminResult",description="返回结果集resultList")
public class OrgAdminResult {
	@ApiModelProperty(value="部门组织编号",dataType="String")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private String orgUnitNo;

	@ApiModelProperty(value="部门组织名称",dataType="String")
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
