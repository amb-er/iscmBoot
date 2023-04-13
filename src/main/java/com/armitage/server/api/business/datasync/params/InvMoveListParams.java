package com.armitage.server.api.business.datasync.params;

import java.util.Date;
import java.util.List;

import com.armitage.server.api.config.StringDeserializer;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="InvMoveListParams",description="成本转移单对象")
public class InvMoveListParams {
	
	@JsonDeserialize(using = StringDeserializer.class)
	@ApiModelProperty(value="成本转移单号",dataType="String",example="MT20200606001",required=true)
	private String wtNo;
	
	@JsonDeserialize(using = StringDeserializer.class)
	@ApiModelProperty(value="调出部门",dataType="String",example="00002125",required=true)
	private String outOrgUnitNo;

	@JsonDeserialize(using = StringDeserializer.class)
	@ApiModelProperty(value="调入部门",dataType="String",example="00001996",required=true)
	private String inOrgUnitNo;
	
	@ApiModelProperty(value="业务日期",dataType="Date",example="2022-03-02 11:32:45",required=true)
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	private Date bizDate;
	
	@JsonDeserialize(using = StringDeserializer.class)
	@ApiModelProperty(value="状态",dataType="String",example="I",required=true)
	private String postStatus;
	
	@JsonDeserialize(using = StringDeserializer.class)
	@ApiModelProperty(value="是否删除",dataType="String",example="Y",required=true)
	private String delete;
	
	@JsonDeserialize(using = StringDeserializer.class)
	@ApiModelProperty(value="备注",dataType="String",example="成本转移单数据同步",required=false)
	private String remarks;
	
	@ApiModelProperty(value="成本转移单明细",dataType="List",required=true)
	private List<InvMoveDetailParams> detailList;

	
	
	public String getDelete() {
		return delete;
	}

	public void setDelete(String delete) {
		this.delete = delete;
	}

	public String getWtNo() {
		return wtNo;
	}

	public void setWtNo(String wtNo) {
		this.wtNo = wtNo;
	}

	public String getOutOrgUnitNo() {
		return outOrgUnitNo;
	}

	public void setOutOrgUnitNo(String outOrgUnitNo) {
		this.outOrgUnitNo = outOrgUnitNo;
	}

	public String getInOrgUnitNo() {
		return inOrgUnitNo;
	}

	public void setInOrgUnitNo(String inOrgUnitNo) {
		this.inOrgUnitNo = inOrgUnitNo;
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

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public List<InvMoveDetailParams> getDetailList() {
		return detailList;
	}

	public void setDetailList(List<InvMoveDetailParams> detailList) {
		this.detailList = detailList;
	}


	
	

}
