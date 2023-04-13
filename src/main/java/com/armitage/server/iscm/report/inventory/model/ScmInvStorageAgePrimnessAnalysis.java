package com.armitage.server.iscm.report.inventory.model;

import java.math.BigDecimal;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;
import com.armitage.server.common.base.model.BaseModel;
/*
 * 库龄及呆滞分析表
 */
@XmlRootElement(name = "ScmInvStorageAgePrimnessAnalysis")  
public class ScmInvStorageAgePrimnessAnalysis  extends BaseModel {
	private long itemId;
	private String itemNo;
	private String itemName;
	private String spec;
	private String lot;
	private String orgUnitNo;
	private String orgUnitName;
	private long wareHouseId;
	private String wareHouseName;
	private long unit;
	private String unitName;
	private BigDecimal stockQty;
	private BigDecimal stockPrice;
	private BigDecimal stockAmt;
	private int storageAge;
	private int primnessDays;
	private BigDecimal primnessQty;
	
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

	public String getWareHouseName() {
		return wareHouseName;
	}

	public void setWareHouseName(String wareHouseName) {
		this.wareHouseName = wareHouseName;
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

	public BigDecimal getStockQty() {
		return stockQty;
	}

	public void setStockQty(BigDecimal stockQty) {
		this.stockQty = stockQty;
	}

	public BigDecimal getStockPrice() {
		return stockPrice;
	}

	public void setStockPrice(BigDecimal stockPrice) {
		this.stockPrice = stockPrice;
	}

	public BigDecimal getStockAmt() {
		return stockAmt;
	}

	public void setStockAmt(BigDecimal stockAmt) {
		this.stockAmt = stockAmt;
	}

	public int getStorageAge() {
		return storageAge;
	}

	public void setStorageAge(int storageAge) {
		this.storageAge = storageAge;
	}

	public int getPrimnessDays() {
		return primnessDays;
	}

	public void setPrimnessDays(int primnessDays) {
		this.primnessDays = primnessDays;
	}

	public BigDecimal getPrimnessQty() {
		return primnessQty;
	}

	public void setPrimnessQty(BigDecimal primnessQty) {
		this.primnessQty = primnessQty;
	}

	public ScmInvStorageAgePrimnessAnalysis() {
		super();
	}

	public ScmInvStorageAgePrimnessAnalysis(boolean flag) {
		super();
		if(flag) {
			this.stockQty = BigDecimal.ZERO;
			this.stockPrice = BigDecimal.ZERO;
			this.stockAmt = BigDecimal.ZERO;
			this.primnessQty = BigDecimal.ZERO;
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
}