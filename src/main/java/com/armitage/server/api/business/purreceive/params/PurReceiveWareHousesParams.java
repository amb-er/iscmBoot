package com.armitage.server.api.business.purreceive.params;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="PurReceiveWareHousesParams",description="收货仓库查询参数")
public class PurReceiveWareHousesParams {
	@ApiModelProperty(value="收货库存组织",dataType="String",example="00000027",required=true)
	private String invOrgUnitNo;

	public String getInvOrgUnitNo() {
		return invOrgUnitNo;
	}

	public void setInvOrgUnitNo(String invOrgUnitNo) {
		this.invOrgUnitNo = invOrgUnitNo;
	}

}
