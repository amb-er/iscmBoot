package com.armitage.server.api.business.invcounttask.params;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.List;

@ApiModel(value="CountInvTableSaveParams",description="盘点表保存参数")
public class CountInvTableSaveParams {
	@ApiModelProperty(value="任务单号",dataType="String",example="T00001",required=true)
	private String taskNo;

	@ApiModelProperty(value="仓库编号",dataType="String",example="0003",required=true)
	private String wareHouseNo;

	@ApiModelProperty(value="单据明细",dataType="List")
	private List<CountInvTableSaveDetailParams> detailList;
	
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
	public List<CountInvTableSaveDetailParams> getDetailList() {
		return detailList;
	}
	public void setDetailList(List<CountInvTableSaveDetailParams> detailList) {
		this.detailList = detailList;
	}
	
}
