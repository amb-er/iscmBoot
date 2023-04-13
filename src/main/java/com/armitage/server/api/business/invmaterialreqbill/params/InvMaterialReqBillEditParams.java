package com.armitage.server.api.business.invmaterialreqbill.params;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="InvMaterialReqBillEditParams",description="修改参数对象")
public class InvMaterialReqBillEditParams {
	@ApiModelProperty(value="出库单号",dataType="String",example="123",required=true)
	private String otNo;
	
	@ApiModelProperty(value="业务日期",dataType="Date",example="2019-05-22 00:00:00",required=true)
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	private Date bizDate;
	
	@ApiModelProperty(value="业务类型",dataType="String",example="1",required=true)
    private String bizType;
	
	@ApiModelProperty(value="库存组织",dataType="String",example="123",required=false)
	private String invOrgUnitNo;
	
	@ApiModelProperty(value="领料部门",dataType="String",example="123",required=false)
	private String useDepNo;
	
	@ApiModelProperty(value="领料人",dataType="String",example="00001",required=true)
	private String requestPerson;
	
	@ApiModelProperty(value="出库仓库",dataType="String",example="00001",required=false)
	private String wareHouseNo;

	@ApiModelProperty(value="备注",dataType="String",example="123",required=false)
	private String remarks;
	
	@ApiModelProperty(value="单据明细",dataType="List<InvMaterialReqBillDetailParams>",example="",required=true)
	private List<InvMaterialReqBillDetailParams> detailList;

	public String getOtNo() {
		return otNo;
	}

	public void setOtNo(String otNo) {
		this.otNo = otNo;
	}

	public String getInvOrgUnitNo() {
		return invOrgUnitNo;
	}

	public void setInvOrgUnitNo(String invOrgUnitNo) {
		this.invOrgUnitNo = invOrgUnitNo;
	}

	public String getUseDepNo() {
		return useDepNo;
	}

	public String getRequestPerson() {
		return requestPerson;
	}

	public void setRequestPerson(String requestPerson) {
		this.requestPerson = requestPerson;
	}

	public void setUseDepNo(String useDepNo) {
		this.useDepNo = useDepNo;
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

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public List<InvMaterialReqBillDetailParams> getDetailList() {
		return detailList;
	}

	public void setDetailList(List<InvMaterialReqBillDetailParams> detailList) {
		this.detailList = detailList;
	}

}

