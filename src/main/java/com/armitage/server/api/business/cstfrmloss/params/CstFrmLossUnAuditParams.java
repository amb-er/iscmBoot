package com.armitage.server.api.business.cstfrmloss.params;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="CstFrmLossUnAuditParams",description="反审批参数对象")
public class CstFrmLossUnAuditParams {
	@ApiModelProperty(value="报损单号",dataType="String",example="123",required=true)
	private String billNo;
	
	@ApiModelProperty(value="审批备注",dataType="String",example="同意",required=false)
	private String opinionRemarks;

	public String getBillNo() {
		return billNo;
	}

	public void setBillNo(String billNo) {
		this.billNo = billNo;
	}

	public String getOpinionRemarks() {
		return opinionRemarks;
	}

	public void setOpinionRemarks(String opinionRemarks) {
		this.opinionRemarks = opinionRemarks;
	}
}
