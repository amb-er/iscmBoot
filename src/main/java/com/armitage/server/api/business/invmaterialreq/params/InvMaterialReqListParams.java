package com.armitage.server.api.business.invmaterialreq.params;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="InvMaterialReqListParams",description="领料列表查询参数")
public class InvMaterialReqListParams {
	@ApiModelProperty(value="领料单号",dataType="String",example="123",required=false)
	private String reqNo;

	@ApiModelProperty(value="领料部门",dataType="String",example="0003",required=false)
	private String useDeptNo;

    @ApiModelProperty(value="领料人",dataType="String",example="0003",required=false)
	private String requestPerson;

    @ApiModelProperty(value="业务开始日期",dataType="Date",example="2019-05-22 00:00:00",required=false)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	private Date bizDateFrom;

    @ApiModelProperty(value="业务结束日期",dataType="Date",example="2019-05-22 00:00:00",required=false)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	private Date bizDateTo;

    @ApiModelProperty(value="单据状态",dataType="String",example="I：新建，D：待审，P：审核中，A：通过，B：部分通过，N：未通过，S：部分下达，E：下达，F：部分关闭，C：关闭，多个状态如：I,D,A",required=false)
	private String status;

    public String getReqNo() {
		return reqNo;
	}

	public void setReqNo(String reqNo) {
		this.reqNo = reqNo;
	}

	public String getUseDeptNo() {
		return useDeptNo;
	}

	public void setUseDeptNo(String useDeptNo) {
		this.useDeptNo = useDeptNo;
	}

	public String getRequestPerson() {
		return requestPerson;
	}

	public void setRequestPerson(String requestPerson) {
		this.requestPerson = requestPerson;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
    
}
