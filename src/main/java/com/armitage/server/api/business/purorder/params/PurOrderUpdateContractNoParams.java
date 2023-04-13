package com.armitage.server.api.business.purorder.params;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="PurOrderUpdateContractNoParams",description="合同号更新参数对象")
public class PurOrderUpdateContractNoParams {
	@ApiModelProperty(value="单据编号",dataType="String",example="00001",required=true)
	private String billNo;

	@ApiModelProperty(value="合同编号",dataType="String",example="123",required=true)
	private String contractNo;
	
	public String getBillNo() {
		return billNo;
	}

	public void setBillNo(String billNo) {
		this.billNo = billNo;
	}

	public String getContractNo() {
		return contractNo;
	}

	public void setContractNo(String contractNo) {
		this.contractNo = contractNo;
	}
}
