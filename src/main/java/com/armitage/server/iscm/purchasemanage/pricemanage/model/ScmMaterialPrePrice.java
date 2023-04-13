
package com.armitage.server.iscm.purchasemanage.pricemanage.model;
import java.math.BigDecimal;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import com.armitage.server.common.base.model.BaseModel;
 
@XmlRootElement(name = "scmMaterialPrePrice")  
public class ScmMaterialPrePrice extends BaseModel{
	private long priceBillId;
    private long vendorId;
	private long itemId;
	private String itemNo;
    private BigDecimal price;
    private BigDecimal taxRate;
    private BigDecimal taxPrice;
    private long purUnit;
    private String refPriceStatus;
    private String taxRateStr;
    private String vendorName;
    private String bizType;
    //备选
    private long preVendorId1;
    private BigDecimal prePrice1;
    private BigDecimal preTaxRate1;
    private BigDecimal preTaxPrice1;
    private String preTaxRateStr1;
    private long preVendorId2;
    private BigDecimal prePrice2;
    private BigDecimal preTaxRate2;
    private BigDecimal preTaxPrice2;
    private String preTaxRateStr2;
    private long preVendorId3;
    private BigDecimal prePrice3;
    private BigDecimal preTaxRate3;
    private BigDecimal preTaxPrice3;
    private String preTaxRateStr3;
    
	public long getPreVendorId1() {
		return preVendorId1;
	}
	public void setPreVendorId1(long preVendorId1) {
		this.preVendorId1 = preVendorId1;
	}
	public BigDecimal getPrePrice1() {
		return prePrice1;
	}
	public void setPrePrice1(BigDecimal prePrice1) {
		this.prePrice1 = prePrice1;
	}
	public BigDecimal getPreTaxRate1() {
		return preTaxRate1;
	}
	public void setPreTaxRate1(BigDecimal preTaxRate1) {
		this.preTaxRate1 = preTaxRate1;
	}
	public BigDecimal getPreTaxPrice1() {
		return preTaxPrice1;
	}
	public void setPreTaxPrice1(BigDecimal preTaxPrice1) {
		this.preTaxPrice1 = preTaxPrice1;
	}
	public String getPreTaxRateStr1() {
		return preTaxRateStr1;
	}
	public void setPreTaxRateStr1(String preTaxRateStr1) {
		this.preTaxRateStr1 = preTaxRateStr1;
	}
	public long getPreVendorId2() {
		return preVendorId2;
	}
	public void setPreVendorId2(long preVendorId2) {
		this.preVendorId2 = preVendorId2;
	}
	public BigDecimal getPrePrice2() {
		return prePrice2;
	}
	public void setPrePrice2(BigDecimal prePrice2) {
		this.prePrice2 = prePrice2;
	}
	public BigDecimal getPreTaxRate2() {
		return preTaxRate2;
	}
	public void setPreTaxRate2(BigDecimal preTaxRate2) {
		this.preTaxRate2 = preTaxRate2;
	}
	public BigDecimal getPreTaxPrice2() {
		return preTaxPrice2;
	}
	public void setPreTaxPrice2(BigDecimal preTaxPrice2) {
		this.preTaxPrice2 = preTaxPrice2;
	}
	public String getPreTaxRateStr2() {
		return preTaxRateStr2;
	}
	public void setPreTaxRateStr2(String preTaxRateStr2) {
		this.preTaxRateStr2 = preTaxRateStr2;
	}
	public long getPreVendorId3() {
		return preVendorId3;
	}
	public void setPreVendorId3(long preVendorId3) {
		this.preVendorId3 = preVendorId3;
	}
	public BigDecimal getPrePrice3() {
		return prePrice3;
	}
	public void setPrePrice3(BigDecimal prePrice3) {
		this.prePrice3 = prePrice3;
	}
	public BigDecimal getPreTaxRate3() {
		return preTaxRate3;
	}
	public void setPreTaxRate3(BigDecimal preTaxRate3) {
		this.preTaxRate3 = preTaxRate3;
	}
	public BigDecimal getPreTaxPrice3() {
		return preTaxPrice3;
	}
	public void setPreTaxPrice3(BigDecimal preTaxPrice3) {
		this.preTaxPrice3 = preTaxPrice3;
	}
	public String getPreTaxRateStr3() {
		return preTaxRateStr3;
	}
	public void setPreTaxRateStr3(String preTaxRateStr3) {
		this.preTaxRateStr3 = preTaxRateStr3;
	}
	public long getPriceBillId() {
		return priceBillId;
	}
	public void setPriceBillId(long priceBillId) {
		this.priceBillId = priceBillId;
	}
	public long getVendorId() {
		return vendorId;
	}
	public void setVendorId(long vendorId) {
		this.vendorId = vendorId;
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
	public String getRefPriceStatus() {
		return refPriceStatus;
	}
	public void setRefPriceStatus(String refPriceStatus) {
		this.refPriceStatus = refPriceStatus;
	}
	public long getPurUnit() {
		return purUnit;
	}
	public void setPurUnit(long purUnit) {
		this.purUnit = purUnit;
	}
	public String getVendorName() {
		return vendorName;
	}
	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}
	public String getBizType() {
		return bizType;
	}
	public void setBizType(String bizType) {
		this.bizType = bizType;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public BigDecimal getTaxRate() {
		return taxRate;
	}
	public void setTaxRate(BigDecimal taxRate) {
		this.taxRate = taxRate;
	}
	public BigDecimal getTaxPrice() {
		return taxPrice;
	}
	public void setTaxPrice(BigDecimal taxPrice) {
		this.taxPrice = taxPrice;
	}
	public String getTaxRateStr() {
		return taxRateStr;
	}
	public void setTaxRateStr(String taxRateStr) {
		this.taxRateStr = taxRateStr;
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
