package com.armitage.server.api.business.invmaterialreq.params;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="InvMaterialReqInvOrgUnitParams",description="领料库存组织查询参数对象")
public class InvMaterialReqInvOrgUnitParams {
	@ApiModelProperty(value="领料部门编号",dataType="String",example="00000004",required=false)
	private String deptNo;

	public String getDeptNo() {
		return deptNo;
	}

	public void setDeptNo(String deptNo) {
		this.deptNo = deptNo;
	}

	
}
