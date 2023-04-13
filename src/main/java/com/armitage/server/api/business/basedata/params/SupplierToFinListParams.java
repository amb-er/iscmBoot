package com.armitage.server.api.business.basedata.params;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="SupplierToFinListParams",description="查询列表参数对象")
public class SupplierToFinListParams {
	@ApiModelProperty(value="控制单元",dataType="String",example="00000001",required=false)
	private String controlUnitNo;

	@ApiModelProperty(value="供应商状态",dataType="String",example="A",required=true)
	private String status;
	
	@ApiModelProperty(value="分组级别",dataType="Int",required=false)
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private int groupLevel;

	public String getControlUnitNo() {
		return controlUnitNo;
	}

	public void setControlUnitNo(String controlUnitNo) {
		this.controlUnitNo = controlUnitNo;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getGroupLevel() {
		return groupLevel;
	}

	public void setGroupLevel(int groupLevel) {
		this.groupLevel = groupLevel;
	}
}
