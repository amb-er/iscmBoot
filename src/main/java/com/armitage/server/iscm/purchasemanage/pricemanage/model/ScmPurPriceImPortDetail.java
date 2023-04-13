package com.armitage.server.iscm.purchasemanage.pricemanage.model;

import java.math.BigDecimal;
import java.util.Date;
 
public class ScmPurPriceImPortDetail{
    
	private String itemNo;
	private BigDecimal inquiryPrice1; //组价
    private BigDecimal inquiryPrice2;	
    private BigDecimal inquiryPrice3;
    private BigDecimal price1;  //供应商价
    private BigDecimal price2;
    private BigDecimal price3;
    private BigDecimal taxRate1;//供应商税率
    private BigDecimal taxRate2;//供应商税率
    private BigDecimal taxRate3;//供应商税率
    private BigDecimal prePrice; //上期
    private BigDecimal lastYearPrice; //同期
    private BigDecimal price;   //本期含税定价
    private BigDecimal taxRate;
    private BigDecimal riseInquiryRate;
    private BigDecimal prePurQty;
    private BigDecimal riseRate;
    private BigDecimal differCost;
    private long itemId;
    
    private String remarks;
    public String getItemNo() {
        return itemNo;
    }
    public void setItemNo(String itemNo) {
        this.itemNo = itemNo;
    }
    public BigDecimal getInquiryPrice1() {
        return inquiryPrice1;
    }
    public void setInquiryPrice1(BigDecimal inquiryPrice1) {
        this.inquiryPrice1 = inquiryPrice1;
    }
    public BigDecimal getInquiryPrice2() {
        return inquiryPrice2;
    }
    public void setInquiryPrice2(BigDecimal inquiryPrice2) {
        this.inquiryPrice2 = inquiryPrice2;
    }
    public BigDecimal getInquiryPrice3() {
        return inquiryPrice3;
    }
    public void setInquiryPrice3(BigDecimal inquiryPrice3) {
        this.inquiryPrice3 = inquiryPrice3;
    }
    public BigDecimal getPrice1() {
        return price1;
    }
    public void setPrice1(BigDecimal price1) {
        this.price1 = price1;
    }
    public BigDecimal getPrice2() {
        return price2;
    }
    public void setPrice2(BigDecimal price2) {
        this.price2 = price2;
    }
    public BigDecimal getPrice3() {
        return price3;
    }
    public void setPrice3(BigDecimal price3) {
        this.price3 = price3;
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
    public String getRemarks() {
        return remarks;
    }
    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
	public BigDecimal getRiseInquiryRate() {
		return riseInquiryRate;
	}
	public void setRiseInquiryRate(BigDecimal riseInquiryRate) {
		this.riseInquiryRate = riseInquiryRate;
	}
	public BigDecimal getPrePurQty() {
		return prePurQty;
	}
	public void setPrePurQty(BigDecimal prePurQty) {
		this.prePurQty = prePurQty;
	}
	public BigDecimal getRiseRate() {
		return riseRate;
	}
	public void setRiseRate(BigDecimal riseRate) {
		this.riseRate = riseRate;
	}
	public BigDecimal getDifferCost() {
		return differCost;
	}
	public void setDifferCost(BigDecimal differCost) {
		this.differCost = differCost;
	}
	public long getItemId() {
		return itemId;
	}
	public void setItemId(long itemId) {
		this.itemId = itemId;
	}
	public BigDecimal getTaxRate1() {
		return taxRate1;
	}
	public void setTaxRate1(BigDecimal taxRate1) {
		this.taxRate1 = taxRate1;
	}
	public BigDecimal getTaxRate2() {
		return taxRate2;
	}
	public void setTaxRate2(BigDecimal taxRate2) {
		this.taxRate2 = taxRate2;
	}
	public BigDecimal getTaxRate3() {
		return taxRate3;
	}
	public void setTaxRate3(BigDecimal taxRate3) {
		this.taxRate3 = taxRate3;
	}
   
}
