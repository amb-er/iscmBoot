package com.armitage.server.api.business.basedata.params;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="SupplierOAParams",description="查询列表参数对象")
public class SupplierOAParams {
	
	@ApiModelProperty(value="供应商状态",dataType="String",example="A",required=false)
	private String supplierStatus;
	
	@ApiModelProperty(value="供应商资质状态",dataType="String",example="A",required=false)
	private String qualificationStatus;

	public String getSupplierStatus() {
		return supplierStatus;
	}

	public void setSupplierStatus(String supplierStatus) {
		this.supplierStatus = supplierStatus;
	}

	public String getQualificationStatus() {
		return qualificationStatus;
	}

	public void setQualificationStatus(String qualificationStatus) {
		this.qualificationStatus = qualificationStatus;
	}

}
