package com.armitage.server.iscm.inventorymanage.inventorydata.model;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.armitage.server.common.base.model.BaseModel;
import com.armitage.server.common.base.model.RelationModel;

public class ScmFinDeptConsume extends BaseModel {
	private String classCode;
	private String className;
	private String orgUnitNo;
	private String orgUnitName;
	private String costOrgUnitNo;
	private String whNo;
	private BigDecimal startAmt;
	private BigDecimal startTaxAmt;
	private BigDecimal startTotalAmt;
	private BigDecimal endAmt;
	private BigDecimal endTaxAmt;
	private BigDecimal endTotalAmt;
	private BigDecimal reqAmt;
	private BigDecimal reqTaxAmt;
	private BigDecimal reqTotalAmt;
	private BigDecimal amt;
	private BigDecimal taxAmt;
	private BigDecimal totalAmt;
	private String costUseTypeCode;
	private long costUseTypeId;
	
	public String getClassCode() {
		return classCode;
	}

	public void setClassCode(String classCode) {
		this.classCode = classCode;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getOrgUnitNo() {
		return orgUnitNo;
	}

	public void setOrgUnitNo(String orgUnitNo) {
		this.orgUnitNo = orgUnitNo;
	}

	public String getOrgUnitName() {
		return orgUnitName;
	}

	public void setOrgUnitName(String orgUnitName) {
		this.orgUnitName = orgUnitName;
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

	public ScmFinDeptConsume() {
		super();
	}

	public String getCostUseTypeCode() {
		return costUseTypeCode;
	}

	public void setCostUseTypeCode(String costUseTypeCode) {
		this.costUseTypeCode = costUseTypeCode;
	}

	public long getCostUseTypeId() {
		return costUseTypeId;
	}

	public void setCostUseTypeId(long costUseTypeId) {
		this.costUseTypeId = costUseTypeId;
	}

	public ScmFinDeptConsume(boolean flag) {
		super();
		if(flag) {
			this.startAmt = BigDecimal.ZERO;
			this.startTaxAmt = BigDecimal.ZERO;
			this.startTotalAmt = BigDecimal.ZERO;
			this.reqAmt = BigDecimal.ZERO;
			this.reqTaxAmt = BigDecimal.ZERO;
			this.reqTotalAmt = BigDecimal.ZERO;
			this.endAmt = BigDecimal.ZERO;
			this.endTaxAmt = BigDecimal.ZERO;
			this.endTotalAmt = BigDecimal.ZERO;
			this.amt = BigDecimal.ZERO;
			this.taxAmt = BigDecimal.ZERO;
			this.totalAmt = BigDecimal.ZERO;
		}
	}
	@Override
	public String getPkKey() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getPK() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String[] getRequiredFields() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String[] getFieldNames() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String[]> getUniqueKeys() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, RelationModel> getForeignMap() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, List<RelationModel>> getReferenceMap() {
		// TODO Auto-generated method stub
		return null;
	}

}
