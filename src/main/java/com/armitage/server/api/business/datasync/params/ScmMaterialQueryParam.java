package com.armitage.server.api.business.datasync.params;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="ScmScmMaterialQueryParams",description="物资编码查询参数对象")
public class ScmMaterialQueryParam {
	
	@ApiModelProperty(value="物资编码",dataType="String",example="10001001",required=false)
	private String itemNo;
	@ApiModelProperty(value="物资类别编码",dataType="String",example="10001",required=false)
	private String itemClassNo;
	@ApiModelProperty(value="物资创建时间起",dataType="String",example="2010-05-23 17:40:16",required=false)
	private String createStartDate;
	@ApiModelProperty(value="物资创建时间止",dataType="String",example="2030-05-23 17:40:16",required=false)
	private String createEndDate;
	@ApiModelProperty(value="物资状态",dataType="String",example="A",required=false)
	private String status;
	@ApiModelProperty(value="最后修改时间起",dataType="String",example="2010-05-23 17:40:16",required=false)
	private String editStartDate;
	@ApiModelProperty(value="最后修改时间止",dataType="String",example="2030-05-23 17:40:16",required=false)
	private String editEndDate;

	public String getItemNo() {
		return itemNo;
	}
	public void setItemNo(String itemNo) {
		this.itemNo = itemNo;
	}
	public String getItemClassNo() {
		return itemClassNo;
	}
	public void setItemClassNo(String itemClassNo) {
		this.itemClassNo = itemClassNo;
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
