package com.armitage.server.api.business.purrequire.params;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="PurRequireAuditDetailParams",description="明细表参数对象")
public class PurRequireAuditDetailParams {
	@ApiModelProperty(value="行号",dataType="Integer",example="1",required=true)
	private int lineId;
	
	@ApiModelProperty(value="行审批意见",dataType="String",example="agree",required=true)
	private String rowOpinion;

	@ApiModelProperty(value="备注",dataType="String",example="备注信息",required=true)
	private String remarks;

	public int getLineId() {
		return lineId;
	}

	public void setLineId(int lineId) {
		this.lineId = lineId;
	}

	public String getRowOpinion() {
		return rowOpinion;
	}

	public void setRowOpinion(String rowOpinion) {
		this.rowOpinion = rowOpinion;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
}
