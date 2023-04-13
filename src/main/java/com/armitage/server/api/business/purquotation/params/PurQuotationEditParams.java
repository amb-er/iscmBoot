package com.armitage.server.api.business.purquotation.params;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="PurQuotationEditParams",description="新增参数对象")
public class PurQuotationEditParams {
	@ApiModelProperty(value="报价单号",dataType="String",example="123",required=true)
	private String pqNo;
	
	@ApiModelProperty(value="供应商编号",dataType="String",example="123",required=true)
	private String vendorCode;
	
	@ApiModelProperty(value="采购员编号",dataType="String",example="123",required=true)
	private String buyerCode;
	
	@ApiModelProperty(value="报价日期",dataType="Date",example="2019-05-22 00:00:00",required=true)
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	private Date pqDate;
	
	@ApiModelProperty(value="有效期起",dataType="Date",example="2019-05-22 00:00:00",required=true)
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	private Date begDate;
	
	@ApiModelProperty(value="有效期止",dataType="Date",example="2019-05-22 00:00:00",required=true)
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	private Date endDate;
	
	@ApiModelProperty(value="制单人",dataType="String",example="123",required=true)
	private String creator;
	
	@ApiModelProperty(value="制单日期",dataType="Date",example="2019-05-22 00:00:00",required=true)
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	private Date createDate;
	
	
	@ApiModelProperty(value="备注",dataType="String",example="123",required=true)
	private String remarks;
	
	@ApiModelProperty(value="单据明细",dataType="String",example="123",required=true)
	private List<PurQuotaionDetailParams> detailList;

	public String getPqNo() {
		return pqNo;
	}

	public void setPqNo(String pqNo) {
		this.pqNo = pqNo;
	}

	public String getVendorCode() {
		return vendorCode;
	}

	public void setVendorCode(String vendorCode) {
		this.vendorCode = vendorCode;
	}

	public String getBuyerCode() {
		return buyerCode;
	}

	public void setBuyerCode(String buyerCode) {
		this.buyerCode = buyerCode;
	}

	public Date getPqDate() {
		return pqDate;
	}

	public void setPqDate(Date pqDate) {
		this.pqDate = pqDate;
	}

	public Date getBegDate() {
		return begDate;
	}

	public void setBegDate(Date begDate) {
		this.begDate = begDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public List<PurQuotaionDetailParams> getDetailList() {
		return detailList;
	}

	public void setDetailList(List<PurQuotaionDetailParams> detailList) {
		this.detailList = detailList;
	}
}
