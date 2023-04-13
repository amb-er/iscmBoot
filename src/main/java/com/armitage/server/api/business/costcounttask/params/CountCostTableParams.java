package com.armitage.server.api.business.costcounttask.params;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="CountCostTableParams",description="盘存表查询参数")
public class CountCostTableParams {
	@ApiModelProperty(value="任务单号",dataType="String",example="123",required=false)
	private String taskNo;

	@ApiModelProperty(value="部门编号",dataType="String",example="00000356",required=false)
	private String deptNo;
	
	public String getTaskNo() {
		return taskNo;
	}
	public void setTaskNo(String taskNo) {
		this.taskNo = taskNo;
	}
	public String getDeptNo() {
		return deptNo;
	}
	public void setDeptNo(String deptNo) {
		this.deptNo = deptNo;
	}
}
