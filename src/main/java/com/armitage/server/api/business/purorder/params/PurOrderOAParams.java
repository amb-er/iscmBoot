package com.armitage.server.api.business.purorder.params;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="PurOrderOAParams",description="订货单查询参数对象")
public class PurOrderOAParams {
	@ApiModelProperty(value="单据编号",dataType="String",example="00001",required=false)
	private String billNo;

	@ApiModelProperty(value="单据状态",dataType="String",example="A",required=false)
	private String billStatus;
	
	@ApiModelProperty(value="查询类型",dataType="String",example="1",required=false)
	private String type;

	public String getBillNo() {
		return billNo;
	}

	public void setBillNo(String billNo) {
		this.billNo = billNo;
	}

	public String getBillStatus() {
		return billStatus;
	}

	public void setBillStatus(String billStatus) {
		this.billStatus = billStatus;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
}
