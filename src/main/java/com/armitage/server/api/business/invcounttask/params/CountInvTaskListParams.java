package com.armitage.server.api.business.invcounttask.params;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="CountInvTaskListParams",description="盘点列表查询参数")
public class CountInvTaskListParams {
	@ApiModelProperty(value="任务单号",dataType="String",example="123",required=false)
	private String taskNo;
	
    @ApiModelProperty(value="仓库编号",dataType="String",example="0003",required=false)
	private String wareHouseNo;
    
    @ApiModelProperty(value="业务开始日期",dataType="Date",example="2019-05-22 00:00:00",required=false)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	private Date bizDateFrom;
    @ApiModelProperty(value="业务结束日期",dataType="Date",example="2019-05-22 00:00:00",required=false)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	private Date bizDateTo;
    
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
	public Date getBizDateFrom() {
		return bizDateFrom;
	}
	public void setBizDateFrom(Date bizDateFrom) {
		this.bizDateFrom = bizDateFrom;
	}
	public Date getBizDateTo() {
		return bizDateTo;
	}
	public void setBizDateTo(Date bizDateTo) {
		this.bizDateTo = bizDateTo;
	}
	
}
