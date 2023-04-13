package com.armitage.server.api.business.basedata.params;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="MaterialDetailedParams",description="查询列表参数对象")
public class MaterialDetailedParams {
	@ApiModelProperty(value="管理单元",dataType="String",example="123",required=false)
	private String controlUnitNo;
	
	@ApiModelProperty(value="物资级别",dataType="int",example="123",required=false)
	private int groupLevel;

	public String getControlUnitNo() {
		return controlUnitNo;
	}

	public void setControlUnitNo(String controlUnitNo) {
		this.controlUnitNo = controlUnitNo;
	}

	public int getGroupLevel() {
		return groupLevel;
	}

	public void setGroupLevel(int groupLevel) {
		this.groupLevel = groupLevel;
	}

}
