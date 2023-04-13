package com.armitage.server.api.business.invmaterialreqbill.params;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="InvMaterialReqBillAddParams",description="新增参数对象")
public class InvMaterialReqBillAddParams {
	@ApiModelProperty(value="业务日期",dataType="Date",example="2019-05-22 00:00:00",required=true)
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	private Date bizDate;
	
	@ApiModelProperty(value="业务类型",dataType="String",example="1",required=true)
    private String bizType;
	
	@ApiModelProperty(value="库存组织",dataType="String",example="123",required=true)
	private String orgUnitNo;
	
	@ApiModelProperty(value="领料部门",dataType="String",example="123",required=true)
	private String useOrgUnitNo;
	
	@ApiModelProperty(value="出库仓库",dataType="String",example="00001",required=true)
	private String wareHouseNo;

	@ApiModelProperty(value="领料人",dataType="String",example="00001",required=true)
	private String requestPerson;
	
	@ApiModelProperty(value="备注",dataType="String",example="123",required=false)
	private String remarks;
	
	@ApiModelProperty(value="生成单据是否过账",dataType="Boolean")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private boolean posted;
	
	@ApiModelProperty(value="单据明细",dataType="List<InvMaterialReqBillDetailParams>",example="",required=true)
	private List<InvMaterialReqBillDetailParams> detailList;

	public String getOrgUnitNo() {
		return orgUnitNo;
	}

	public void setOrgUnitNo(String orgUnitNo) {
		this.orgUnitNo = orgUnitNo;
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
	
	public String getUseOrgUnitNo() {
		return useOrgUnitNo;
	}

	public void setUseOrgUnitNo(String useOrgUnitNo) {
		this.useOrgUnitNo = useOrgUnitNo;
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

	public String getRequestPerson() {
		return requestPerson;
	}

	public void setRequestPerson(String requestPerson) {
		this.requestPerson = requestPerson;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public boolean isPosted() {
		return posted;
	}

	public void setPosted(boolean posted) {
		this.posted = posted;
	}

	public List<InvMaterialReqBillDetailParams> getDetailList() {
		return detailList;
	}

	public void setDetailList(List<InvMaterialReqBillDetailParams> detailList) {
		this.detailList = detailList;
	}

}

