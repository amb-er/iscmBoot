package com.armitage.server.api.business.purreceive.params;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="PurReceiveListParams",description="查询列表参数对象")
public class PurReceiveListParams {
	@ApiModelProperty(value="收货购单号",dataType="String",example="PV20191031001",required=false)
	private String pvNo;
	
	@ApiModelProperty(value="采购组织",dataType="String",example="00000351",required=false)
	private String purOrgUnitNo;
	
	@ApiModelProperty(value="供应商编号",dataType="String",example="V0001",required=false)
	private String vendorCode;
	
	@ApiModelProperty(value="收货人编号",dataType="String",example="0001",required=false)
	private String receiver;

	@ApiModelProperty(value="申购开始日期",dataType="Date",example="2019-05-22 00:00:00",required=false)
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	private Date recDateFrom;
	
	@ApiModelProperty(value="申购结束日期",dataType="Date",example="2019-05-22 00:00:00",required=false)
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	private Date recDateTo;

	@ApiModelProperty(value="单据状态",dataType="String",example="I：新建，D：待审，P：审核中，A：通过，B：部分通过，N：未通过，S：部分下达，E：下达，F：部分关闭，C：关闭，多个状态如：I,D,A",required=false)
	private String status;

	public String getPvNo() {
		return pvNo;
	}

	public void setPvNo(String pvNo) {
		this.pvNo = pvNo;
	}

	public String getPurOrgUnitNo() {
		return purOrgUnitNo;
	}

	public void setPurOrgUnitNo(String purOrgUnitNo) {
		this.purOrgUnitNo = purOrgUnitNo;
	}

	public String getVendorCode() {
		return vendorCode;
	}

	public void setVendorCode(String vendorCode) {
		this.vendorCode = vendorCode;
	}

	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	public Date getRecDateFrom() {
		return recDateFrom;
	}

	public void setRecDateFrom(Date recDateFrom) {
		this.recDateFrom = recDateFrom;
	}

	public Date getRecDateTo() {
		return recDateTo;
	}

	public void setRecDateTo(Date recDateTo) {
		this.recDateTo = recDateTo;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
