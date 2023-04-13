package com.armitage.server.api.business.purrequire.params;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="PurReqWareHouseParams",description="存货仓库查询参数")
public class PurReqWareHouseParams {
	@ApiModelProperty(value="申购组织",dataType="String",example="00000531",required=false)
	private String reqOrgUnitNo;

	public String getReqOrgUnitNo() {
		return reqOrgUnitNo;
	}

	public void setReqOrgUnitNo(String reqOrgUnitNo) {
		this.reqOrgUnitNo = reqOrgUnitNo;
	}
	
}
