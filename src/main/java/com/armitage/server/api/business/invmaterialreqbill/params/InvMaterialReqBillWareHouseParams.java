package com.armitage.server.api.business.invmaterialreqbill.params;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="InvMaterialReqBillWareHouseParams",description="获取出库仓库列表参数对象")
public class InvMaterialReqBillWareHouseParams {
	@ApiModelProperty(value="仓库名称",dataType="String",example="123",required=false)
	private String wareHouseName;

	public String getWareHouseName() {
		return wareHouseName;
	}

	public void setWareHouseName(String wareHouseName) {
		this.wareHouseName = wareHouseName;
	}

}
