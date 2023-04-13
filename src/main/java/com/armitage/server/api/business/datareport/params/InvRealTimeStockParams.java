package com.armitage.server.api.business.datareport.params;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="InvRealTimeStockParams",description="实时结存查询参数")
public class InvRealTimeStockParams {
	@ApiModelProperty(value="仓库编号",dataType="String",example="123",required=false)
	private String wareHouseNo;

	@ApiModelProperty(value="物资分类",dataType="String",example="001",required=false)
	private String itemClass;
    
	@ApiModelProperty(value="物资编号",dataType="String",example="1020001",required=false)
	private String itemNo;
	
	@ApiModelProperty(value="供应商编号",dataType="String",example="V0001",required=false)
	private String vendorCode;

	public String getWareHouseNo() {
		return wareHouseNo;
	}

	public void setWareHouseNo(String wareHouseNo) {
		this.wareHouseNo = wareHouseNo;
	}

	public String getItemClass() {
		return itemClass;
	}

	public void setItemClass(String itemClass) {
		this.itemClass = itemClass;
	}

	public String getItemNo() {
		return itemNo;
	}

	public void setItemNo(String itemNo) {
		this.itemNo = itemNo;
	}

	public String getVendorCode() {
		return vendorCode;
	}

	public void setVendorCode(String vendorCode) {
		this.vendorCode = vendorCode;
	}

}
