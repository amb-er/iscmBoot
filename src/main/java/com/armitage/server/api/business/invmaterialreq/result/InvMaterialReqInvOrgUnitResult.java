package com.armitage.server.api.business.invmaterialreq.result;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@ApiModel(value="InvMaterialReqInvOrgUnitResult",description="领料库存组织查询返回结果")
public class InvMaterialReqInvOrgUnitResult {
	
	@ApiModelProperty(value="库存组织编号",dataType="String")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private String invOrgUnitNo;
	
	@ApiModelProperty(value="库存组织名称",dataType="String")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private String invOrgUnitName;

	public String getInvOrgUnitNo() {
		return invOrgUnitNo;
	}

	public void setInvOrgUnitNo(String invOrgUnitNo) {
		this.invOrgUnitNo = invOrgUnitNo;
	}

	public String getInvOrgUnitName() {
		return invOrgUnitName;
	}

	public void setInvOrgUnitName(String invOrgUnitName) {
		this.invOrgUnitName = invOrgUnitName;
	}

}
