package com.armitage.server.iscm.basedata.model;

import java.math.BigDecimal;
import java.util.Date;

public class ScmIdleItems2 extends ScmIdleItems{
	public static final String FN_FINORGUNITNO = "finOrgUnitNo"; 
	public static final String FN_SPEC = "spec"; 
	public static final String FN_VENDORID = "vendorId"; 
	private long vendorId;
	private String vendorName;
	private String itemNo;
	private String itemName;
	private String className;
	private String spec;
	private BigDecimal stocQty;
	private BigDecimal price;
	private BigDecimal amt;
	private String finOrgUnitNo;//财务组织
	private long idleCause;
	private String idleCausename;
	private String unitName;
	private boolean existsAttach;
	private boolean choosed;
	private String attachPic;
	
	public long getVendorId() {
		return vendorId;
	}
	public void setVendorId(long vendorId) {
		this.vendorId = vendorId;
	}
	public String getItemNo() {
		return itemNo;
	}
	public void setItemNo(String itemNo) {
		this.itemNo = itemNo;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public String getSpec() {
		return spec;
	}
	public void setSpec(String spec) {
		this.spec = spec;
	}
	public BigDecimal getStocQty() {
		return stocQty;
	}
	public void setStocQty(BigDecimal stocQty) {
		this.stocQty = stocQty;
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
	public String getFinOrgUnitNo() {
		return finOrgUnitNo;
	}
	public void setFinOrgUnitNo(String finOrgUnitNo) {
		this.finOrgUnitNo = finOrgUnitNo;
	}
	public long getIdleCause() {
		return idleCause;
	}
	public void setIdleCause(long idleCause) {
		this.idleCause = idleCause;
	}
	public String getIdleCausename() {
		return idleCausename;
	}
	public void setIdleCausename(String idleCausename) {
		this.idleCausename = idleCausename;
	}
	public String getUnitName() {
		return unitName;
	}
	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}
	public String getVendorName() {
		return vendorName;
	}
	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}
	public boolean isExistsAttach() {
		return existsAttach;
	}
	public void setExistsAttach(boolean existsAttach) {
		this.existsAttach = existsAttach;
	}
	public boolean isChoosed() {
		return choosed;
	}
	public void setChoosed(boolean choosed) {
		this.choosed = choosed;
	}
	public String getAttachPic() {
		return attachPic;
	}
	public void setAttachPic(String attachPic) {
		this.attachPic = attachPic;
	}
	public ScmIdleItems2() {
		if (true) {
			this.stocQty=BigDecimal.ZERO;
			this.price=BigDecimal.ZERO;
			this.amt=BigDecimal.ZERO;
		}
	}
	
}
