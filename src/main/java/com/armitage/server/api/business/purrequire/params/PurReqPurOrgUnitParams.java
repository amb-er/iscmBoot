package com.armitage.server.api.business.purrequire.params;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="PurReqPurOrgUnitParams",description="采购组织查询参数")
public class PurReqPurOrgUnitParams {
	@ApiModelProperty(value="申购组织",dataType="String",example="00000254",required=true)
	private String reqOrgUnitNo;

	public String getReqOrgUnitNo() {
		return reqOrgUnitNo;
	}

	public void setReqOrgUnitNo(String reqOrgUnitNo) {
		this.reqOrgUnitNo = reqOrgUnitNo;
	}
	
}
