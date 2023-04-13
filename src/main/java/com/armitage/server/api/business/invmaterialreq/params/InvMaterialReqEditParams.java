package com.armitage.server.api.business.invmaterialreq.params;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

@ApiModel(value="InvMaterialReqEditParams",description="修改参数对象")
public class InvMaterialReqEditParams {
	@ApiModelProperty(value="请购单号",dataType="String",example="123",required=true)
	private String reqNo;
	
	@ApiModelProperty(value="申购组织",dataType="String",example="123",required=false)
	private String useDeptNo;
	
	@ApiModelProperty(value="业务日期",dataType="Date",example="2019-05-22 00:00:00",required=false)
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	private Date bizDate;
	
	@ApiModelProperty(value="库存组织",dataType="String",example="00001",required=true)
	private String invOrgUnitNo;

	@ApiModelProperty(value="领料仓库编号",dataType="String",example="W00001",required=true)
	private String wareHouseNo;

	@ApiModelProperty(value="领料人",dataType="String",example="0001",required=true)
    private String requestPerson;

	@ApiModelProperty(value="备注",dataType="String",example="123",required=false)
	private String remarks;
	
	@ApiModelProperty(value="单据明细",dataType="List",example="123",required=false)
	private List<InvMaterialReqDetailParams> detailList;

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

	public Date getBizDate() {
		return bizDate;
	}

	public void setBizDate(Date bizDate) {
		this.bizDate = bizDate;
	}

	public String getInvOrgUnitNo() {
		return invOrgUnitNo;
	}

	public void setInvOrgUnitNo(String invOrgUnitNo) {
		this.invOrgUnitNo = invOrgUnitNo;
	}

	public String getWareHouseNo() {
		return wareHouseNo;
	}

	public void setWareHouseNo(String wareHouseNo) {
		this.wareHouseNo = wareHouseNo;
	}

	public String getRequestPerson() {
		return requestPerson;
	}

	public void setRequestPerson(String requestPerson) {
		this.requestPerson = requestPerson;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public List<InvMaterialReqDetailParams> getDetailList() {
		return detailList;
	}

	public void setDetailList(List<InvMaterialReqDetailParams> detailList) {
		this.detailList = detailList;
	}

}
