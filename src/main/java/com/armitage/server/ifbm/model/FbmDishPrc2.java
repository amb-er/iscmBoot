package com.armitage.server.ifbm.model;

import java.math.BigDecimal;
import java.util.Date;

public class FbmDishPrc2 extends FbmDishPrc  {

    public static final String FN_DISHSPECCODE ="dishSpecCode";
    public static final String FN_DISHSPECNAME ="dishSpecName";
    
    private String dishSpecCode;
    private String dishSpecName;
    private Date accDate;
    private BigDecimal salePrice;
	public String getDishSpecCode() {
		return dishSpecCode;
	}
	public void setDishSpecCode(String dishSpecCode) {
		this.dishSpecCode = dishSpecCode;
	}
	public String getDishSpecName() {
		return dishSpecName;
	}
	public void setDishSpecName(String dishSpecName) {
		this.dishSpecName = dishSpecName;
	}
	public Date getAccDate() {
		return accDate;
	}
	public void setAccDate(Date accDate) {
		this.accDate = accDate;
	}
	public BigDecimal getSalePrice() {
		return salePrice;
	}
	public void setSalePrice(BigDecimal salePrice) {
		this.salePrice = salePrice;
	}
	public FbmDishPrc2() {
		super();
		// TODO Auto-generated constructor stub
	}
	public FbmDishPrc2(boolean defaultValue) {
		super(defaultValue);
		if(defaultValue)
			this.salePrice = BigDecimal.ZERO;
	}

}
