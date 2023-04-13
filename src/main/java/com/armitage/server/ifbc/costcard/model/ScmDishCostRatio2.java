package com.armitage.server.ifbc.costcard.model;

import java.math.BigDecimal;

public class ScmDishCostRatio2 extends ScmDishCostRatio{
	
	public static final String FN_DISHSPECNAME ="dishSpecName";
	public static final String FN_PRICE ="price";
	
	private String dishSpecName;
	private BigDecimal price;

	public String getDishSpecName() {
		return dishSpecName;
	}

	public void setDishSpecName(String dishSpecName) {
		this.dishSpecName = dishSpecName;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public ScmDishCostRatio2() {
		super();
	}

	public ScmDishCostRatio2(boolean defaultValue) {
		super(defaultValue);
		if(defaultValue){
			this.price=BigDecimal.ZERO;
		}
	}

}
