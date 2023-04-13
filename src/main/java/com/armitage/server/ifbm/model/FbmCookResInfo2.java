package com.armitage.server.ifbm.model;

import java.math.BigDecimal;
import java.util.Date;

public class FbmCookResInfo2 extends FbmCookResInfo  {
    private Date accDate;
    private BigDecimal salePrice;

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
	public FbmCookResInfo2() {
		super();
	}
	public FbmCookResInfo2(boolean defaultValue) {
		super(defaultValue);
	}

}
