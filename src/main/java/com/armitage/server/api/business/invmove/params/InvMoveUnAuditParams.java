package com.armitage.server.api.business.invmove.params;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="InvMoveUnAuditParams",description="反审批参数对象")
public class InvMoveUnAuditParams {
	@ApiModelProperty(value="移库单号",dataType="String",example="123",required=true)
	private String wtNo;
	
	@ApiModelProperty(value="审批备注",dataType="String",example="同意",required=false)
	private String opinionRemarks;

	public String getWtNo() {
		return wtNo;
	}

	public void setWtNo(String wtNo) {
		this.wtNo = wtNo;
	}

	public String getOpinionRemarks() {
		return opinionRemarks;
	}

	public void setOpinionRemarks(String opinionRemarks) {
		this.opinionRemarks = opinionRemarks;
	}
}
