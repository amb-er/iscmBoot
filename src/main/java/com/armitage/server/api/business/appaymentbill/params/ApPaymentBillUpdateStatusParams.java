package com.armitage.server.api.business.appaymentbill.params;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="ApPaymentBillUpdateStatusParams",description="单据状态回写参数对象")
public class ApPaymentBillUpdateStatusParams {
	@ApiModelProperty(value="单据编号",dataType="String",example="00001",required=true)
	private String billNo;

	@ApiModelProperty(value="单据状态",dataType="String",example="A",required=true)
	private String billStatus;
	
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

}
