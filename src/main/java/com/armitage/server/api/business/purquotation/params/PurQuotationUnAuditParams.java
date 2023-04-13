package com.armitage.server.api.business.purquotation.params;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="PurQuotationUnAuditParams",description="反审批参数对象")
public class PurQuotationUnAuditParams {
	@ApiModelProperty(value="报价单号",dataType="String",example="123",required=true)
	private String pqNo;
	
	@ApiModelProperty(value="审批备注",dataType="String",example="同意",required=false)
	private String opinionRemarks;

	public String getPqNo() {
		return pqNo;
	}

	public void setPqNo(String pqNo) {
		this.pqNo = pqNo;
	}

	public String getOpinionRemarks() {
		return opinionRemarks;
	}

	public void setOpinionRemarks(String opinionRemarks) {
		this.opinionRemarks = opinionRemarks;
	}
}
