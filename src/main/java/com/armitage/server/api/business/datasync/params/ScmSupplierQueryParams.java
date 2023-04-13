package com.armitage.server.api.business.datasync.params;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="ScmScmSupplierQueryParams",description="供应商查询参数对象")
public class ScmSupplierQueryParams {
	@ApiModelProperty(value="供应商编号",dataType="String",example="00013",required=false)
	private String vendorNo;
	
	@ApiModelProperty(value="供应商类别编号",dataType="String",example="102",required=false)
	private String vendorClassNo;
	
	@ApiModelProperty(value="创建日期起",dataType="String(date-time)",example="2010-05-14 18:27:45",required=false)
	private String createStartDate;
	
	@ApiModelProperty(value="创建日期止",dataType="String(date-time)",example="2025-05-14 20:27:45",required=false)
	private String createEndDate;
	
	@ApiModelProperty(value="最后修改时间起",dataType="String(date-time)",example="2010-05-14 18:27:45",required=false)
	private String editStartDate;
	
	@ApiModelProperty(value="最后修改时间止",dataType="String(date-time)",example="2025-05-14 20:27:45",required=false)
	private String editEndDate;
	
	@ApiModelProperty(value="状态",dataType="String",example="A",required=false)
	private String status;

	public String getVendorNo() {
		return vendorNo;
	}

	public void setVendorNo(String vendorNo) {
		this.vendorNo = vendorNo;
	}

	public String getVendorClassNo() {
		return vendorClassNo;
	}

	public void setVendorClassNo(String vendorClassNo) {
		this.vendorClassNo = vendorClassNo;
	}

	public String getCreateStartDate() {
		return createStartDate;
	}

	public void setCreateStartDate(String createStartDate) {
		this.createStartDate = createStartDate;
	}

	public String getCreateEndDate() {
		return createEndDate;
	}

	public void setCreateEndDate(String createEndDate) {
		this.createEndDate = createEndDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getEditStartDate() {
		return editStartDate;
	}

	public void setEditStartDate(String editStartDate) {
		this.editStartDate = editStartDate;
	}

	public String getEditEndDate() {
		return editEndDate;
	}

	public void setEditEndDate(String editEndDate) {
		this.editEndDate = editEndDate;
	}
	
	
	

}
