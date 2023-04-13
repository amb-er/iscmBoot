package com.armitage.server.api.business.purrequire.params;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="PurReqPersonParams",description="申请人查询参数")
public class PurReqPersonParams {
	@ApiModelProperty(value="申请人名称",dataType="String",example="张三",required=false)
	private String applicantsName;
	
	@ApiModelProperty(value="是否为获取默认申请人",dataType="int",example="001",required=false)
	private String defaultEmp;

	public String getApplicantsName() {
		return applicantsName;
	}

	public void setApplicantsName(String applicantsName) {
		this.applicantsName = applicantsName;
	}

	public String getDefaultEmp() {
		return defaultEmp;
	}

	public void setDefaultEmp(String defaultEmp) {
		this.defaultEmp = defaultEmp;
	}
	
}
