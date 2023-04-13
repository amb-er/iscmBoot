package com.armitage.server.api.business.security.params;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="UsrPendingBillQueryParams",description="审批单据查询对象")
public class UsrPendingBillQueryParams {
	@ApiModelProperty(value="用户编号",dataType="String",example="001",required=true)
	private String usrCode;
	
	@ApiModelProperty(value="提交开始日期",dataType="Date",example="2019-06-22 00:00:00",required=false)
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	private Date submitDateFrom;
	
	@ApiModelProperty(value="提交结束日期",dataType="Date",example="2019-07-22 00:00:00",required=false)
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	private Date submitDateTo;
	
	@ApiModelProperty(value="单据类型",dataType="String",example="PurRequire",required=false)
	private String billType;

	public String getUsrCode() {
		return usrCode;
	}

	public void setUsrCode(String usrCode) {
		this.usrCode = usrCode;
	}

	public Date getSubmitDateFrom() {
		return submitDateFrom;
	}

	public void setSubmitDateFrom(Date submitDateFrom) {
		this.submitDateFrom = submitDateFrom;
	}

	public Date getSubmitDateTo() {
		return submitDateTo;
	}

	public void setSubmitDateTo(Date submitDateTo) {
		this.submitDateTo = submitDateTo;
	}

	public String getBillType() {
		return billType;
	}

	public void setBillType(String billType) {
		this.billType = billType;
	}

	
}
