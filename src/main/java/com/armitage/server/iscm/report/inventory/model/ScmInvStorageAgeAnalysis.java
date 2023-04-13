package com.armitage.server.iscm.report.inventory.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import com.armitage.server.common.base.model.BaseModel;

@XmlRootElement(name = "scmInvStorageAgeAnalysis")  
public class ScmInvStorageAgeAnalysis extends BaseModel{
	private String classCode;
    private String className;
    private long itemId;
    private String itemNo;
    private String itemName;
    private String spec;
    private long unit;
    private String unitName;
    private String lot;
    private String orgUnitNo;
    private String orgUnitName;
    private String finOrgUnitNo;
    private String finOrgUnitName;
    private long wareHouseId;
    private String wareHouseName;
    private BigDecimal currentQty;
    private BigDecimal qty;
    private BigDecimal taxAmt;
    private Date lastintdate;
    private long storageAge;
    private String intervaltype;
    
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

	public String getLot() {
		return lot;
	}

	public void setLot(String lot) {
		this.lot = lot;
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

	public String getFinOrgUnitNo() {
		return finOrgUnitNo;
	}

	public void setFinOrgUnitNo(String finOrgUnitNo) {
		this.finOrgUnitNo = finOrgUnitNo;
	}

	public String getFinOrgUnitName() {
		return finOrgUnitName;
	}

	public void setFinOrgUnitName(String finOrgUnitName) {
		this.finOrgUnitName = finOrgUnitName;
	}

	public long getWareHouseId() {
		return wareHouseId;
	}

	public void setWareHouseId(long wareHouseId) {
		this.wareHouseId = wareHouseId;
	}

	public String getWareHouseName() {
		return wareHouseName;
	}

	public void setWareHouseName(String wareHouseName) {
		this.wareHouseName = wareHouseName;
	}

	public BigDecimal getCurrentQty() {
		return currentQty;
	}

	public void setCurrentQty(BigDecimal currentQty) {
		this.currentQty = currentQty;
	}

	public BigDecimal getQty() {
		return qty;
	}

	public void setQty(BigDecimal qty) {
		this.qty = qty;
	}

	public BigDecimal getTaxAmt() {
		return taxAmt;
	}

	public void setTaxAmt(BigDecimal taxAmt) {
		this.taxAmt = taxAmt;
	}

	public Date getLastintdate() {
		return lastintdate;
	}

	public void setLastintdate(Date lastintdate) {
		this.lastintdate = lastintdate;
	}

	public long getStorageAge() {
		return storageAge;
	}

	public void setStorageAge(long storageAge) {
		this.storageAge = storageAge;
	}

	public String getIntervaltype() {
		return intervaltype;
	}

	public void setIntervaltype(String intervaltype) {
		this.intervaltype = intervaltype;
	}

	public ScmInvStorageAgeAnalysis() {
		super();
	}
	
	public ScmInvStorageAgeAnalysis(boolean flag) {
        super();
        if(flag) {
            this.currentQty = BigDecimal.ZERO;
            this.qty = BigDecimal.ZERO;
            this.taxAmt = BigDecimal.ZERO;

        }
    }

	@Override
	public String[] getFieldNames() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getPK() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getPkKey() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String[] getRequiredFields() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String[]> getUniqueKeys() {
		// TODO Auto-generated method stub
		return null;
	}

}
