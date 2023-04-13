package com.armitage.server.api.business.datasync.params;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.armitage.server.api.config.StringDeserializer;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="ScmPurRequireBillParams",description="请购单主表写入对象")
public class ScmPurRequireBillParam {
	@JsonDeserialize(using = StringDeserializer.class)
	@ApiModelProperty(value="请购单号",dataType="String",example="5465sad45645",required=false)
	private String prNo;
	
	@ApiModelProperty(value="申请日期",dataType="Date",example="2022-03-02 11:32:45",required=true)
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	private Date applyDate;
	
	@JsonDeserialize(using = StringDeserializer.class)
	@ApiModelProperty(value="单据状态",dataType="status",example="I",required=false)
	private String status;
	
	@ApiModelProperty(value="金额",dataType="BigDecimal",example="1000.05",required=false)
	private BigDecimal taxAmt;
	
	@ApiModelProperty(value="需求日期",dataType="Date",example="2020-05-14 18:27:45",required=false)
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	private Date reqDate;
	
	@JsonDeserialize(using = StringDeserializer.class)
	@ApiModelProperty(value="申购组织",dataType="String",example="00004314",required=false)
    private String orgUnitNo;
	
	@JsonDeserialize(using = StringDeserializer.class)
	@ApiModelProperty(value="创建人",dataType="String",example="kd888",required=false)
    private String creator;
	
	@JsonDeserialize(using = StringDeserializer.class)
	@ApiModelProperty(value="采购组织",dataType="String",example="00004313",required=false)
    private String purOrgUnitNo;
	
	@JsonDeserialize(using = StringDeserializer.class)
	@ApiModelProperty(value="申购理由",dataType="String",example="申购理由",required=false)
    private String subscribeReason;
	
	@JsonDeserialize(using = StringDeserializer.class)
	@ApiModelProperty(value="申购主题",dataType="String",example="申购主题",required=false)
    private String purRequireTheme;
	
	@JsonDeserialize(using = StringDeserializer.class)
	@ApiModelProperty(value="备注",dataType="String",example="申购主题",required=false)
    private String remarks;
	
	@ApiModelProperty(value="单据明细",dataType="List",required=true)
    private List<ScmPurRequireDetailParams> detailList;
	
	
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	public String getPrNo() {
		return prNo;
	}
	public void setPrNo(String prNo) {
		this.prNo = prNo;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public BigDecimal getTaxAmt() {
		return taxAmt;
	}
	public void setTaxAmt(BigDecimal taxAmt) {
		this.taxAmt = taxAmt;
	}
	public String getOrgUnitNo() {
		return orgUnitNo;
	}
	public void setOrgUnitNo(String orgUnitNo) {
		this.orgUnitNo = orgUnitNo;
	}
	public String getPurOrgUnitNo() {
		return purOrgUnitNo;
	}
	public void setPurOrgUnitNo(String purOrgUnitNo) {
		this.purOrgUnitNo = purOrgUnitNo;
	}
	public String getSubscribeReason() {
		return subscribeReason;
	}
	public void setSubscribeReason(String subscribeReason) {
		this.subscribeReason = subscribeReason;
	}
	public String getPurRequireTheme() {
		return purRequireTheme;
	}
	public void setPurRequireTheme(String purRequireTheme) {
		this.purRequireTheme = purRequireTheme;
	}
	public List<ScmPurRequireDetailParams> getDetailList() {
		return detailList;
	}
	public void setDetailList(List<ScmPurRequireDetailParams> detailList) {
		this.detailList = detailList;
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
	
	
}
