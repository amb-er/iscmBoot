
package com.armitage.server.iscm.purchasemanage.pricemanage.model;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

import com.armitage.server.common.base.model.BaseModel;
import com.armitage.server.common.base.model.RelationModel;
 
public class ScmPurPriceDetail {

    private long pmId;
    private int lineId;
    private long itemId;
    private long purUnit;
    private BigDecimal inquiryPrice1;
    private BigDecimal inquiryPrice2;
    private BigDecimal inquiryPrice3;
    private BigDecimal price1;
    private BigDecimal price2;
    private BigDecimal price3;
    private BigDecimal prePrice;
    private BigDecimal lastYearPrice;
    private BigDecimal price;
    private BigDecimal taxRate;
    private long selVndrId;
    private String rowStatus;
    private String checker;
    private Date checkDate;
    private String remarks;

    public long getPmId() {
        return pmId;
    }

    public void setPmId(long val) {
        this.pmId = val;
    }
    public int getLineId() {
        return lineId;
    }

    public void setLineId(int val) {
        this.lineId = val;
    }
    public long getItemId() {
        return itemId;
    }

    public void setItemId(long val) {
        this.itemId = val;
    }
    public long getPurUnit() {
        return purUnit;
    }

    public void setPurUnit(long val) {
        this.purUnit = val;
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

    public void setPrice1(BigDecimal val) {
        this.price1 = val;
    }
    public BigDecimal getPrice2() {
        return price2;
    }

    public void setPrice2(BigDecimal val) {
        this.price2 = val;
    }
    public BigDecimal getPrice3() {
        return price3;
    }

    public void setPrice3(BigDecimal val) {
        this.price3 = val;
    }
    public BigDecimal getPrePrice() {
        return prePrice;
    }

    public void setPrePrice(BigDecimal val) {
        this.prePrice = val;
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

    public void setPrice(BigDecimal val) {
        this.price = val;
    }
    public BigDecimal getTaxRate() {
		return taxRate;
	}

	public void setTaxRate(BigDecimal taxRate) {
		this.taxRate = taxRate;
	}

	public long getSelVndrId() {
		return selVndrId;
	}

	public void setSelVndrId(long selVndrId) {
		this.selVndrId = selVndrId;
	}

	public String getRowStatus() {
        return rowStatus;
    }

    public void setRowStatus(String val) {
        this.rowStatus = val;
    }
    public String getChecker() {
        return checker;
    }

    public void setChecker(String val) {
        this.checker = val;
    }
    public Date getCheckDate() {
        return checkDate;
    }

    public void setCheckDate(Date val) {
        this.checkDate = val;
    }
    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String val) {
        this.remarks = val;
    }

}
