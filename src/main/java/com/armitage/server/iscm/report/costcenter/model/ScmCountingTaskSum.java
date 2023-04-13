package com.armitage.server.iscm.report.costcenter.model;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import com.armitage.server.common.base.model.BaseModel;
import com.armitage.server.common.base.model.RelationModel;

/**
 * 盘存盘点表
 * @author Fengxq
 *
 */
public class ScmCountingTaskSum extends BaseModel {
	private String finOrgUnitNo;
	private String finOrgUnitName;
	private String taskNo;
	private boolean costCenter;
	private String whName;
	private BigDecimal amt;
	private BigDecimal taxAmt;
	
	public String getFinOrgUnitNo() {
		return finOrgUnitNo;
	}

	public String getFinOrgUnitName() {
		return finOrgUnitName;
	}

	public String getTaskNo() {
		return taskNo;
	}

	public boolean isCostCenter() {
		return costCenter;
	}

	public String getWhName() {
		return whName;
	}

	public BigDecimal getAmt() {
		return amt;
	}

	public BigDecimal getTaxAmt() {
		return taxAmt;
	}

	public void setFinOrgUnitNo(String finOrgUnitNo) {
		this.finOrgUnitNo = finOrgUnitNo;
	}

	public void setFinOrgUnitName(String finOrgUnitName) {
		this.finOrgUnitName = finOrgUnitName;
	}

	public void setTaskNo(String taskNo) {
		this.taskNo = taskNo;
	}

	public void setCostCenter(boolean costCenter) {
		this.costCenter = costCenter;
	}

	public void setWhName(String whName) {
		this.whName = whName;
	}

	public void setAmt(BigDecimal amt) {
		this.amt = amt;
	}

	public void setTaxAmt(BigDecimal taxAmt) {
		this.taxAmt = taxAmt;
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
