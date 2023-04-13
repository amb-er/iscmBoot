package com.armitage.server.iscm.report.inventory.model;

import java.math.BigDecimal;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import com.armitage.server.common.base.model.BaseModel;

@XmlRootElement(name = "scmInvGlobalStock")  
public class ScmInvGlobalStock  extends BaseModel {
	private String finOrgUnitNo;
	private String orgUnitNo;
	private long wareHouseId;
	private String costOrgUnitNo;
	private boolean costCenter;
	private long unit;
	private String unitName;
	private BigDecimal qty;
	private BigDecimal amt;
	private BigDecimal taxAmt;
	private int level;
	private String orgOrWhName;
	private BigDecimal totalAmt;
	private BigDecimal totalTaxAmt;

	public String getFinOrgUnitNo() {
		return finOrgUnitNo;
	}
	public void setFinOrgUnitNo(String finOrgUnitNo) {
		this.finOrgUnitNo = finOrgUnitNo;
	}
	public String getOrgUnitNo() {
		return orgUnitNo;
	}
	public void setOrgUnitNo(String orgUnitNo) {
		this.orgUnitNo = orgUnitNo;
	}
	public long getWareHouseId() {
		return wareHouseId;
	}
	public void setWareHouseId(long wareHouseId) {
		this.wareHouseId = wareHouseId;
	}
	public String getCostOrgUnitNo() {
		return costOrgUnitNo;
	}
	public void setCostOrgUnitNo(String costOrgUnitNo) {
		this.costOrgUnitNo = costOrgUnitNo;
	}
	public boolean isCostCenter() {
		return costCenter;
	}
	public void setCostCenter(boolean costCenter) {
		this.costCenter = costCenter;
	}
	public long getUnit() {
		return unit;
	}
	public void setUnit(long unit) {
		this.unit = unit;
	}
	public String getUnitName() {
		return unitName;
	}
	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}
	public BigDecimal getQty() {
		return qty;
	}
	public void setQty(BigDecimal qty) {
		this.qty = qty;
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
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public String getOrgOrWhName() {
		return orgOrWhName;
	}
	public void setOrgOrWhName(String orgOrWhName) {
		this.orgOrWhName = orgOrWhName;
	}

	public BigDecimal getTotalAmt() {
		return totalAmt;
	}
	public void setTotalAmt(BigDecimal totalAmt) {
		this.totalAmt = totalAmt;
	}
	public BigDecimal getTotalTaxAmt() {
		return totalTaxAmt;
	}
	public void setTotalTaxAmt(BigDecimal totalTaxAmt) {
		this.totalTaxAmt = totalTaxAmt;
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
}