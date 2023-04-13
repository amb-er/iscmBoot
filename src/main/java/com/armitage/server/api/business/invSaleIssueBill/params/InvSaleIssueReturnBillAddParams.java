package com.armitage.server.api.business.invSaleIssueBill.params;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="InvSaleIssueReturnBillAddParams",description="新增参数对象")
public class InvSaleIssueReturnBillAddParams {
	@ApiModelProperty(value="业务日期",dataType="Date",example="2019-05-22 00:00:00",required=true)
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	private Date bizDate;
	
	@ApiModelProperty(value="库存组织",dataType="String",example="123",required=true)
	private String invOrgUnitNo;
	
	@ApiModelProperty(value="第三方单号",dataType="String",example="123",required=true)
	private String otherNo;
	
	@ApiModelProperty(value="备注",dataType="String",example="123",required=false)
	private String remarks;
	
	@ApiModelProperty(value="单据明细",dataType="List<InvSaleIssueBillDetailParams>",example="",required=true)
	private List<InvSaleIssueBillDetailParams> detailList;

	public String getInvOrgUnitNo() {
		return invOrgUnitNo;
	}

	public void setInvOrgUnitNo(String invOrgUnitNo) {
		this.invOrgUnitNo = invOrgUnitNo;
	}

	public Date getBizDate() {
		return bizDate;
	}

	public void setBizDate(Date bizDate) {
		this.bizDate = bizDate;
	}
	
	public String getOtherNo() {
		return otherNo;
	}

	public void setOtherNo(String otherNo) {
		this.otherNo = otherNo;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public List<InvSaleIssueBillDetailParams> getDetailList() {
		return detailList;
	}

	public void setDetailList(List<InvSaleIssueBillDetailParams> detailList) {
		this.detailList = detailList;
	}

}

