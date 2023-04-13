package com.armitage.server.iscm.basedata.model;

import java.util.List;

public class Scmsuppliercompanyinfo2 extends Scmsuppliercompanyinfo {

	private List<Scmsupplierbank> scmsupplierbankList;

	public List<Scmsupplierbank> getScmsupplierbankList() {
		return scmsupplierbankList;
	}

	public void setScmsupplierbankList(List<Scmsupplierbank> scmsupplierbankList) {
		this.scmsupplierbankList = scmsupplierbankList;
	}

	public Scmsuppliercompanyinfo2(boolean defaultValue) {
		super(defaultValue);
	}

	public Scmsuppliercompanyinfo2() {
		super();
	}
}
