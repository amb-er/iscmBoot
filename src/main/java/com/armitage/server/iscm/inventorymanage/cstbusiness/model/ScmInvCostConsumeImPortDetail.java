package com.armitage.server.iscm.inventorymanage.cstbusiness.model;

import java.math.BigDecimal;
import java.util.Date;
 
public class ScmInvCostConsumeImPortDetail{
	 
	private String itemNo;
	private BigDecimal qty;
    private BigDecimal price;
    private BigDecimal amt;
    private Date expdate;
    private String remarks;
	public String getItemNo() {
		return itemNo;
	}
	public void setItemNo(String itemNo) {
		this.itemNo = itemNo;
	}
	
	public BigDecimal getQty() {
		return qty;
	}
	public void setQty(BigDecimal qty) {
		this.qty = qty;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public BigDecimal getAmt() {
		return amt;
	}
	public void setAmt(BigDecimal amt) {
		this.amt = amt;
	}
    public Date getExpdate() {
        return expdate;
    }
    public void setExpdate(Date expdate) {
        this.expdate = expdate;
    }
    public String getRemarks() {
        return remarks;
    }
    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
  
}
