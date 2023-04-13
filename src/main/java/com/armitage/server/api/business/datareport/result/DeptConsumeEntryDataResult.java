package com.armitage.server.api.business.datareport.result;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@ApiModel(value="DeptConsumeEntryDataResult",description="盘存任务查询返回结果")
public class DeptConsumeEntryDataResult {
	
	@ApiModelProperty(value="财务组织",dataType="String")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private String finOrgUnitNo;
	
	@ApiModelProperty(value="部门",dataType="String")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private String useOrgUnitNo;
	
	@ApiModelProperty(value="成本中心",dataType="String")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private String costOrgUnitNo;
	
	@ApiModelProperty(value="存库仓库",dataType="String")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private String whNo;
	
	@ApiModelProperty(value="物资类别",dataType="String")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private String classCode;
	
	@ApiModelProperty(value="上期结存金额",dataType="BigDecimal")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private BigDecimal startAmt;
	
	@ApiModelProperty(value="上期结存税额",dataType="BigDecimal")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private BigDecimal startTaxAmt;

	@ApiModelProperty(value="上期结存价税合计",dataType="BigDecimal")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private BigDecimal startTotalAmt;
	
	@ApiModelProperty(value="当期结存金额",dataType="BigDecimal")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private BigDecimal endAmt;
	
	@ApiModelProperty(value="当期结存税额",dataType="BigDecimal")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private BigDecimal endTaxAmt;

	@ApiModelProperty(value="当期结存价税合计",dataType="BigDecimal")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private BigDecimal endTotalAmt;
	
	@ApiModelProperty(value="领料金额",dataType="BigDecimal")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private BigDecimal reqAmt;
	
	@ApiModelProperty(value="领料税额",dataType="BigDecimal")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private BigDecimal reqTaxAmt;

	@ApiModelProperty(value="领料价税合计",dataType="BigDecimal")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private BigDecimal reqTotalAmt;
	
	@ApiModelProperty(value="入账金额",dataType="BigDecimal")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private BigDecimal amt;
	
	@ApiModelProperty(value="入账税额",dataType="BigDecimal")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private BigDecimal taxAmt;

	@ApiModelProperty(value="入账价税合计",dataType="BigDecimal")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private BigDecimal totalAmt;
	
	public String getFinOrgUnitNo() {
		return finOrgUnitNo;
	}

	public void setFinOrgUnitNo(String finOrgUnitNo) {
		this.finOrgUnitNo = finOrgUnitNo;
	}

	public String getUseOrgUnitNo() {
		return useOrgUnitNo;
	}

	public void setUseOrgUnitNo(String useOrgUnitNo) {
		this.useOrgUnitNo = useOrgUnitNo;
	}

	public String getCostOrgUnitNo() {
		return costOrgUnitNo;
	}

	public void setCostOrgUnitNo(String costOrgUnitNo) {
		this.costOrgUnitNo = costOrgUnitNo;
	}

	public String getWhNo() {
		return whNo;
	}

	public void setWhNo(String whNo) {
		this.whNo = whNo;
	}

	public String getClassCode() {
		return classCode;
	}

	public void setClassCode(String classCode) {
		this.classCode = classCode;
	}

	public BigDecimal getStartAmt() {
		return startAmt;
	}

	public void setStartAmt(BigDecimal startAmt) {
		this.startAmt = startAmt;
	}

	public BigDecimal getStartTaxAmt() {
		return startTaxAmt;
	}

	public void setStartTaxAmt(BigDecimal startTaxAmt) {
		this.startTaxAmt = startTaxAmt;
	}

	public BigDecimal getStartTotalAmt() {
		return startTotalAmt;
	}

	public void setStartTotalAmt(BigDecimal startTotalAmt) {
		this.startTotalAmt = startTotalAmt;
	}

	public BigDecimal getEndAmt() {
		return endAmt;
	}

	public void setEndAmt(BigDecimal endAmt) {
		this.endAmt = endAmt;
	}

	public BigDecimal getEndTaxAmt() {
		return endTaxAmt;
	}

	public void setEndTaxAmt(BigDecimal endTaxAmt) {
		this.endTaxAmt = endTaxAmt;
	}

	public BigDecimal getEndTotalAmt() {
		return endTotalAmt;
	}

	public void setEndTotalAmt(BigDecimal endTotalAmt) {
		this.endTotalAmt = endTotalAmt;
	}

	public BigDecimal getReqAmt() {
		return reqAmt;
	}

	public void setReqAmt(BigDecimal reqAmt) {
		this.reqAmt = reqAmt;
	}

	public BigDecimal getReqTaxAmt() {
		return reqTaxAmt;
	}

	public void setReqTaxAmt(BigDecimal reqTaxAmt) {
		this.reqTaxAmt = reqTaxAmt;
	}

	public BigDecimal getReqTotalAmt() {
		return reqTotalAmt;
	}

	public void setReqTotalAmt(BigDecimal reqTotalAmt) {
		this.reqTotalAmt = reqTotalAmt;
	}

	public BigDecimal getAmt() {
		return amt;
	}

	public void setAmt(BigDecimal amt) {
		this.amt = amt;
	}

	public BigDecimal getTaxAmt() {
		return taxAmt;
	}

	public void setTaxAmt(BigDecimal taxAmt) {
		this.taxAmt = taxAmt;
	}

	public BigDecimal getTotalAmt() {
		return totalAmt;
	}

	public void setTotalAmt(BigDecimal totalAmt) {
		this.totalAmt = totalAmt;
	}

}
