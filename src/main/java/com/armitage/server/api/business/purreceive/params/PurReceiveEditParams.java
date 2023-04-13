package com.armitage.server.api.business.purreceive.params;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="PurReceiveEditParams",description="修改参数对象")
public class PurReceiveEditParams {
	@ApiModelProperty(value="收货单号",dataType="String",example="PV20191001001",required=true)
	private String pvNo;
	
	@ApiModelProperty(value="供应商编号",dataType="String",example="001",required=false)
	private String vendorCode;
	
	@ApiModelProperty(value="采购组织",dataType="String",example="00001",required=true)
	private String purOrgUnitNo;

	@ApiModelProperty(value="采购员编号",dataType="String",example="003",required=false)
	private String buyerCode;
	
	@ApiModelProperty(value="送货人",dataType="String",example="A003",required=false)
	private String deliverer;

	@ApiModelProperty(value="送货单号",dataType="String",example="123456",required=false)
	private String pdNo;

	@ApiModelProperty(value="收货人",dataType="String",example="123456",required=false)
	private String receiver;

	@ApiModelProperty(value="收货日期",dataType="Date",example="2019-05-22 00:00:00",required=false)
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	private Date receiveDate;
	
	@ApiModelProperty(value="备注",dataType="String",example="123",required=false)
	private String remarks;
	
	@ApiModelProperty(value="单据明细",dataType="List<PurRequireDetailParams>",example="123",required=false)
	private List<PurReceiveDetailParams> detailList;

	public String getPvNo() {
		return pvNo;
	}

	public void setPvNo(String pvNo) {
		this.pvNo = pvNo;
	}

	public String getVendorCode() {
		return vendorCode;
	}

	public void setVendorCode(String vendorCode) {
		this.vendorCode = vendorCode;
	}

	public String getPurOrgUnitNo() {
		return purOrgUnitNo;
	}

	public void setPurOrgUnitNo(String purOrgUnitNo) {
		this.purOrgUnitNo = purOrgUnitNo;
	}

	public String getBuyerCode() {
		return buyerCode;
	}

	public void setBuyerCode(String buyerCode) {
		this.buyerCode = buyerCode;
	}

	public String getDeliverer() {
		return deliverer;
	}

	public void setDeliverer(String deliverer) {
		this.deliverer = deliverer;
	}

	public String getPdNo() {
		return pdNo;
	}

	public void setPdNo(String pdNo) {
		this.pdNo = pdNo;
	}

	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	public Date getReceiveDate() {
		return receiveDate;
	}

	public void setReceiveDate(Date receiveDate) {
		this.receiveDate = receiveDate;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public List<PurReceiveDetailParams> getDetailList() {
		return detailList;
	}

	public void setDetailList(List<PurReceiveDetailParams> detailList) {
		this.detailList = detailList;
	}
	
}
