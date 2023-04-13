package com.armitage.server.api.business.basedata.params;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="InvOrgMaterialListParams",description="查询列表参数对象")
public class InvOrgMaterialListParams {

	@ApiModelProperty(value="库存组织",dataType="String",example="123",required=true)
	private String invOrgUnitNo;
	
	@ApiModelProperty(value="物资类别编码",dataType="String",example="123",required=false)
	private String classCode;
	
	@ApiModelProperty(value="混合查询条件",dataType="String",example="123",required=false)
	private String mixParam;


	public String getInvOrgUnitNo() {
		return invOrgUnitNo;
	}

	public void setInvOrgUnitNo(String invOrgUnitNo) {
		this.invOrgUnitNo = invOrgUnitNo;
	}

	public String getMixParam() {
		return mixParam;
	}

	public void setMixParam(String mixParam) {
		this.mixParam = mixParam;
	}

	public String getClassCode() {
		return classCode;
	}

	public void setClassCode(String classCode) {
		this.classCode = classCode;
	}
	
}
