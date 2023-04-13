package com.armitage.server.api.business.basedata.params;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="WareHouseParams",description="仓库查询参数")
public class WareHouseParams {
	@ApiModelProperty(value="仓库名称",dataType="String",example="调料",required=false)
	private String wareHouseName;

	public String getWareHouseName() {
		return wareHouseName;
	}

	public void setWareHouseName(String wareHouseName) {
		this.wareHouseName = wareHouseName;
	}

}
