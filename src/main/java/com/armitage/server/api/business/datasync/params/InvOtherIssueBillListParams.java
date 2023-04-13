package com.armitage.server.api.business.datasync.params;

import java.util.Date;
import java.util.List;

import com.armitage.server.api.config.StringDeserializer;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="InvOtherIssueBillListParams",description="其他出库单对象")
public class InvOtherIssueBillListParams {
	
	@JsonDeserialize(using = StringDeserializer.class)
	@ApiModelProperty(value="其他出库单号",dataType="String",example="MT20200606001",required=true)
	private String otNo;
	
//	@ApiModelProperty(value="库存组织",dataType="String",example="00002125",required=true)
//	private String orgUnitNo;
	
	@JsonDeserialize(using = StringDeserializer.class)
	@ApiModelProperty(value="仓库编码",dataType="String",example="00002125",required=true)
	private String whNo;
	
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
	private List<InvOtherIssueBillDetailParams> detailList;

	public String getOtNo() {
		return otNo;
	}

	public void setOtNo(String otNo) {
		this.otNo = otNo;
	}

//	public String getOrgUnitNo() {
//		return orgUnitNo;
//	}
//
//	public void setOrgUnitNo(String orgUnitNo) {
//		this.orgUnitNo = orgUnitNo;
//	}

	public String getWhNo() {
		return whNo;
	}

	public void setWhNo(String whNo) {
		this.whNo = whNo;
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

	public List<InvOtherIssueBillDetailParams> getDetailList() {
		return detailList;
	}

	public void setDetailList(List<InvOtherIssueBillDetailParams> detailList) {
		this.detailList = detailList;
	}

}
