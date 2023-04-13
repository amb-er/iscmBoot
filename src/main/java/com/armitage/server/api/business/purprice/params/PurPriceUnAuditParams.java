package com.armitage.server.api.business.purprice.params;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="PurPriceUnAuditParams",description="反审批参数对象")
public class PurPriceUnAuditParams {
	@ApiModelProperty(value="定价单号",dataType="String",example="123",required=true)
	private String pmNo;
	
	@ApiModelProperty(value="审批备注",dataType="String",example="同意",required=false)
	private String opinionRemarks;

	public String getPmNo() {
		return pmNo;
	}

	public void setPmNo(String pmNo) {
		this.pmNo = pmNo;
	}

	public String getOpinionRemarks() {
		return opinionRemarks;
	}

	public void setOpinionRemarks(String opinionRemarks) {
		this.opinionRemarks = opinionRemarks;
	}
}
