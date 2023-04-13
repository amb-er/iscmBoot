package com.armitage.server.api.business.invcounttask.params;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="CountInvTaskWareHouseParams",description="盘点仓库查询参数")
public class CountInvTaskWareHouseParams {
	@ApiModelProperty(value="任务单号",dataType="String",example="123",required=false)
	private String taskNo;

	public String getTaskNo() {
		return taskNo;
	}
	public void setTaskNo(String taskNo) {
		this.taskNo = taskNo;
	}
}
