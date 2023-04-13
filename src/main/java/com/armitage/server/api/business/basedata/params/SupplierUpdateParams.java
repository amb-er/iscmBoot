package com.armitage.server.api.business.basedata.params;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="SupplierUpdateParams",description="供应商修改参数")
public class SupplierUpdateParams {
	
	@ApiModelProperty(value="业务组织编码",dataType="String",example="00000075",required=true)
	private String orgunitNo;
	@ApiModelProperty(value="外部系统档案编码",dataType="String",example="1",required=false)
	private String externalCode;
	@ApiModelProperty(value="千里马档案编码",dataType="String",example="1",required=false)
	private String vendorNo;
	@ApiModelProperty(value="供应商名称",dataType="String",example="1",required=true)
	private String vendorName;
	
	public String getOrgunitNo() {
		return orgunitNo;
	}
	public void setOrgunitNo(String orgunitNo) {
		this.orgunitNo = orgunitNo;
	}
	public String getExternalCode() {
		return externalCode;
	}
	public void setExternalCode(String externalCode) {
		this.externalCode = externalCode;
	}
	public String getVendorNo() {
		return vendorNo;
	}
	public void setVendorNo(String vendorNo) {
		this.vendorNo = vendorNo;
	}
	public String getVendorName() {
		return vendorName;
	}
	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}

}
