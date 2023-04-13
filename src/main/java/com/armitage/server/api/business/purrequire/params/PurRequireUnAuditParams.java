package com.armitage.server.api.business.purrequire.params;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="PurRequireUnAuditParams",description="反审批参数对象")
public class PurRequireUnAuditParams {
	@ApiModelProperty(value="请购单号",dataType="String",example="123",required=true)
	private String prNo;
	
	@ApiModelProperty(value="审批备注",dataType="String",example="同意",required=false)
	private String opinionRemarks;
	
	public String getPrNo() {
		return prNo;
	}

	public void setPrNo(String prNo) {
		this.prNo = prNo;
	}

	public String getOpinionRemarks() {
		return opinionRemarks;
	}

	public void setOpinionRemarks(String opinionRemarks) {
		this.opinionRemarks = opinionRemarks;
	}
}
