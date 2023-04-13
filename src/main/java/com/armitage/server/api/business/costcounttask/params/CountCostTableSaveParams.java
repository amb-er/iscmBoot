package com.armitage.server.api.business.costcounttask.params;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.List;

@ApiModel(value="CountCostTableSaveParams",description="盘存部门查询参数")
public class CountCostTableSaveParams {
	@ApiModelProperty(value="任务单号",dataType="String",example="T00001",required=false)
	private String taskNo;

	@ApiModelProperty(value="部门编号",dataType="String",example="00000531",required=false)
	private String deptNo;

	@ApiModelProperty(value="单据明细",dataType="List")
	private List<CountCostTableSaveDetailParams> detailList;
	
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
	public List<CountCostTableSaveDetailParams> getDetailList() {
		return detailList;
	}
	public void setDetailList(List<CountCostTableSaveDetailParams> detailList) {
		this.detailList = detailList;
	}
	
}
