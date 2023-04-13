package com.armitage.server.api.business.datasync.params;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="ScmInvPurInWarehsBillListQueryParams",description="采购入库查询参数对象")
public class ScmInvPurInWarehsBillQueryParams {

	@ApiModelProperty(value="采购入库单号",dataType="String",example="WRWR2020102620201026002",required=false)
	private String wrNo;
	@ApiModelProperty(value="开始日期",dataType="String",example="2010-05-23 17:40:16",required=false)
	private String begDate;
	@ApiModelProperty(value="结束日期",dataType="String",example="2030-05-23 17:40:16",required=false)
	private String endDate;
	@ApiModelProperty(value="单据状态",dataType="String",example="E",required=false)
	private String status;
	@ApiModelProperty(value="供应商编号",dataType="String",example="00035",required=false)
	private String vendorNo;
	public String getWrNo() {
		return wrNo;
	}
	public void setWrNo(String wrNo) {
		this.wrNo = wrNo;
	}
	public String getBegDate() {
		return begDate;
	}
	public void setBegDate(String begDate) {
		this.begDate = begDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getVendorNo() {
		return vendorNo;
	}
	public void setVendorNo(String vendorNo) {
		this.vendorNo = vendorNo;
	}

	
	
	
	
}
