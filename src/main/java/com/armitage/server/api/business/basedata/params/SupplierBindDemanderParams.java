package com.armitage.server.api.business.basedata.params;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="SupplierBindDemanderParams",description="需求方绑定参数对象")
public class SupplierBindDemanderParams {
	
	@ApiModelProperty(value="需求方Id",dataType="Long",example="170",required=true)
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private long demanderId;
	
	@ApiModelProperty(value="需求方组织编码",dataType="String",example="123",required=true)
	private String orgUnitNo;

	public long getDemanderId() {
		return demanderId;
	}

	public void setDemanderId(long demanderId) {
		this.demanderId = demanderId;
	}

	public String getOrgUnitNo() {
		return orgUnitNo;
	}

	public void setOrgUnitNo(String orgUnitNo) {
		this.orgUnitNo = orgUnitNo;
	}

}
