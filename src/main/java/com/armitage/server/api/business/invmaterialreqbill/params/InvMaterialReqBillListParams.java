package com.armitage.server.api.business.invmaterialreqbill.params;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="InvMaterialReqBillListParams",description="查询列表参数对象")
public class InvMaterialReqBillListParams {

	@ApiModelProperty(value="领料出库单号",dataType="String",example="123",required=false)
	private String reqBillNo;
	
	@ApiModelProperty(value="领料仓库",dataType="String",example="123",required=false)
	private String wareHouseNo;
	
	@ApiModelProperty(value="领料部门",dataType="String",example="123",required=false)
	private String useDeptNo;

	@ApiModelProperty(value="领料人",dataType="String",example="W00001",required=false)
	private String requestPerson;
	
	@ApiModelProperty(value="开始业务日期",dataType="Date",example="yyyy-MM-dd 00:00:00",required=false)
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	private Date bizDateFrom;
	
	@ApiModelProperty(value="结束业务日期",dataType="Date",example="yyyy-MM-dd 00:00:00",required=false)
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	private Date bizDateTo;

	@ApiModelProperty(value="单据状态",dataType="String",example="I：新建，D：待审，P：审核中，A：通过，B：部分通过，N：未通过，S：部分下达，E：下达，F：部分关闭，C：关闭，多个状态如：I,D,A",required=false)
	private String status;
	
	@ApiModelProperty(value="业务类型",dataType="String",example=":1:领料出库,2:领料退仓",required=false)
	private String bizType;

	public String getReqBillNo() {
		return reqBillNo;
	}

	public void setReqBillNo(String reqBillNo) {
		this.reqBillNo = reqBillNo;
	}

	public String getWareHouseNo() {
		return wareHouseNo;
	}

	public void setWareHouseNo(String wareHouseNo) {
		this.wareHouseNo = wareHouseNo;
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

	public String getBizType() {
		return bizType;
	}

	public void setBizType(String bizType) {
		this.bizType = bizType;
	}
	
}
