package com.armitage.server.api.business.purrequire.params;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

@ApiModel(value="PurReqMaterialPriceParams",description="物资价格查询参数")
public class PurReqMaterialPriceParams {
	@ApiModelProperty(value="采购组织",dataType="String",example="00000006",required=true)
	private String purOrgUnitNo;

	@ApiModelProperty(value="申购组织",dataType="String",example="00000008",required=true)
	private String reqOrgUnitNo;

	@ApiModelProperty(value="物资编码",dataType="String",example="12345",required=true)
	private String itemNo;

	@ApiModelProperty(value="申购日期",dataType="Date",example="2019-11-27 00:00:00",required=true)
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	private Date applyDate;

	@ApiModelProperty(value="需求日期",dataType="Date",example="2019-11-27 00:00:00",required=true)
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	private Date reqDate;
	
	@ApiModelProperty(value="供应商编码",dataType="String",example="001",required=true)
	private String vendorCode;

	public String getPurOrgUnitNo() {
		return purOrgUnitNo;
	}

	public void setPurOrgUnitNo(String purOrgUnitNo) {
		this.purOrgUnitNo = purOrgUnitNo;
	}

	public String getReqOrgUnitNo() {
		return reqOrgUnitNo;
	}

	public void setReqOrgUnitNo(String reqOrgUnitNo) {
		this.reqOrgUnitNo = reqOrgUnitNo;
	}

	public String getItemNo() {
		return itemNo;
	}

	public void setItemNo(String itemNo) {
		this.itemNo = itemNo;
	}

	public Date getApplyDate() {
		return applyDate;
	}

	public void setApplyDate(Date applyDate) {
		this.applyDate = applyDate;
	}

	public Date getReqDate() {
		return reqDate;
	}

	public void setReqDate(Date reqDate) {
		this.reqDate = reqDate;
	}

	public String getVendorCode() {
		return vendorCode;
	}

	public void setVendorCode(String vendorCode) {
		this.vendorCode = vendorCode;
	}
	
}
