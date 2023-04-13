package com.armitage.server.api.business.invmaterialreq.params;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="InvReqMaterialListParams",description="查询列表参数对象")
public class InvReqMaterialListParams {
	@ApiModelProperty(value="库存组织",dataType="String",example="123",required=true)
	private String invOrgUnitNo;
	
	@ApiModelProperty(value="领料仓库",dataType="String",example="00001",required=true)
	private String wareHouseNo;
	
	@ApiModelProperty(value="混合查询条件",dataType="String",example="123",required=false)
	private String mixParam;

	public String getInvOrgUnitNo() {
		return invOrgUnitNo;
	}

	public void setInvOrgUnitNo(String invOrgUnitNo) {
		this.invOrgUnitNo = invOrgUnitNo;
	}

	public String getWareHouseNo() {
		return wareHouseNo;
	}

	public void setWareHouseNo(String wareHouseNo) {
		this.wareHouseNo = wareHouseNo;
	}

	public String getMixParam() {
		return mixParam;
	}

	public void setMixParam(String mixParam) {
		this.mixParam = mixParam;
	}
	
	
}
