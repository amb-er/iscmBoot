package com.armitage.server.iscm.report.inventory.model;

import java.math.BigDecimal;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import com.armitage.server.common.base.model.BaseModel;

@XmlRootElement(name = "scmInvRealtimeStockSum")  
public class ScmInvRealtimeStockSum  extends BaseModel {
	private String orgUnitNo;
	private String orgUnitName;
	private long wareHouseId;
	private String whName;
	private String className;
	private long itemId;
	private String itemNo;
	private String itemName;
	private String spec;
	private long unit;
	private String unitName;
	private BigDecimal qty;
	private BigDecimal amt;
	private BigDecimal taxAmt;
	private long pieUnit;
	private String pieUnitName;
	private BigDecimal pieQty;
	private BigDecimal addInQty;
	private BigDecimal addInPieQty;
	private BigDecimal addInAmt;
	private BigDecimal addInTaxAmt;
	private BigDecimal outQty;
	private BigDecimal outPieQty;
	private BigDecimal outAmt;
	private BigDecimal outTaxAmt;
	private BigDecimal allStockQty;
	private BigDecimal allStockPieQty;
	private BigDecimal allStockAmt;
	private BigDecimal allStockTaxAmt;
	private BigDecimal totalAmt;
	private BigDecimal totalTaxAmt;
	private BigDecimal totalAddInAmt;
	private BigDecimal totalAddInTaxAmt;
	private BigDecimal totalOutAmt;
	private BigDecimal totalOutTaxAmt;
	private BigDecimal totalAllStockAmt;
	private BigDecimal totalAllStockTaxAmt;

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

	public long getWareHouseId() {
		return wareHouseId;
	}

	public void setWareHouseId(long wareHouseId) {
		this.wareHouseId = wareHouseId;
	}

	public String getWhName() {
		return whName;
	}

	public void setWhName(String whName) {
		this.whName = whName;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public long getItemId() {
		return itemId;
	}

	public void setItemId(long itemId) {
		this.itemId = itemId;
	}

	public String getItemNo() {
		return itemNo;
	}

	public void setItemNo(String itemNo) {
		this.itemNo = itemNo;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getSpec() {
		return spec;
	}

	public void setSpec(String spec) {
		this.spec = spec;
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

	public long getPieUnit() {
		return pieUnit;
	}

	public void setPieUnit(long pieUnit) {
		this.pieUnit = pieUnit;
	}

	public String getPieUnitName() {
		return pieUnitName;
	}

	public void setPieUnitName(String pieUnitName) {
		this.pieUnitName = pieUnitName;
	}

	public BigDecimal getPieQty() {
		return pieQty;
	}

	public void setPieQty(BigDecimal pieQty) {
		this.pieQty = pieQty;
	}

	public BigDecimal getAddInQty() {
		return addInQty;
	}

	public void setAddInQty(BigDecimal addInQty) {
		this.addInQty = addInQty;
	}

	public BigDecimal getAddInPieQty() {
		return addInPieQty;
	}

	public void setAddInPieQty(BigDecimal addInPieQty) {
		this.addInPieQty = addInPieQty;
	}

	public BigDecimal getAddInAmt() {
		return addInAmt;
	}

	public void setAddInAmt(BigDecimal addInAmt) {
		this.addInAmt = addInAmt;
	}

	public BigDecimal getAddInTaxAmt() {
		return addInTaxAmt;
	}

	public void setAddInTaxAmt(BigDecimal addInTaxAmt) {
		this.addInTaxAmt = addInTaxAmt;
	}

	public BigDecimal getOutQty() {
		return outQty;
	}

	public void setOutQty(BigDecimal outQty) {
		this.outQty = outQty;
	}

	public BigDecimal getOutPieQty() {
		return outPieQty;
	}

	public void setOutPieQty(BigDecimal outPieQty) {
		this.outPieQty = outPieQty;
	}

	public BigDecimal getOutAmt() {
		return outAmt;
	}

	public void setOutAmt(BigDecimal outAmt) {
		this.outAmt = outAmt;
	}

	public BigDecimal getOutTaxAmt() {
		return outTaxAmt;
	}

	public void setOutTaxAmt(BigDecimal outTaxAmt) {
		this.outTaxAmt = outTaxAmt;
	}

	public BigDecimal getAllStockQty() {
		return allStockQty;
	}

	public void setAllStockQty(BigDecimal allStockQty) {
		this.allStockQty = allStockQty;
	}

	public BigDecimal getAllStockPieQty() {
		return allStockPieQty;
	}

	public void setAllStockPieQty(BigDecimal allStockPieQty) {
		this.allStockPieQty = allStockPieQty;
	}

	public BigDecimal getAllStockAmt() {
		return allStockAmt;
	}

	public void setAllStockAmt(BigDecimal allStockAmt) {
		this.allStockAmt = allStockAmt;
	}

	public BigDecimal getAllStockTaxAmt() {
		return allStockTaxAmt;
	}

	public void setAllStockTaxAmt(BigDecimal allStockTaxAmt) {
		this.allStockTaxAmt = allStockTaxAmt;
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

	public BigDecimal getTotalAddInAmt() {
		return totalAddInAmt;
	}

	public void setTotalAddInAmt(BigDecimal totalAddInAmt) {
		this.totalAddInAmt = totalAddInAmt;
	}

	public BigDecimal getTotalAddInTaxAmt() {
		return totalAddInTaxAmt;
	}

	public void setTotalAddInTaxAmt(BigDecimal totalAddInTaxAmt) {
		this.totalAddInTaxAmt = totalAddInTaxAmt;
	}

	public BigDecimal getTotalOutAmt() {
		return totalOutAmt;
	}

	public void setTotalOutAmt(BigDecimal totalOutAmt) {
		this.totalOutAmt = totalOutAmt;
	}

	public BigDecimal getTotalOutTaxAmt() {
		return totalOutTaxAmt;
	}

	public void setTotalOutTaxAmt(BigDecimal totalOutTaxAmt) {
		this.totalOutTaxAmt = totalOutTaxAmt;
	}

	public BigDecimal getTotalAllStockAmt() {
		return totalAllStockAmt;
	}

	public void setTotalAllStockAmt(BigDecimal totalAllStockAmt) {
		this.totalAllStockAmt = totalAllStockAmt;
	}

	public BigDecimal getTotalAllStockTaxAmt() {
		return totalAllStockTaxAmt;
	}

	public void setTotalAllStockTaxAmt(BigDecimal totalAllStockTaxAmt) {
		this.totalAllStockTaxAmt = totalAllStockTaxAmt;
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