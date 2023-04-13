package com.armitage.server.api.business.purorder.params;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="PurOrderListParams",description="查询列表参数对象")
public class PurOrderListParams {
	@ApiModelProperty(value="订货单号",dataType="String",example="123",required=false)
	private String poNo;
	
	@ApiModelProperty(value="采购员",dataType="String",example="123",required=false)
	private String buyerId;
	
	@ApiModelProperty(value="订货开始日期",dataType="Date",example="2020-04-23 00:00:00",required=false)
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	private Date orderDateFrom;
	
	@ApiModelProperty(value="订货结束日期",dataType="Date",example="2020-04-23 00:00:00",required=false)
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	private Date orderDateTo;

	@ApiModelProperty(value="单据状态",dataType="String",example="I：新建，D：待审，P：审核中，A：通过，B：部分通过，N：未通过，S：部分下达，E：下达，F：部分关闭，C：关闭，多个状态如：I,D,A",required=false)
	private String status;
	
	@ApiModelProperty(value="供应商编号",dataType="String",example="1001",required=false)
	private String vendorCode;

	public String getPoNo() {
		return poNo;
	}

	public String getBuyerId() {
		return buyerId;
	}

	public void setBuyerId(String buyerId) {
		this.buyerId = buyerId;
	}

	public Date getOrderDateFrom() {
		return orderDateFrom;
	}

	public void setOrderDateFrom(Date orderDateFrom) {
		this.orderDateFrom = orderDateFrom;
	}

	public Date getOrderDateTo() {
		return orderDateTo;
	}

	public void setOrderDateTo(Date orderDateTo) {
		this.orderDateTo = orderDateTo;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getVendorCode() {
		return vendorCode;
	}

	public void setVendorCode(String vendorCode) {
		this.vendorCode = vendorCode;
	}

	public void setPoNo(String poNo) {
		this.poNo = poNo;
	}
	
}
