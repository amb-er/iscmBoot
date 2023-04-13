package com.armitage.server.api.business.invpurinwarehs.params;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="InvPurInWarehsUnAuditParams",description="反审批参数对象")
public class InvPurInWarehsUnAuditParams {
	@ApiModelProperty(value="入库单号",dataType="String",example="123",required=true)
	private String wrNo;
	
	@ApiModelProperty(value="审批备注",dataType="String",example="同意",required=false)
	private String opinionRemarks;

	public String getWrNo() {
		return wrNo;
	}

	public void setWrNo(String wrNo) {
		this.wrNo = wrNo;
	}

	public String getOpinionRemarks() {
		return opinionRemarks;
	}

	public void setOpinionRemarks(String opinionRemarks) {
		this.opinionRemarks = opinionRemarks;
	}
}
