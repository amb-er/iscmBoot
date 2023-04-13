package com.armitage.server.api.business.invmaterialreqbill.params;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="InvMaterialReqBillDeptParams",description="获取领料部门列表参数对象")
public class InvMaterialReqBillDeptParams {

	@ApiModelProperty(value="部门名称",dataType="String",example="123",required=false)
	private String deptName;

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	
}
