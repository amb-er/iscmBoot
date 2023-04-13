package com.armitage.server.api.business.security.result;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="LoginOrgUnitResult",description="可登录组织结果")
public class LoginOrgUnitResult {
	
	@ApiModelProperty(value="组织编号",dataType="String")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private String orgUnitNo;
	
	@ApiModelProperty(value="组织名称",dataType="String")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private String orgUnitName;
	
	@ApiModelProperty(value="所属管理单元",dataType="String")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private String controlUnitNo;
	
	@ApiModelProperty(value="是否管理单元",dataType="Boolean")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private boolean cu;

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

	public String getControlUnitNo() {
		return controlUnitNo;
	}

	public void setControlUnitNo(String controlUnitNo) {
		this.controlUnitNo = controlUnitNo;
	}

	public boolean isCu() {
		return cu;
	}

	public void setCu(boolean cu) {
		this.cu = cu;
	}
}
