package com.armitage.server.iscm.report.costcenter.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.armitage.server.common.base.model.BaseModel;
import com.armitage.server.common.base.model.RelationModel;

/**
 * 成本中心进出存明细
 * @author Fengxq
 *
 */
public class ScmDeptConsume extends BaseModel {
	private String classCode;
	private String className;
	private String orgUnitNo;
	private String orgUnitName;
	private String costOrgUnitNo;
	private BigDecimal startAmt;
	private BigDecimal purinAmt;
	private BigDecimal reqAmt;
	private BigDecimal cstinAmt;
	private BigDecimal cstoutAmt;
	private BigDecimal endAmt;
	private BigDecimal consumeAmt;
	private BigDecimal accountAmt;
	private String costuseTypeName;
	
	public String getCostuseTypeName() {
		return costuseTypeName;
	}

	public void setCostuseTypeName(String costuseTypeName) {
		this.costuseTypeName = costuseTypeName;
	}

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

	public BigDecimal getStartAmt() {
		return startAmt;
	}

	public void setStartAmt(BigDecimal startAmt) {
		this.startAmt = startAmt;
	}

	public BigDecimal getPurinAmt() {
		return purinAmt;
	}

	public void setPurinAmt(BigDecimal purinAmt) {
		this.purinAmt = purinAmt;
	}

	public BigDecimal getReqAmt() {
		return reqAmt;
	}

	public void setReqAmt(BigDecimal reqAmt) {
		this.reqAmt = reqAmt;
	}

	public BigDecimal getCstinAmt() {
		return cstinAmt;
	}

	public void setCstinAmt(BigDecimal cstinAmt) {
		this.cstinAmt = cstinAmt;
	}

	public BigDecimal getCstoutAmt() {
		return cstoutAmt;
	}

	public void setCstoutAmt(BigDecimal cstoutAmt) {
		this.cstoutAmt = cstoutAmt;
	}

	public BigDecimal getEndAmt() {
		return endAmt;
	}

	public void setEndAmt(BigDecimal endAmt) {
		this.endAmt = endAmt;
	}

	public BigDecimal getConsumeAmt() {
		return consumeAmt;
	}

	public void setConsumeAmt(BigDecimal consumeAmt) {
		this.consumeAmt = consumeAmt;
	}

	public BigDecimal getAccountAmt() {
		return accountAmt;
	}

	public void setAccountAmt(BigDecimal accountAmt) {
		this.accountAmt = accountAmt;
	}

	public ScmDeptConsume() {
		super();
	}

	public ScmDeptConsume(boolean flag) {
		super();
		if(flag) {
			this.startAmt = BigDecimal.ZERO;
			this.purinAmt = BigDecimal.ZERO;
			this.reqAmt = BigDecimal.ZERO;
			this.cstinAmt = BigDecimal.ZERO;
			this.cstoutAmt = BigDecimal.ZERO;
			this.endAmt = BigDecimal.ZERO;
			this.consumeAmt = BigDecimal.ZERO;
			this.accountAmt = BigDecimal.ZERO;
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
