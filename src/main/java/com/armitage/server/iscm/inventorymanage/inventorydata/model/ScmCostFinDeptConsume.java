package com.armitage.server.iscm.inventorymanage.inventorydata.model;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.armitage.server.common.base.model.BaseModel;
import com.armitage.server.common.base.model.RelationModel;

public class ScmCostFinDeptConsume extends BaseModel {
	private String finOrgUnitNo;
	private String costOrgUnitNo;
	private String costOrgUnitName;
	private long groupId;
    private String longNo;
	private String classCode;
	private String className;
	private long costUseTypeId;
	private String costUseTypeCode;
	private String costUseTypeName;
	private BigDecimal initAmt;
	private BigDecimal initTax;
	private BigDecimal initTaxAmt;
	private BigDecimal addInAmt;
	private BigDecimal addInTax;
	private BigDecimal addInTaxAmt;
	private BigDecimal outAmt;
	private BigDecimal outTax;
	private BigDecimal outTaxAmt;
	private BigDecimal moveInAmt;
	private BigDecimal moveInTax;
	private BigDecimal moveInTaxAmt;
	private BigDecimal moveOutAmt;
	private BigDecimal moveOutTax;
	private BigDecimal moveOutTaxAmt;
	private BigDecimal stockAmt;
	private BigDecimal stockTax;
	private BigDecimal stockTaxAmt;
	private BigDecimal costAmt;
	private BigDecimal costTax;
	private BigDecimal costTaxAmt;
	
	public String getFinOrgUnitNo() {
		return finOrgUnitNo;
	}
	public void setFinOrgUnitNo(String finOrgUnitNo) {
		this.finOrgUnitNo = finOrgUnitNo;
	}
	public String getCostOrgUnitNo() {
		return costOrgUnitNo;
	}
	public void setCostOrgUnitNo(String costOrgUnitNo) {
		this.costOrgUnitNo = costOrgUnitNo;
	}
	public String getCostOrgUnitName() {
		return costOrgUnitName;
	}
	public void setCostOrgUnitName(String costOrgUnitName) {
		this.costOrgUnitName = costOrgUnitName;
	}
	public long getGroupId() {
		return groupId;
	}
	public void setGroupId(long groupId) {
		this.groupId = groupId;
	}
	public String getLongNo() {
		return longNo;
	}
	public void setLongNo(String longNo) {
		this.longNo = longNo;
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
	public long getCostUseTypeId() {
		return costUseTypeId;
	}
	public void setCostUseTypeId(long costUseTypeId) {
		this.costUseTypeId = costUseTypeId;
	}
	public String getCostUseTypeCode() {
		return costUseTypeCode;
	}
	public void setCostUseTypeCode(String costUseTypeCode) {
		this.costUseTypeCode = costUseTypeCode;
	}
	public String getCostUseTypeName() {
		return costUseTypeName;
	}
	public void setCostUseTypeName(String costUseTypeName) {
		this.costUseTypeName = costUseTypeName;
	}
	public BigDecimal getInitAmt() {
		return initAmt;
	}
	public void setInitAmt(BigDecimal initAmt) {
		this.initAmt = initAmt;
	}
	public BigDecimal getInitTax() {
		return initTax;
	}
	public void setInitTax(BigDecimal initTax) {
		this.initTax = initTax;
	}
	public BigDecimal getInitTaxAmt() {
		return initTaxAmt;
	}
	public void setInitTaxAmt(BigDecimal initTaxAmt) {
		this.initTaxAmt = initTaxAmt;
	}
	public BigDecimal getAddInAmt() {
		return addInAmt;
	}
	public void setAddInAmt(BigDecimal addInAmt) {
		this.addInAmt = addInAmt;
	}
	public BigDecimal getAddInTax() {
		return addInTax;
	}
	public void setAddInTax(BigDecimal addInTax) {
		this.addInTax = addInTax;
	}
	public BigDecimal getAddInTaxAmt() {
		return addInTaxAmt;
	}
	public void setAddInTaxAmt(BigDecimal addInTaxAmt) {
		this.addInTaxAmt = addInTaxAmt;
	}
	public BigDecimal getOutAmt() {
		return outAmt;
	}
	public void setOutAmt(BigDecimal outAmt) {
		this.outAmt = outAmt;
	}
	public BigDecimal getOutTax() {
		return outTax;
	}
	public void setOutTax(BigDecimal outTax) {
		this.outTax = outTax;
	}
	public BigDecimal getOutTaxAmt() {
		return outTaxAmt;
	}
	public void setOutTaxAmt(BigDecimal outTaxAmt) {
		this.outTaxAmt = outTaxAmt;
	}
	public BigDecimal getMoveInAmt() {
		return moveInAmt;
	}
	public void setMoveInAmt(BigDecimal moveInAmt) {
		this.moveInAmt = moveInAmt;
	}
	public BigDecimal getMoveInTax() {
		return moveInTax;
	}
	public void setMoveInTax(BigDecimal moveInTax) {
		this.moveInTax = moveInTax;
	}
	public BigDecimal getMoveInTaxAmt() {
		return moveInTaxAmt;
	}
	public void setMoveInTaxAmt(BigDecimal moveInTaxAmt) {
		this.moveInTaxAmt = moveInTaxAmt;
	}
	public BigDecimal getMoveOutAmt() {
		return moveOutAmt;
	}
	public void setMoveOutAmt(BigDecimal moveOutAmt) {
		this.moveOutAmt = moveOutAmt;
	}
	public BigDecimal getMoveOutTax() {
		return moveOutTax;
	}
	public void setMoveOutTax(BigDecimal moveOutTax) {
		this.moveOutTax = moveOutTax;
	}
	public BigDecimal getMoveOutTaxAmt() {
		return moveOutTaxAmt;
	}
	public void setMoveOutTaxAmt(BigDecimal moveOutTaxAmt) {
		this.moveOutTaxAmt = moveOutTaxAmt;
	}
	public BigDecimal getStockAmt() {
		return stockAmt;
	}
	public void setStockAmt(BigDecimal stockAmt) {
		this.stockAmt = stockAmt;
	}
	public BigDecimal getStockTax() {
		return stockTax;
	}
	public void setStockTax(BigDecimal stockTax) {
		this.stockTax = stockTax;
	}
	public BigDecimal getStockTaxAmt() {
		return stockTaxAmt;
	}
	public void setStockTaxAmt(BigDecimal stockTaxAmt) {
		this.stockTaxAmt = stockTaxAmt;
	}
	public BigDecimal getCostAmt() {
		return costAmt;
	}
	public void setCostAmt(BigDecimal costAmt) {
		this.costAmt = costAmt;
	}
	public BigDecimal getCostTax() {
		return costTax;
	}
	public void setCostTax(BigDecimal costTax) {
		this.costTax = costTax;
	}
	public BigDecimal getCostTaxAmt() {
		return costTaxAmt;
	}
	public void setCostTaxAmt(BigDecimal costTaxAmt) {
		this.costTaxAmt = costTaxAmt;
	}
	public ScmCostFinDeptConsume(boolean flag) {
		super();
		if(flag) {
			initAmt = BigDecimal.ZERO;
			initTax = BigDecimal.ZERO;
			initTaxAmt = BigDecimal.ZERO;
			addInAmt = BigDecimal.ZERO;
			addInTax = BigDecimal.ZERO;
			addInTaxAmt = BigDecimal.ZERO;
			outAmt = BigDecimal.ZERO;
			outTax = BigDecimal.ZERO;
			outTaxAmt = BigDecimal.ZERO;
			moveInAmt = BigDecimal.ZERO;
			moveInTax = BigDecimal.ZERO;
			moveInTaxAmt = BigDecimal.ZERO;
			moveOutAmt = BigDecimal.ZERO;
			moveOutTax = BigDecimal.ZERO;
			moveOutTaxAmt = BigDecimal.ZERO;
			stockAmt = BigDecimal.ZERO;
			stockTax = BigDecimal.ZERO;
			stockTaxAmt = BigDecimal.ZERO;
			costAmt = BigDecimal.ZERO;
			costTax = BigDecimal.ZERO;
			costTaxAmt = BigDecimal.ZERO;
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
