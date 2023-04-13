package com.armitage.server.api.business.invcounttask.params;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="CountInvTaskDiffParams",description="盘点差异查询参数")
public class CountInvTaskDiffParams {
	@ApiModelProperty(value="任务单号",dataType="String",example="T000123",required=true)
	private String taskNo;
	@ApiModelProperty(value="汇总显示",dataType="Boolean",required=false)
	private boolean showSum=false;

	public String getTaskNo() {
		return taskNo;
	}
	public void setTaskNo(String taskNo) {
		this.taskNo = taskNo;
	}
	public boolean isShowSum() {
		return showSum;
	}
	public void setShowSum(boolean showSum) {
		this.showSum = showSum;
	}
}
