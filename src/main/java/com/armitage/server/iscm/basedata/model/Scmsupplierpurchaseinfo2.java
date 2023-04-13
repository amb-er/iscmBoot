package com.armitage.server.iscm.basedata.model;

import java.util.List;

public class Scmsupplierpurchaseinfo2 extends Scmsupplierpurchaseinfo {
    public static final String FN_TAXRATE ="taxRate";
	private String taxRate;

	private List<Scmsupplierlinkman> scmsupplierlinkmanList;

	public String getTaxRate() {
		return taxRate;
	}

	public void setTaxRate(String taxRate) {
		this.taxRate = taxRate;
	}

	public List<Scmsupplierlinkman> getScmsupplierlinkmanList() {
		return scmsupplierlinkmanList;
	}

	public void setScmsupplierlinkmanList(List<Scmsupplierlinkman> scmsupplierlinkmanList) {
		this.scmsupplierlinkmanList = scmsupplierlinkmanList;
	}

	public Scmsupplierpurchaseinfo2(boolean defaultValue) {
		super(defaultValue);
	}

	public Scmsupplierpurchaseinfo2() {
		super();
	}
}
