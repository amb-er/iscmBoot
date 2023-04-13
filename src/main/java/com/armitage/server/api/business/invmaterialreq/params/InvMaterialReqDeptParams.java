package com.armitage.server.api.business.invmaterialreq.params;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="InvMaterialReqDeptParams",description="领料部门查询参数")
public class InvMaterialReqDeptParams {
	@ApiModelProperty(value="部门名称",dataType="String",example="123",required=false)
	private String deptName;

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

}
