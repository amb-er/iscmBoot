package com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.model;

import java.math.BigDecimal;
import java.util.List;

public class ScmInvSalePrice2 extends ScmInvSalePrice {
	public static final String FN_SALEPRICE = "salePrice";

	private BigDecimal salePrice;

    public BigDecimal getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(BigDecimal salePrice) {
        this.salePrice = salePrice;
    }

    public ScmInvSalePrice2(boolean defaultValue) {
		super(defaultValue);
	}

	public ScmInvSalePrice2() {
		super();
	}
}
