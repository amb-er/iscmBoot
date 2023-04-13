package com.armitage.server.iscm.purchasemanage.pricemanage.model;

import java.math.BigDecimal;
import java.util.List;

import com.armitage.server.common.base.model.BaseModel;

public class ScmPurPriceAll  extends BaseModel{
	private long itemId;
	private BigDecimal vendorPrice1;
	private BigDecimal vendorPrice2;
	private BigDecimal vendorPrice3;
	private BigDecimal groupPrice1;
	private BigDecimal groupPrice2;
	private BigDecimal groupPrice3;
	private BigDecimal prePrice;
	private BigDecimal lastYearPrice;
	private BigDecimal vendorTaxRate1;
	private BigDecimal vendorTaxRate2;
	private BigDecimal vendorTaxRate3;
	public long getItemId() {
		return itemId;
	}
	public void setItemId(long itemId) {
		this.itemId = itemId;
	}
	public BigDecimal getVendorPrice1() {
		return vendorPrice1;
	}
	public void setVendorPrice1(BigDecimal vendorPrice1) {
		this.vendorPrice1 = vendorPrice1;
	}
	public BigDecimal getVendorPrice2() {
		return vendorPrice2;
	}
	public void setVendorPrice2(BigDecimal vendorPrice2) {
		this.vendorPrice2 = vendorPrice2;
	}
	public BigDecimal getVendorPrice3() {
		return vendorPrice3;
	}
	public void setVendorPrice3(BigDecimal vendorPrice3) {
		this.vendorPrice3 = vendorPrice3;
	}
	public BigDecimal getGroupPrice1() {
		return groupPrice1;
	}
	public void setGroupPrice1(BigDecimal groupPrice1) {
		this.groupPrice1 = groupPrice1;
	}
	public BigDecimal getGroupPrice2() {
		return groupPrice2;
	}
	public void setGroupPrice2(BigDecimal groupPrice2) {
		this.groupPrice2 = groupPrice2;
	}
	public BigDecimal getGroupPrice3() {
		return groupPrice3;
	}
	public void setGroupPrice3(BigDecimal groupPrice3) {
		this.groupPrice3 = groupPrice3;
	}
	public BigDecimal getPrePrice() {
		return prePrice;
	}
	public void setPrePrice(BigDecimal prePrice) {
		this.prePrice = prePrice;
	}
	public BigDecimal getLastYearPrice() {
		return lastYearPrice;
	}
	public void setLastYearPrice(BigDecimal lastYearPrice) {
		this.lastYearPrice = lastYearPrice;
	}
	public BigDecimal getVendorTaxRate1() {
		return vendorTaxRate1;
	}
	public void setVendorTaxRate1(BigDecimal vendorTaxRate1) {
		this.vendorTaxRate1 = vendorTaxRate1;
	}
	public BigDecimal getVendorTaxRate2() {
		return vendorTaxRate2;
	}
	public void setVendorTaxRate2(BigDecimal vendorTaxRate2) {
		this.vendorTaxRate2 = vendorTaxRate2;
	}
	public BigDecimal getVendorTaxRate3() {
		return vendorTaxRate3;
	}
	public void setVendorTaxRate3(BigDecimal vendorTaxRate3) {
		this.vendorTaxRate3 = vendorTaxRate3;
	}
	public ScmPurPriceAll(boolean defaultValue) {
		if (defaultValue) {
			this.vendorPrice1 = BigDecimal.ZERO;
			this.vendorPrice2 = BigDecimal.ZERO;
			this.vendorPrice3 = BigDecimal.ZERO;
			this.groupPrice1 = BigDecimal.ZERO;
			this.groupPrice2 = BigDecimal.ZERO;
			this.groupPrice3 = BigDecimal.ZERO;
			this.prePrice = BigDecimal.ZERO;
			this.lastYearPrice = BigDecimal.ZERO;
			this.vendorTaxRate1 = BigDecimal.ZERO;
			this.vendorTaxRate2 = BigDecimal.ZERO;
			this.vendorTaxRate3 = BigDecimal.ZERO;
		}
	}

	public ScmPurPriceAll() {

	}

	@Override
	public String getPkKey() {
		return null;
	}
	@Override
	public long getPK() {
		return 0;
	}
	@Override
	public String[] getRequiredFields() {
		return null;
	}
	@Override
	public String[] getFieldNames() {
		return null;
	}
	@Override
	public List<String[]> getUniqueKeys() {
		return null;
	}
}
