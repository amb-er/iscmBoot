package com.armitage.server.api.business.invcounttask.params;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="CountInvTableParams",description="盘点表查询参数")
public class CountInvTableParams {
	@ApiModelProperty(value="任务单号",dataType="String",example="123",required=false)
	private String taskNo;

	@ApiModelProperty(value="仓库编号",dataType="String",example="0003",required=false)
	private String wareHouseNo;
	
	public String getTaskNo() {
		return taskNo;
	}
	public void setTaskNo(String taskNo) {
		this.taskNo = taskNo;
	}
	public String getWareHouseNo() {
		return wareHouseNo;
	}
	public void setWareHouseNo(String wareHouseNo) {
		this.wareHouseNo = wareHouseNo;
	}
	
}
