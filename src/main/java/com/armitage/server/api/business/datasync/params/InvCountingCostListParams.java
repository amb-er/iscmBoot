package com.armitage.server.api.business.datasync.params;

import java.util.Date;
import java.util.List;

import com.armitage.server.api.config.StringDeserializer;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="InvCountingCostListParams",description="盘存单对象")
public class InvCountingCostListParams {
	
	@JsonDeserialize(using = StringDeserializer.class)
	@ApiModelProperty(value="盘存单号",dataType="String",example="MT20200606001",required=true)
	private String taskNo;
	
	@JsonDeserialize(using = StringDeserializer.class)
	@ApiModelProperty(value="盘点部门",dataType="String",example="00002125",required=true)
	private String useOrgUnitNo;
	
	@ApiModelProperty(value="业务日期",dataType="Date",example="2022-03-02 11:32:45",required=true)
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	private Date bizDate;
	
	@JsonDeserialize(using = StringDeserializer.class)
	@ApiModelProperty(value="状态",dataType="String",example="I",required=true)
	private String postStatus;
	
	@JsonDeserialize(using = StringDeserializer.class)
	@ApiModelProperty(value="是否删除",dataType="String",example="Y",required=true)
	private String delete;
	
	@ApiModelProperty(value="备注",dataType="String",example="成本耗用单数据同步",required=false)
	private String remarks;
	
	@ApiModelProperty(value="其他入库单明细",dataType="List",required=true)
	private List<InvCountingCostDetailParams> detailList;

	public String getTaskNo() {
		return taskNo;
	}

	public void setTaskNo(String taskNo) {
		this.taskNo = taskNo;
	}

	public String getUseOrgUnitNo() {
		return useOrgUnitNo;
	}

	public void setUseOrgUnitNo(String useOrgUnitNo) {
		this.useOrgUnitNo = useOrgUnitNo;
	}

	public Date getBizDate() {
		return bizDate;
	}

	public void setBizDate(Date bizDate) {
		this.bizDate = bizDate;
	}

	public String getPostStatus() {
		return postStatus;
	}

	public void setPostStatus(String postStatus) {
		this.postStatus = postStatus;
	}

	public String getDelete() {
		return delete;
	}

	public void setDelete(String delete) {
		this.delete = delete;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public List<InvCountingCostDetailParams> getDetailList() {
		return detailList;
	}

	public void setDetailList(List<InvCountingCostDetailParams> detailList) {
		this.detailList = detailList;
	}
	
}
