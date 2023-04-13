package com.armitage.server.api.business.audit.params;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="ApinvoiceLockBillParams",description="锁单参数对象")
public class AuditStatusParams {
	@ApiModelProperty(value="单据类型",dataType="String",required=false)
	private String billTypeCode;

	@ApiModelProperty(value="单据编号",dataType="String",example="00001",required=true)
	private String billNo;

	public String getBillTypeCode() {
		return billTypeCode;
	}

	public void setBillTypeCode(String billTypeCode) {
		this.billTypeCode = billTypeCode;
	}

	public String getBillNo() {
		return billNo;
	}

	public void setBillNo(String billNo) {
		this.billNo = billNo;
	}
}
