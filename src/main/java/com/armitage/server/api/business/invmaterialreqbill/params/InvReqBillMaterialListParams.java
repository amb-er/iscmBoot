package com.armitage.server.api.business.invmaterialreqbill.params;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="InvReqBillMaterialListParams",description="查询列表参数对象")
public class InvReqBillMaterialListParams {
	
	@ApiModelProperty(value="业务类型",dataType="String",example="00001",required=true)
	private String bizType;
	
	@ApiModelProperty(value="领料仓库",dataType="String",example="00001",required=true)
	private String wareHouseNo;
	
	@ApiModelProperty(value="领料部门",dataType="String",example="123",required=true)
	private String useDeptNo;
	
	@ApiModelProperty(value="混合查询条件",dataType="String",example="123",required=false)
	private String mixParam;

	public String getBizType() {
		return bizType;
	}

	public void setBizType(String bizType) {
		this.bizType = bizType;
	}

	public String getWareHouseNo() {
		return wareHouseNo;
	}

	public void setWareHouseNo(String wareHouseNo) {
		this.wareHouseNo = wareHouseNo;
	}

	public String getUseDeptNo() {
		return useDeptNo;
	}

	public void setUseDeptNo(String useDeptNo) {
		this.useDeptNo = useDeptNo;
	}

	public String getMixParam() {
		return mixParam;
	}

	public void setMixParam(String mixParam) {
		this.mixParam = mixParam;
	}

}
