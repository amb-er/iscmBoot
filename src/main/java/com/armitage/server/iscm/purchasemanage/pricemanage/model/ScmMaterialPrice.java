
package com.armitage.server.iscm.purchasemanage.pricemanage.model;
import java.math.BigDecimal;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import com.armitage.server.common.base.model.BaseModel;
 
@XmlRootElement(name = "scmMaterialPrice")  
public class ScmMaterialPrice extends BaseModel{
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
    private BigDecimal preTaxPrice1;
    private long preVendorId2;
    private BigDecimal preTaxPrice2;
    private long preVendorId3;
    private BigDecimal preTaxPrice3;
    
    private String priceBillNo;
    private String priceBillStatus;
    private BigDecimal invQty;
    
    private String vendorEditType;
    private String priceEditType;
    
    private BigDecimal stockAvgPrice;
    private long invUnit;
    private boolean directPurchase;
    
	public String getPriceBillNo() {
		return priceBillNo;
	}
	public void setPriceBillNo(String priceBillNo) {
		this.priceBillNo = priceBillNo;
	}
	public String getPriceBillStatus() {
		return priceBillStatus;
	}
	public void setPriceBillStatus(String priceBillStatus) {
		this.priceBillStatus = priceBillStatus;
	}
	public long getPreVendorId1() {
		return preVendorId1;
	}
	public void setPreVendorId1(long preVendorId1) {
		this.preVendorId1 = preVendorId1;
	}
	public BigDecimal getPreTaxPrice1() {
		return preTaxPrice1;
	}
	public void setPreTaxPrice1(BigDecimal preTaxPrice1) {
		this.preTaxPrice1 = preTaxPrice1;
	}
	public long getPreVendorId2() {
		return preVendorId2;
	}
	public void setPreVendorId2(long preVendorId2) {
		this.preVendorId2 = preVendorId2;
	}
	public BigDecimal getPreTaxPrice2() {
		return preTaxPrice2;
	}
	public void setPreTaxPrice2(BigDecimal preTaxPrice2) {
		this.preTaxPrice2 = preTaxPrice2;
	}
	public long getPreVendorId3() {
		return preVendorId3;
	}
	public void setPreVendorId3(long preVendorId3) {
		this.preVendorId3 = preVendorId3;
	}
	public BigDecimal getPreTaxPrice3() {
		return preTaxPrice3;
	}
	public void setPreTaxPrice3(BigDecimal preTaxPrice3) {
		this.preTaxPrice3 = preTaxPrice3;
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
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
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
	
	public BigDecimal getInvQty() {
		return invQty;
	}
	public void setInvQty(BigDecimal invQty) {
		this.invQty = invQty;
	}
	
	public String getVendorEditType() {
		return vendorEditType;
	}
	public void setVendorEditType(String vendorEditType) {
		this.vendorEditType = vendorEditType;
	}
	public String getPriceEditType() {
		return priceEditType;
	}
	public void setPriceEditType(String priceEditType) {
		this.priceEditType = priceEditType;
	}
	public BigDecimal getStockAvgPrice() {
		return stockAvgPrice;
	}
	public void setStockAvgPrice(BigDecimal stockAvgPrice) {
		this.stockAvgPrice = stockAvgPrice;
	}
	public long getInvUnit() {
		return invUnit;
	}
	public void setInvUnit(long invUnit) {
		this.invUnit = invUnit;
	}
	public boolean isDirectPurchase() {
		return directPurchase;
	}
	public void setDirectPurchase(boolean directPurchase) {
		this.directPurchase = directPurchase;
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

	public ScmMaterialPrice() {
	}
	
	public ScmMaterialPrice(boolean defaultValue) {
		if (defaultValue) {
			stockAvgPrice = BigDecimal.ZERO;
			directPurchase=false;
		}
	}
}
