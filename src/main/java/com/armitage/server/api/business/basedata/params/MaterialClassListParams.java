package com.armitage.server.api.business.basedata.params;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="MaterialClassListParams",description="查询列表参数对象")
public class MaterialClassListParams {
	@ApiModelProperty(value="混合查询条件",dataType="String",example="123",required=false)
	private String mixParam;
	
	@ApiModelProperty(value="物资级别",dataType="int",example="1",required=false)
	private int groupLevel;

	public String getMixParam() {
		return mixParam;
	}

	public void setMixParam(String mixParam) {
		this.mixParam = mixParam;
	}

	public int getGroupLevel() {
		return groupLevel;
	}

	public void setGroupLevel(int groupLevel) {
		this.groupLevel = groupLevel;
	}
	
}
