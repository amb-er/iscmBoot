package com.armitage.server.iscm.basedata.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "scmsuppliergroup")
public class Scmsuppliergroup2 extends Scmsuppliergroup {
	public static final String FN_LIERID = "lier_id";
	public static final String FN_VENDORNO = "vendorNo";
	public static final String FN_VENDORNAME = "vendorName";
	public static final String FN_SIMPLENAME = "simpleName";
	public static final String FN_ADDRESS = "address";
	public static final String FN_REMARKS = "remarks";

	private long lier_id;
	private String vendorNo;
	private String vendorName;
	private String simpleName;
	private String address;
	private String remarks;

	public long getLier_id() {
		return lier_id;
	}

	public void setLier_id(long lier_id) {
		this.lier_id = lier_id;
	}

	public String getVendorNo() {
		return vendorNo;
	}

	public void setVendorNo(String vendorNo) {
		this.vendorNo = vendorNo;
	}

	public String getVendorName() {
		return vendorName;
	}

	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}

	public String getSimpleName() {
		return simpleName;
	}

	public void setSimpleName(String simpleName) {
		this.simpleName = simpleName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public Scmsuppliergroup2() {
		super();
	}

	public Scmsuppliergroup2(boolean defaultValue) {
		super(defaultValue);
	}

}
