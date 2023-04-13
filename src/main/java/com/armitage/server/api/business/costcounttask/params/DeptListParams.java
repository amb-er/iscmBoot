package com.armitage.server.api.business.costcounttask.params;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="DeptListParams",description="部门查询参数")
public class DeptListParams {
	@ApiModelProperty(value="部门名称",dataType="String",example="中厨部",required=false)
	private String deptName;

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	
}
