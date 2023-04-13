package com.armitage.server.api.business.invmaterialreqbill.params;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="InvReqBillLotListParams",description="查询列表参数对象")
public class InvReqBillLotListParams {

	@ApiModelProperty(value="物资编码",dataType="String",example="00001",required=true)
	private String itemNo;
	
	@ApiModelProperty(value="业务日期",dataType="Date",example="yyyy-MM-dd",required=false)
	@JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+8")
	private Date bizDate;
	
	@ApiModelProperty(value="业务类型",dataType="String",example="00001",required=true)
	private String bizType;
	
	@ApiModelProperty(value="领料仓库",dataType="String",example="00001",required=true)
	private String wareHouseNo;
	
	@ApiModelProperty(value="领料部门",dataType="String",example="123",required=true)
	private String useDeptNo;

	public String getItemNo() {
		return itemNo;
	}

	public void setItemNo(String itemNo) {
		this.itemNo = itemNo;
	}

	public Date getBizDate() {
		return bizDate;
	}

	public void setBizDate(Date bizDate) {
		this.bizDate = bizDate;
	}

	public String getBizType() {
		return bizType;
	}

	public void setBizType(String bizType) {
		this.bizType = bizType;
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
	
}
