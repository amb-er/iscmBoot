package com.armitage.server.iscm.report.inventory.model;

import java.math.BigDecimal;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;
import com.armitage.server.common.base.model.BaseModel;
/*
 * 销售汇总表
 */
@XmlRootElement(name = "scmInvItemSaleSum")  
public class ScmInvItemSaleSum  extends BaseModel {
	private String className;
	private String orgUnitNo;
	private String orgUnitName;
	private long wareHouseId;
	private String wareHouseName;
	private String lot;
	private long itemId;
	private String itemNo;
	private String itemName;
	private String spec;
	private long unit;
	private String unitName;
	private BigDecimal saleQty;
	private BigDecimal saleTaxRate;
	private BigDecimal saleTaxPrice;
	private BigDecimal saleTaxAmt;
	private BigDecimal returnQty;
	private BigDecimal returnTaxAmt;
	private BigDecimal totalQty;
	private BigDecimal totalTaxAmt;
	private long custId;
	private String custName;
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

	public String getLot() {
		return lot;
	}

	public void setLot(String lot) {
		this.lot = lot;
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

	public BigDecimal getSaleQty() {
		return saleQty;
	}

	public void setSaleQty(BigDecimal saleQty) {
		this.saleQty = saleQty;
	}

	public BigDecimal getSaleTaxAmt() {
		return saleTaxAmt;
	}

	public void setSaleTaxAmt(BigDecimal saleTaxAmt) {
		this.saleTaxAmt = saleTaxAmt;
	}

	public BigDecimal getReturnQty() {
		return returnQty;
	}

	public void setReturnQty(BigDecimal returnQty) {
		this.returnQty = returnQty;
	}

	public BigDecimal getReturnTaxAmt() {
		return returnTaxAmt;
	}

	public void setReturnTaxAmt(BigDecimal returnTaxAmt) {
		this.returnTaxAmt = returnTaxAmt;
	}

	public BigDecimal getTotalQty() {
		return totalQty;
	}

	public void setTotalQty(BigDecimal totalQty) {
		this.totalQty = totalQty;
	}

	public BigDecimal getTotalTaxAmt() {
		return totalTaxAmt;
	}

	public void setTotalTaxAmt(BigDecimal totalTaxAmt) {
		this.totalTaxAmt = totalTaxAmt;
	}

	public BigDecimal getSaleTaxRate() {
		return saleTaxRate;
	}

	public void setSaleTaxRate(BigDecimal saleTaxRate) {
		this.saleTaxRate = saleTaxRate;
	}

	public BigDecimal getSaleTaxPrice() {
		return saleTaxPrice;
	}

	public void setSaleTaxPrice(BigDecimal saleTaxPrice) {
		this.saleTaxPrice = saleTaxPrice;
	}

	public long getCustId() {
		return custId;
	}

	public void setCustId(long custId) {
		this.custId = custId;
	}

	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}

	public ScmInvItemSaleSum() {
		super();
	}

	public ScmInvItemSaleSum(boolean flag) {
		super();
		if(flag) {
			this.saleQty = BigDecimal.ZERO;
			this.saleTaxAmt = BigDecimal.ZERO;
			this.saleTaxRate = BigDecimal.ZERO;
			this.saleTaxPrice = BigDecimal.ZERO;
			this.returnQty = BigDecimal.ZERO;
			this.returnTaxAmt = BigDecimal.ZERO;
			this.totalQty = BigDecimal.ZERO;
			this.totalTaxAmt = BigDecimal.ZERO;
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