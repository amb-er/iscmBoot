package com.armitage.server.api.business.purorder.params;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="PurOrderLockBillParams",description="锁单参数对象")
public class PurOrderLockBillParams {
	@ApiModelProperty(value="单据编号",dataType="String",example="00001",required=true)
	private String billNo;

	@ApiModelProperty(value="锁单类型",dataType="String",example="THD",required=false)
	private String lockType;
	
	@ApiModelProperty(value="单据状态",dataType="String",example="A",required=false)
	private String billStatus;

	public String getBillNo() {
		return billNo;
	}

	public void setBillNo(String billNo) {
		this.billNo = billNo;
	}

	public String getLockType() {
		return lockType;
	}

	public void setLockType(String lockType) {
		this.lockType = lockType;
	}

	public String getBillStatus() {
		return billStatus;
	}

	public void setBillStatus(String billStatus) {
		this.billStatus = billStatus;
	}
}
